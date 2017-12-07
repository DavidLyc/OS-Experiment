package os4;

import java.util.List;
import java.util.Scanner;

public class Main {

    /*
    段式管理把二维虚拟地址空间设计成设计成段号S与段内相对地址W
    段式虚拟地址空间包括：段号S：段内地址W
      每个段定义一组逻辑上完整的程序或数据
      段号之间无顺序关系
      段长不固定（根据需要，段长可动态增长）
     */
    private static RamManager manager;
    private static Scanner scanner;
    private static final String inputTip = "请输入初始内存的大小：";
    private static final String menuTip = "1.创建段  2.调用段  3.显示内存信息  4.退出";
    private static final String createSegTip = "请输入段的大小：";
    private static final String useSegTip = "请输入的调用段的段号：";
    private static final String errorTip = "菜单输入有误,请重新输入！";

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        manager = new RamManager();
        int choose;
        System.out.println(inputTip);
        manager.setRamSize(scanner.nextInt());
        while (true) {
            System.out.println(menuTip);
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    doCreateSegment();
                    break;
                case 2:
                    showRamInfo();
                    break;
                case 3:
                    useSegment();
                    break;
                case 4:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println(errorTip);
                    break;
            }
        }
    }

    //创建新的段
    private static void doCreateSegment() {
        System.out.println(createSegTip);
        Segment segment = new Segment();
        segment.setLength(scanner.nextInt());
        manager.addSegment(segment);
    }

    //显示内存信息
    private static void showRamInfo() {
        List<Segment> segmentList = manager.getSegmentList();
        System.out.println("内存大小：" + manager.getRamSize());
        System.out.println("内存中的段数" + segmentList.size());
        for (int i = 0; i < segmentList.size(); i++) {
            Segment segment = segmentList.get(i);
            System.out.println("段" + i + "\t起始地址：" + segment.getStart() + "\t段长：" + segment.getLength());
        }

    }

    //调用段
    private static void useSegment() {
        System.out.println(useSegTip);
        manager.useSegment(scanner.nextInt());
    }

}
