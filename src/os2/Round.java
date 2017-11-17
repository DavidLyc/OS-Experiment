package os2;

public class Round {

    public static void main(String[] args) {
        TableManager1 manager1 = new TableManager1();
        TableManager2 manager2 = new TableManager2();
        TableManager3 manager3 = new TableManager3();
        for (int i = 1; i <= 5; i++) {
            new People(String.valueOf(i), manager1).start();
        }
    }

}
