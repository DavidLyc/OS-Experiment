package os3;

//最短寻道时间优先磁盘调度
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Short {

    public static void start() {
        System.out.println("最短寻道时间优先磁盘调度");
        List<Disk> diskList = Util.getDisksFromFile();
        List<Integer> integerList = new ArrayList<>();
        int start = 30;
        int totalDistance = 0;
        for (Disk disk : diskList) {
            integerList.add(disk.getNum());
        }
        integerList.add(start);
        integerList.sort(Comparator.comparingInt(o -> o));
        int pos = integerList.indexOf(start);
        while (integerList.size() > 1) {
            if (pos == integerList.size() - 1) {
                pos = integerList.size() - 2;
                totalDistance += integerList.get(pos - 1) - integerList.get(pos);
                integerList.remove(integerList.size() - 1);
            } else if (pos == 0) {
                totalDistance += integerList.get(1) - integerList.get(0);
                integerList.remove(0);
            } else {
                if (integerList.get(pos) - integerList.get(pos - 1) > integerList.get(pos + 1) - integerList.get(pos)) {
                    totalDistance += integerList.get(pos + 1) - integerList.get(pos);
                    integerList.remove(pos);
                } else {
                    totalDistance += (integerList.get(pos) - integerList.get(pos - 1));
                    integerList.remove(pos);
                    --pos;
                }
            }
        }
        System.out.println(totalDistance);
    }

}
