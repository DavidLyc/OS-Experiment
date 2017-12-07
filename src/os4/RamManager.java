package os4;

import java.util.ArrayList;
import java.util.List;

public class RamManager {

    private List<Segment> segmentList;
    private int ramSize;

    RamManager() {
        segmentList = new ArrayList<>();
        ramSize = 0;
    }

    public void useSegment(int segmentID) {
        Segment useSeg = segmentList.get(segmentID);

    }

    public void addSegment(Segment segment) {
        segmentList.add(segment);
    }

    public List<Segment> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<Segment> segmentList) {
        this.segmentList = segmentList;
    }

    public int getRamSize() {
        return ramSize;
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }

}
