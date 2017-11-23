package os3;

import java.io.*;
import java.util.*;

public class Util {

    public static List<Disk> getDisksFromFile() {
        List<Disk> diskList = new ArrayList<>();
        String pathname = "C:\\Users\\宇成童鞋\\IdeaProjects\\Test\\src\\os3\\disk_info.txt";
        File filename = new File(pathname);
        InputStreamReader reader;
        try {
            reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            String[] disks = line.split(",");
            for (String disk : disks) {
                diskList.add(new Disk(Integer.valueOf(disk)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diskList;
    }

}
