package os2;

//奇数号哲学家先拿起右边筷子，然后再去拿左边筷子，而偶数号哲学家则相反
class TableManager1 extends TableBaseManager {

    @Override
    synchronized void takeChopstick() {
        //左右手筷子编号
        int i = Integer.parseInt(Thread.currentThread().getName());
        int j = i == 5 ? 1 : i + 1;
        int odd, even;
        if (i % 2 == 0) {
            even = i;
            odd = j;
        } else {
            odd = i;
            even = j;
        }
        //竞争奇数号筷子
        while (used[odd]) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        used[odd] = true;
        printTakeAction(odd);
        //竞争偶数号筷子
        while (used[even]) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        used[even] = true;
        printTakeAction(even);
    }

}
