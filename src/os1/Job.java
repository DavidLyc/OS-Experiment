package os1;

import java.math.BigDecimal;

public class Job {

    private String jobName;
    private BigDecimal submitTime;
    private BigDecimal runTime;
    private BigDecimal startTime;
    private BigDecimal finishTime;
    private BigDecimal turnoverTime;
    private BigDecimal weightTurnoverTime;
    private int sequence;
    private Boolean isRunned;
    //hrn算法
    private BigDecimal react;
    private BigDecimal waitTime;
    //rr
    private BigDecimal tempRunTime;

    Job(String jobName, BigDecimal submitTime, BigDecimal runTime) {
        this.jobName = jobName;
        this.submitTime = submitTime;
        this.runTime = runTime;
        this.isRunned = false;
        waitTime = new BigDecimal(0);
        this.tempRunTime = runTime;
    }

    public BigDecimal getTempRunTime() {
        return tempRunTime;
    }

    public void setTempRunTime(BigDecimal tempRunTime) {
        this.tempRunTime = tempRunTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public BigDecimal getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(BigDecimal submitTime) {
        this.submitTime = submitTime;
    }

    public BigDecimal getRunTime() {
        return runTime;
    }

    public void setRunTime(BigDecimal runTime) {
        this.runTime = runTime;
    }

    public BigDecimal getStartTime() {
        return startTime;
    }

    public void setStartTime(BigDecimal startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(BigDecimal finishTime) {
        this.finishTime = finishTime;
    }

    public BigDecimal getTurnoverTime() {
        return turnoverTime;
    }

    public void setTurnoverTime(BigDecimal turnoverTime) {
        this.turnoverTime = turnoverTime;
    }

    public BigDecimal getWeightTurnoverTime() {
        return weightTurnoverTime;
    }

    public void setWeightTurnoverTime(BigDecimal weightTurnoverTime) {
        this.weightTurnoverTime = weightTurnoverTime;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Boolean getRunned() {
        return isRunned;
    }

    public void setRunned(Boolean runned) {
        isRunned = runned;
    }

    public BigDecimal getReact() {
        return react;
    }

    public void setReact(BigDecimal react) {
        this.react = react;
    }

    public BigDecimal getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(BigDecimal waitTime) {
        this.waitTime = waitTime;
    }
}
