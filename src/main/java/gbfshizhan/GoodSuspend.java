package gbfshizhan;

public class GoodSuspend {
    public static Object o = new Object();

    public static class ChangeObjectThread2 extends Thread{
        volatile boolean suspendme = false;

        public void suspendMe(){
            suspendme = true;
        }

        public void resumeMe(){
            suspendme = false;
            synchronized (this){
                notify();
            }
        }

        @Override
        public void run() {
            while (true){
                synchronized (this){
                    while (suspendme){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (o){
                    System.out.println("in ChangeObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread1 extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (o){
                    System.out.println("in ReadObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread2 t1 = new ChangeObjectThread2();
        ReadObjectThread1 t2 = new ReadObjectThread1();
        t1.start();
        t2.start();
        Thread.sleep(10000);
        t1.suspendMe();
        System.out.println("suspend t1 2 sec");
        Thread.sleep(2000);
        System.out.println("resume t1");
        t1.resumeMe();
    }
}
