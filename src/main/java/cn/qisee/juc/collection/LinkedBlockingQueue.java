package cn.qisee.juc.collection;

import java.util.concurrent.atomic.AtomicInteger;

import cn.qisee.juc.Condition;
import cn.qisee.juc.ReentrantLock;

public class LinkedBlockingQueue<T> {

    transient Node<T> head;

    /**
     * Tail of linked list.
     * Invariant: last.next == null
     */
    private transient Node<T> last;
    private AtomicInteger count = new AtomicInteger();
    private int capacity;
    private ReentrantLock putLock = new ReentrantLock();
    private Condition notFull = putLock.newCondition();
    private ReentrantLock takeLock = new ReentrantLock();
    private Condition notEmpty = takeLock.newCondition();

    public LinkedBlockingQueue(int capacity) {
        this.capacity = capacity;
        last = head = new Node<T>(null);
    }

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    /**
     * Signals a waiting put. Called only from take/poll.
     */
    private void signalNotFull() {
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    private void enqueue(Node<T> node) {
        // assert putLock.isHeldByCurrentThread();
        // assert last.next == null;
        last = last.next = node;
    }

    /**
     * Removes a node from head of queue.
     *
     * @return the node
     */
    private T dequeue() {
        // assert takeLock.isHeldByCurrentThread();
        // assert head.item == null;
        Node<T> h = head;
        Node<T> first = h.next;
        h.next = h; // help GC
        head = first;
        T x = first.item;
        first.item = null;
        return x;
    }

    public void put(T t) throws InterruptedException {
        if (t == null) throw new NullPointerException();
        // Note: convention in all put/take/etc is to preset local var
        // holding count negative to indicate failure unless set.
        int c = -1;
        Node<T> node = new Node<T>(t);
        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;
        putLock.lockInterruptibly();
        try {
            /*
             * Note that count is used in wait guard even though it is
             * not protected by lock. This works because count can
             * only decrease at this point (all other puts are shut
             * out by lock), and we (or some other waiting put) are
             * signalled if it ever changes from capacity. Similarly
             * for all other uses of count in other wait guards.
             */
            //多线程下put竞争
            //此处用while并阻塞，当再次竞争失败，继续等待
            while (count.get() == capacity) {
                notFull.await();
            }
            enqueue(node);
            //注意此处为getAndInc而不是incAndGet, 所以c为enqueue之前的值
            c = count.getAndIncrement();
            System.out.println("c = " + c);
            if (c + 1 < capacity)
                notFull.signal();
        } finally {
            putLock.unlock();
        }
        if (c == 0) {
            System.out.println("Signal empty");
            signalNotEmpty();
        }
    }

    public T take() throws InterruptedException {
        T x;
        int c = -1;
        final AtomicInteger count = this.count;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lockInterruptibly();
        try {
            while (count.get() == 0) {
                System.out.println("will wait");
                notEmpty.await();
            }
            x = dequeue();
            c = count.getAndDecrement();
            if (c > 1)
                notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
            signalNotFull();
        return x;
    }

    static class Node<T> {
        T item;

        Node<T> next;

        Node(T item) {this.item = item;}

    }
}
