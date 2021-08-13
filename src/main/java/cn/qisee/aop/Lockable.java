package cn.qisee.aop;

public interface Lockable {
    void lock();
    void unlock();
    boolean locked();
}
