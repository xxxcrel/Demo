package cn.qisee.rxjava;

import io.reactivex.rxjava3.core.Flowable;

public class RxDemo {
    public static void main(String[] args) {
        hello("wuxc", "wuxj");
    }

    public static void hello(String... args){
        Flowable.fromArray(args).subscribe(System.out::println);
    }
}
