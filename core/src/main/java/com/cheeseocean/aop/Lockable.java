package com.cheeseocean.aop;

public interface Lockable {
    void lock();
    void unlock();
    boolean locked();
}
