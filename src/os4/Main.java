package os4;

import java.util.Scanner;

public class Main {
    /*
    段式管理把二维虚拟地址空间设计成设计成段号S与段内相对地址W
    段式虚拟地址空间包括：段号S：段内地址W
      每个段定义一组逻辑上完整的程序或数据
      段号之间无顺序关系
      段长不固定（根据需要，段长可动态增长）
     */
    private static Manager manager;
    private static Scanner scanner;
    private static final String inputTip = "请输入初始内存的大小：";
    private static final String menuTip = "1.创建进程  2.调用进程  3.回收进程 4.显示内存信息 5.退出";
    private static final String createProcessTip = "请输入进程号：";
    private static final String useSegTip = "请输入的调用进程的进程号：";
    private static final String recycleTip = "请输入回收进程的进程号";
    private static final String errorTip = "菜单输入有误,请重新输入！";

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        manager = new Manager();
        int choose;
        System.out.println(inputTip);
        manager.setRamSize(scanner.nextInt());
        while (true) {
            System.out.println(menuTip);
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    createProcess();
                    break;
                case 2:
                    useProcess();
                    break;
                case 3:
                    recycleProcess();
                    break;
                case 4:
                    manager.showRamInfo();
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println(errorTip);
                    break;
            }
        }
    }

    //回收
    private static void recycleProcess() {
        System.out.println(recycleTip);
        manager.recycleProcess(scanner.nextInt());
    }

    //创建新进程
    private static void createProcess() {
        System.out.println(createProcessTip);
        int processID = scanner.nextInt();
        while (true) {
            Segment segment = new Segment(processID);
            System.out.println("请输入段号：(输入-1结束)");
            segment.setSegmentID(scanner.nextInt());
            if (segment.getSegmentID() == -1) {
                return;
            }
            System.out.println("请输入内存大小：");
            segment.setLength(scanner.nextInt());
            manager.addSegment(segment);
        }
    }

    //调用进程
    private static void useProcess() {
        System.out.println(useSegTip);
        manager.useProcess(scanner.nextInt());
    }

}
