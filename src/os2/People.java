package os2;

public class People extends Thread {

    private TableBaseManager manager;
    private static final int TIME = 500;

    People(String name, TableBaseManager manager) {
        super(name);
        this.manager = manager;
    }

    @Override
    public void run() {
        super.run();
        thinking();
        manager.takeChopstick();  //P操作
        eating();
        manager.putChopstick();   //V操作
        thinking();
    }

    private void thinking() {
        System.out.println("哲学家" + Thread.currentThread().getName() + "思考中");
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eating() {
        System.out.println("哲学家" + Thread.currentThread().getName() + "进餐中");
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
