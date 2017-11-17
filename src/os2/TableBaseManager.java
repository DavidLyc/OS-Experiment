package os2;

abstract class TableBaseManager {

    Boolean used[] = {true, false, false, false, false, false};

    //放下筷子
    synchronized void putChopstick() {
        String name = Thread.currentThread().getName();
        int i = Integer.parseInt(name);
        int j = i == 5 ? 1 : i + 1;
        used[i] = false;
        used[j] = false;
        printPutAction(i);
        printPutAction(j);
        notifyAll();
    }

    void printTakeAction(int chopstickNum) {
        String name = Thread.currentThread().getName();
        System.out.println("哲学家" + name + "拿起" + chopstickNum + "号筷子");
    }

    private void printPutAction(int chopstickNum) {
        String name = Thread.currentThread().getName();
        System.out.println("哲学家" + name + "放下" + chopstickNum + "号筷子");
    }

    abstract void takeChopstick();

}
