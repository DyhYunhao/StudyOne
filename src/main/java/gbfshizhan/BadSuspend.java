package gbfshizhan;

public class BadSuspend {
    public static Object o = new Object();
    static ChangeObjectThread1 t1 = new ChangeObjectThread1("t1");
    static ChangeObjectThread1 t2 = new ChangeObjectThread1("t2");

    public static class ChangeObjectThread1 extends Thread{
        public ChangeObjectThread1(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (o){
                System.out.println("in " + getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }
}
