package os2;

//至多只允许四个哲学家同时进餐，以保证至少有一个哲学家可以进餐，最终总会释放出他所用过的两只筷子，从而可使更多的哲学家进餐；
class TableManager3 extends TableBaseManager {

    private int eatingNum = 0;

    @Override
    synchronized void takeChopstick() {
        //左右手筷子编号
        int i = Integer.parseInt(Thread.currentThread().getName());
        int j = i == 5 ? 1 : i + 1;
        //等待进餐人数少于4且左右筷子可用
        while (eatingNum >= 4 || used[i] || used[j]) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        used[i] = true;
        used[j] = true;
        printTakeAction(i);
        printTakeAction(j);
        eatingNum++;
    }

    @Override
    synchronized void putChopstick() {
        eatingNum--;
        super.putChopstick();
    }
}
