package gbfshizhan;

public class ConcurrencyTest {
    private static final long count = 10000001;

    private static void concurrency() throws Exception {
        long start = System.currentTimeMillis();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        t.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        t.join();
        System.out.println("concurrency : " + time + "ms, b = " + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial : " + time + "ms, b = " + b + ", a = " + a);
    }

    public static void main(String[] args) throws Exception {
        concurrency();
        serial();
    }
}
