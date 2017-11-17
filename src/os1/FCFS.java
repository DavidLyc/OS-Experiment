package os1;

import java.math.BigDecimal;
import java.util.List;

//先来先服务
public class FCFS {

    private static BigDecimal mNowTime;
    private static BigDecimal mTotalTurnoverTime = new BigDecimal(0);
    private static BigDecimal mTotalWeightTurnoverTime = new BigDecimal(0);

    public static void main(String[] args) throws Exception {
        List<Job> mJobList = Utils.getJobFromFile();
        mJobList.sort((o1, o2) -> o1.getSubmitTime().compareTo(o2.getSubmitTime()) == 1 ? 1 : -1);
        System.out.println("先来先服务算法");
        System.out.printf("%1s%10s%10s%10s%10s\n", "执行顺序", "开始时间", "完成时间", "周转时间", "带权周转时间");
        //记录开始时间
        mNowTime = mJobList.get(0).getSubmitTime();
        for (Job job : mJobList) {
            job.setStartTime(mNowTime);
            mNowTime = mNowTime.add(job.getRunTime());
            job.setFinishTime(mNowTime);
            job.setTurnoverTime(mNowTime.subtract(job.getSubmitTime()));
            job.setWeightTurnoverTime(job.getTurnoverTime().divide(job.getRunTime()));
            mTotalTurnoverTime = mTotalTurnoverTime.add(job.getTurnoverTime());
            mTotalWeightTurnoverTime = mTotalWeightTurnoverTime.add(job.getTurnoverTime().divide(job.getRunTime()));
            System.out.printf("%1s%17s%13s%13s%12s\n", job.getJobName(), job.getStartTime(), job.getFinishTime()
                    , job.getTurnoverTime(), job.getWeightTurnoverTime());
        }
        System.out.println("\n平均周转时间: " + mTotalTurnoverTime.divide(BigDecimal.valueOf(mJobList.size())));
        System.out.println("带权平均周转时间：" + mTotalWeightTurnoverTime.divide(BigDecimal.valueOf(mJobList.size())));
    }

}
