package cn.qisee.jni;

public class Sample {
    public static void main(String[] args) {
        new MyThread(() -> System.out.println("running")).start();
    }
}

class MyThread implements Runnable{

    private Runnable target;
    public MyThread(Runnable target){
        this.target = target;
    }
    @Override
    public void run() {
        if(target != null){
            target.run();
        }
    }

    public synchronized void start(){
        start0();
    }

    public native void start0();

}
