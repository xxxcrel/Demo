package cn.qisee.algorithm;

import java.util.stream.Stream;

public class Fibonacci {
    public static void main(String[] args) {
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Stream.iterate("s", s -> s + s).limit(4).forEach(System.out::println);
//        System.out.println(fib(5));
//        System.out.println(fib2(50, 1, 1));
//        System.out.println(nFactorial(4, 1));
    }
    public static int fib(int n){
        if(n == 0 || n == 1){
            return 1;
        }
        else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static int fib2(int cnt, int res1, int res2){
        if(cnt == 0){
            return res1;
        }
        return fib2(cnt - 1, res2, res1 + res2);
    }
    public static int nFactorial(int n, int res){
        return n == 1 ? res : nFactorial(n - 1, res * n);
    }

    interface IntFunction{
        int apply(int n, int res1, int res2);
    }

//    public static int lambdaRecursive(int cnt){
//        IntFunction gen;
//        gen = (n, res1, res2) -> n == 1 ? res2 : gen.apply(n - 1, res2 + 1, res1 * res2);
//        return gen.apply(cnt, 1, 2);
//    }
}
