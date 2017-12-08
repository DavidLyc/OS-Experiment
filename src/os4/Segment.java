package os4;

public class Segment {

    private int processID;
    private int segmentID;      //段号
    private int start;    //始地址
    private int length;   //段长

    Segment(int processID) {
        this.processID = processID;
    }

    Segment(int start, int length) {
        this.start = start;
        this.length = length;
    }

    public int getSegmentID() {
        return segmentID;
    }

    public void setSegmentID(int segmentID) {
        this.segmentID = segmentID;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getProcessID() {
        return processID;
    }

}
