package os3;

import java.util.List;

public class Fcfs {

    public static void start() {
        System.out.println("先来先服务磁盘调度");
        List<Disk> diskList = Util.getDisksFromFile();
        int totalDistance = 0;
        for (int i = 1; i < diskList.size(); i++) {
            totalDistance += Math.abs(diskList.get(i).getNum() - diskList.get(i - 1).getNum());
        }
        System.out.println(totalDistance);
    }

}
