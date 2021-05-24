package beer.cheese.juc.collection;

import beer.cheese.juc.Condition;
import beer.cheese.juc.ReentrantLock;

public class ArrayBlockingQueue<E> {
    private Object[] items;
    private int takeIndex;
    private int putIndex;
    private int count;
    private ReentrantLock lock;
    private Condition notFull;
    private Condition notEmpty;

    public ArrayBlockingQueue(int cap){
        this(cap, false);
    }

    public ArrayBlockingQueue(int cap, boolean fair){
        items = new Object[cap];
        lock = new ReentrantLock(fair);
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    private void enqueue(E x) {
        // assert lock.getHoldCount() == 1;
        // assert items[putIndex] == null;
        final Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length)
            putIndex = 0;
        count++;
        notEmpty.signal();
    }

    /**
     * Extracts element at current take position, advances, and signals.
     * Call only when holding lock.
     */
    private E dequeue() {
        // assert lock.getHoldCount() == 1;
        // assert items[takeIndex] != null;
        final Object[] items = this.items;
        @SuppressWarnings("unchecked")
        E x = (E) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length)
            takeIndex = 0;
        count--;
//        if (itrs != null)
//            itrs.elementDequeued();
        notFull.signal();
        return x;
    }

    public void put(E e) throws InterruptedException{
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try{
            while(count == items.length)
                notFull.wait();
            enqueue(e);
        }finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException{
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while(count == 0)
                notEmpty.wait();
            return dequeue();
        }finally {
            lock.unlock();
        }
    }
}
