package os1;

import java.math.BigDecimal;
import java.util.List;

//短作业优先法
public class SJF {

    public static void main(String[] args) {
        BigDecimal nowTime = new BigDecimal(0);
        List<Job> allJobList = Utils.getJobFromFile();
        int runnedJobNum = 0;
        BigDecimal totalTurnoverTime = new BigDecimal(0);
        BigDecimal totalWeightTurnoverTime = new BigDecimal(0);
        System.out.println("短作业优先算法");
        System.out.printf("%1s%10s%10s%10s%10s\n", "执行顺序", "开始时间", "完成时间", "周转时间", "带权周转时间");
        Job minRunTimeJob;
        while (runnedJobNum != allJobList.size()) {
            minRunTimeJob = getMinRunTimeJob(nowTime, allJobList);
            if (minRunTimeJob != null) {
                runnedJobNum++;
                minRunTimeJob.setRunned(true);
                minRunTimeJob.setStartTime(nowTime);
                nowTime = nowTime.add(minRunTimeJob.getRunTime());
                minRunTimeJob.setFinishTime(nowTime);
                minRunTimeJob.setTurnoverTime(nowTime.subtract(minRunTimeJob.getSubmitTime()));
                minRunTimeJob.setWeightTurnoverTime(minRunTimeJob.getTurnoverTime()
                        .divide(minRunTimeJob.getRunTime()));
                totalTurnoverTime = totalTurnoverTime.add(minRunTimeJob.getTurnoverTime());
                totalWeightTurnoverTime = totalWeightTurnoverTime.add(minRunTimeJob.getTurnoverTime()
                        .divide(minRunTimeJob.getRunTime()));
                System.out.printf("%1s%18s%13s%13s%12s\n", minRunTimeJob.getJobName()
                        , minRunTimeJob.getStartTime().setScale(2, BigDecimal.ROUND_DOWN)
                        , minRunTimeJob.getFinishTime().setScale(2, BigDecimal.ROUND_DOWN)
                        , minRunTimeJob.getTurnoverTime().setScale(2, BigDecimal.ROUND_DOWN)
                        , minRunTimeJob.getWeightTurnoverTime().setScale(2, BigDecimal.ROUND_DOWN));
            } else {
                nowTime = nowTime.add(new BigDecimal(0.1));
            }
        }
        System.out.println("\n平均周转时间: " + totalTurnoverTime.divide(BigDecimal.valueOf(allJobList.size()))
                .setScale(2, BigDecimal.ROUND_DOWN));
        System.out.println("带权平均周转时间：" + totalWeightTurnoverTime.divide(BigDecimal.valueOf(allJobList.size()))
                .setScale(2, BigDecimal.ROUND_DOWN));
    }

    private static Job getMinRunTimeJob(BigDecimal nowTime, List<Job> jobList) {
        BigDecimal minRunTime = new BigDecimal(1000000);
        Job minRuntimeJob = null;
        for (Job job : jobList) {
            if (!job.getRunned() && job.getSubmitTime().compareTo(nowTime) != 1
                    && job.getRunTime().compareTo(minRunTime) == -1) {
                minRuntimeJob = job;
                minRunTime = job.getRunTime();
            }
        }
        return minRuntimeJob;
    }

}
