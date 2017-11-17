package os2;

//左右两只筷子均可用时，才允许哲学家拿起筷子就餐
class TableManager2 extends TableBaseManager {

    @Override
    synchronized void takeChopstick() {
        //左右手筷子编号
        int i = Integer.parseInt(Thread.currentThread().getName());
        int j = i == 5 ? 1 : i + 1;
        //等待左右两只筷子均可用
        while (used[i] || used[j]) {
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
    }

}
