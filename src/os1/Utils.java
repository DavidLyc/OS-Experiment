package os1;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Job> getJobFromFile() {
        List<Job> jobList = new ArrayList<>();
        String pathname = "C:\\Users\\宇成童鞋\\IdeaProjects\\Test\\src\\os1\\1.txt";
        File filename = new File(pathname);
        InputStreamReader reader;
        try {
            reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line;
            String[] jobInfo;
            while (true) {
                line = br.readLine();
                if (line != null) {
                    jobInfo = line.split(",");
                    Job job = new Job(jobInfo[0], BigDecimal.valueOf(Double.parseDouble(jobInfo[1]))
                            , BigDecimal.valueOf(Double.parseDouble(jobInfo[2])));
                    jobList.add(job);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobList;
    }

}
