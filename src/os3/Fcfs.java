package os3;

import java.util.List;

public class Fcfs {

    public static void start() {
        System.out.println("先来先服务磁盘调度");
        List<Disk> diskList = Util.getDisksFromFile();
        int start = 30;
        int totalDistance = 0;
        for (Disk aDiskList : diskList) {
            totalDistance += Math.abs(aDiskList.getNum() - start);
            start = aDiskList.getNum();
        }
        System.out.println(totalDistance);
    }

}
