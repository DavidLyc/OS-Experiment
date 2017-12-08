package os4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Manager {

    private List<Segment> useSegList;
    private int ramSize;
    private List<Segment> freeList;
    private List<Segment> segmentList;

    Manager() {
        useSegList = new ArrayList<>();
        freeList = new ArrayList<>();
        segmentList = new ArrayList<>();
        ramSize = 0;
    }

    //产生可用表
    private void generateFreeList() {
        freeList.clear();
        if (useSegList.size() == 0) {
            freeList.add(new Segment(0, ramSize));
        } else {
            int start = 0;
            useSegList.sort(Comparator.comparingInt(Segment::getStart));
            for (Segment segment : useSegList) {
                if (segment.getStart() > start) {
                    Segment freeSeg = new Segment(start, segment.getStart() - start);
                    freeList.add(freeSeg);
                }
                start = segment.getStart() + segment.getLength();
            }
            //计算最后一个空闲区
            if (start <= ramSize - 1) {
                freeList.add(new Segment(start, ramSize - start));
            }
        }
    }

    public void showRamInfo() {
        System.out.println("内存大小：" + ramSize);
        System.out.println("内存中的工作段数：" + useSegList.size());
        for (Segment useSeg : useSegList) {
            System.out.println("段" + useSeg.getSegmentID() + "   起始地址：" + useSeg.getStart() + "\t段长：" + useSeg.getLength());
        }
        generateFreeList();
        System.out.println("内存中的空闲段数：" + freeList.size());
        for (int i = 0; i < freeList.size(); i++) {
            Segment freeSeg = freeList.get(i);
            System.out.println("空闲区" + i + "\t起始地址：" + freeSeg.getStart() + "\t内存大小：" + freeSeg.getLength());
        }
    }

    public void useProcess(int processID) {
        for (Segment seg : segmentList) {
            if (seg.getProcessID() == processID) {
                useSegment(processID, seg);
            }
        }
    }

    private void useSegment(int processID, Segment seg) {
        if (ramSize >= seg.getLength()) {
            //有足够空闲区
            //初始化可用表
            generateFreeList();
            //按照空闲区大小升序排列
            freeList.sort(Comparator.comparingInt(Segment::getLength));
            //使用最佳适应法
            for (Segment freeSeg : freeList) {
                if (freeSeg.getLength() >= seg.getLength()) {
                    seg.setStart(freeSeg.getStart());
                    useSegList.add(seg);
                    return;
                }
            }
            //没有足够空闲区域
            //使用FIFO淘汰算法(选择在内存驻留时间最长的页将其淘汰)
            int i;
            for (i = 0; i < useSegList.size(); i++) {
                if (processID != useSegList.get(i).getProcessID()) {
                    break;
                }
            }
            System.out.println("已回收进程" + useSegList.get(i).getProcessID() + "的段" + useSegList.get(i).getSegmentID());
            useSegList.remove(i);
            //递归调用程序
            useSegment(processID, seg);
        } else {
            System.out.println("调用进程的内存过大！");
        }
    }

    //回收进程
    public void recycleProcess(int processID) {
        List<Segment> recycleList = new ArrayList<>();
        for (Segment segment : useSegList) {
            if (segment.getProcessID() == processID) {
                recycleList.add(segment);
                System.out.println("已回收进程" + processID + "的段" + segment.getSegmentID());
            }
        }
        useSegList.removeAll(recycleList);
    }

    //创建段
    public void addSegment(Segment segment) {
        segmentList.add(segment);
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }

}
