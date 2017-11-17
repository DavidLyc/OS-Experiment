package os1;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RR {

    private static final BigDecimal TIME = new BigDecimal(0.1);
    private static BigDecimal nowTime = new BigDecimal(0);
    private static int finishJobNum = 0;

    public static void main(String[] args) {
        List<Job> jobList = Utils.getJobFromFile();
        Queue<Job> workList = new LinkedList<>();
        final int allJobNum = jobList.size();
        while (finishJobNum != allJobNum) {
            //是否有新的作业可加入工作队列
            for (Job job : jobList) {
                if (!job.getRunned() && job.getSubmitTime().compareTo(nowTime) != 1) {
                    job.setRunned(true);
                    job.setStartTime(nowTime);
                    workList.add(job);
                }
            }
            //工作队列首部执行一个时间片
            if (workList.size() != 0) {
                Job workHead = workList.remove();
                workHead.setRunTime(workHead.getRunTime().subtract(TIME));
                System.out.println(workHead.getJobName() + "\t\t" + nowTime.setScale(2, BigDecimal.ROUND_DOWN)
                        + "\t\t" + nowTime.add(TIME).setScale(2, BigDecimal.ROUND_DOWN));
                if (workHead.getRunTime().compareTo(new BigDecimal(0)) == 1) {
                    workList.add(workHead);
                } else {
                    workHead.setFinishTime(nowTime);
                    finishJobNum++;
                }
            }
            //时间增加
            nowTime = nowTime.add(TIME);
        }
        System.out.printf("\n%1s%10s%10s%10s%10s\n", "作业名", "开始时间", "完成时间", "周转时间", "带权周转时间");
        BigDecimal totalTurnoverTime = new BigDecimal(0);
        BigDecimal totalWeightTurnoverTime = new BigDecimal(0);
        for (Job job : jobList) {
            job.setTurnoverTime(job.getFinishTime().subtract(job.getSubmitTime()));
            job.setWeightTurnoverTime(job.getTurnoverTime().divide(job.getTempRunTime()));
            totalTurnoverTime = totalTurnoverTime.add(job.getTurnoverTime());
            totalWeightTurnoverTime = totalWeightTurnoverTime.add(job.getTurnoverTime().divide(job.getTempRunTime()));
            System.out.printf("%1s%17s%13s%13s%12s\n", job.getJobName()
                    , job.getStartTime().setScale(2, BigDecimal.ROUND_DOWN)
                    , job.getFinishTime().setScale(2, BigDecimal.ROUND_DOWN)
                    , job.getTurnoverTime().setScale(2, BigDecimal.ROUND_DOWN)
                    , job.getWeightTurnoverTime().setScale(2, BigDecimal.ROUND_DOWN));
        }
        System.out.println("\n平均周转时间: " + totalTurnoverTime.divide(BigDecimal.valueOf(jobList.size()))
                .setScale(2, BigDecimal.ROUND_DOWN));
        System.out.println("带权平均周转时间：" + totalWeightTurnoverTime.divide(BigDecimal.valueOf(jobList.size()))
                .setScale(2, BigDecimal.ROUND_DOWN));
    }

}
