package gbfshizhan;

public class StopThreadUnsafe {

    public static User user = new User();

    private static class User{
        private int id;
        private String name;

        User() {
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class ChangeObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (user){
                    int value = (int)(System.currentTimeMillis() / 1000);
                    user.setId(value);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(value));
                }
                Thread.yield();
            }
        }
    }

    static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (user) {
                    if (user.getId() != Integer.parseInt(user.getName())){
                        System.out.println(user.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true){
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }


}
