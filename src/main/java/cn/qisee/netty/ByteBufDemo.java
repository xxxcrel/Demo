package cn.qisee.netty;

public class ByteBufDemo {
    public static void main(String[] args) {
        short b1 = 0b0101_1100_0011_0101;
        System.out.println(Integer.toBinaryString(b1 >> 4));
    }
}
