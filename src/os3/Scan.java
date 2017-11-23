package os3;

import java.util.List;

public class Scan {

    public static void start() {
        System.out.println("扫描算法（SCAN，电梯算法）");
        List<Disk> diskList = Util.getDisksFromFile();
        int start = 53;
        int totalDistance = Math.abs(start - getMinDiskNum(diskList))
                + Math.abs(getMinDiskNum(diskList) - getMaxDiskNum(diskList));
        System.out.println(totalDistance);
    }

    private static int getMinDiskNum(List<Disk> disks) {
        int min = disks.get(0).getNum();
        for (Disk disk : disks) {
            if (disk.getNum() < min) {
                min = disk.getNum();
            }
        }
        return min;
    }

    private static int getMaxDiskNum(List<Disk> disks) {
        int max = 0;
        for (Disk disk : disks) {
            if (disk.getNum() > max) {
                max = disk.getNum();
            }
        }
        return max;
    }

}
