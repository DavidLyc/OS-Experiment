package os1;

import java.math.BigDecimal;
import java.util.List;

public class HRN {

    private static BigDecimal nowTime = new BigDecimal(0);
    private static int finishJobNum = 0;
    private static BigDecimal totalTurnoverTime = new BigDecimal(0);
    private static BigDecimal totalWeightTurnoverTime = new BigDecimal(0);

    public static void main(String[] args) {
        System.out.println("最高响应比优先法");
        System.out.printf("%1s%10s%10s%10s%10s\n", "执行顺序", "开始时间", "完成时间", "周转时间", "带权周转时间");
        List<Job> jobList = Utils.getJobFromFile();
        while (finishJobNum < jobList.size()) {
            //计算每个作业的响应比
            for (Job job : jobList) {
                if (!job.getRunned() && job.getSubmitTime().compareTo(nowTime) != 1) {
                    job.setWaitTime(nowTime.subtract(job.getSubmitTime()));
                    job.setReact(new BigDecimal(1).add(job.getWaitTime().divide(job.getRunTime())));
                } else {
                    job.setReact(new BigDecimal(0));
                }
            }
            //响应比降序排序
            jobList.sort((o1, o2) -> o1.getReact().compareTo(o2.getReact()) == 1 ? -1 : 1);
            //若响应比最大的作业响应比值大于等于1则运行它
            if (!jobList.get(0).getRunned() && jobList.get(0).getReact().compareTo(new BigDecimal(1)) != -1) {
                finishJobNum++;
                Job runningJob = jobList.get(0);
                runningJob.setRunned(true);
                runningJob.setStartTime(nowTime);
                nowTime = nowTime.add(runningJob.getRunTime());
                runningJob.setFinishTime(nowTime);
                runningJob.setTurnoverTime(nowTime.subtract(runningJob.getSubmitTime()));
                runningJob.setWeightTurnoverTime(runningJob.getTurnoverTime().divide(runningJob.getRunTime()));
                totalTurnoverTime = totalTurnoverTime.add(runningJob.getTurnoverTime());
                totalWeightTurnoverTime = totalWeightTurnoverTime.add(runningJob.getTurnoverTime().divide(
                        runningJob.getRunTime()));
                System.out.printf("%1s%18s%13s%13s%12s\n", runningJob.getJobName()
                        , runningJob.getStartTime().setScale(2, BigDecimal.ROUND_DOWN)
                        , runningJob.getFinishTime().setScale(2, BigDecimal.ROUND_DOWN)
                        , runningJob.getTurnoverTime().setScale(2, BigDecimal.ROUND_DOWN)
                        , runningJob.getWeightTurnoverTime().setScale(2, BigDecimal.ROUND_DOWN));
            } else {
                nowTime = nowTime.add(new BigDecimal(0.1));
            }
        }
        System.out.println("\n平均周转时间: " + totalTurnoverTime.divide(BigDecimal.valueOf(jobList.size()))
                .setScale(2, BigDecimal.ROUND_DOWN));
        System.out.println("带权平均周转时间：" + totalWeightTurnoverTime.divide(BigDecimal.valueOf(jobList.size()))
                .setScale(2, BigDecimal.ROUND_DOWN));
    }

}


