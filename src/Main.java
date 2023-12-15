public class Main {
    public static void main(String[] args) {
        String[] colors = {"Yellow" , "Red" , "Blue"};
        WaterSortGame waterSortGame = new WaterSortGame(colors,4 );

        waterSortGame.select(2);
        System.out.println("****************************************************************************");
        System.out.println("select");
        System.out.println("****************************************************************************");

        waterSortGame.selectNext();
        System.out.println("****************************************************************************");
        System.out.println("selectNext");
        System.out.println("****************************************************************************");

        waterSortGame.undo();
        System.out.println("****************************************************************************");
        System.out.println("undo");
        System.out.println("****************************************************************************");

        //waterSortGame.selectNext();
        waterSortGame.selectPervious();
        System.out.println("****************************************************************************");
        System.out.println("selectPervious");
        System.out.println("****************************************************************************");




        waterSortGame.undo();
        System.out.println("****************************************************************************");
        System.out.println("undo");
        System.out.println("****************************************************************************");


        waterSortGame.undo();
        System.out.println("****************************************************************************");
        System.out.println("undo");
        System.out.println("****************************************************************************");

        //waterSortGame.selectNext();
//        waterSortGame.pour(4);
//        System.out.println("****************************************************************************");
//        System.out.println("pour");
//        System.out.println("****************************************************************************");
//
//        waterSortGame.undo();
//        System.out.println("****************************************************************************");
//        System.out.println("undo");
//        System.out.println("****************************************************************************");
//
//        waterSortGame.pour(4);
//        System.out.println("****************************************************************************");
//        System.out.println("pour");
//        System.out.println("****************************************************************************");
//
//        //waterSortGame.undo();
//        waterSortGame.swap(2);
//        System.out.println("****************************************************************************");
//        System.out.println("swap");
//        System.out.println("****************************************************************************");
//
//        waterSortGame.undo();
//        System.out.println("****************************************************************************");
//        System.out.println("undo");
//        System.out.println("****************************************************************************");
//
//        waterSortGame.swap(2);
//        System.out.println("****************************************************************************");
//        System.out.println("swap");
//        System.out.println("****************************************************************************");



//        waterSortGame.replaceColor("Red","Green");
//        //waterSortGame.undo();
//
//        waterSortGame.addEmptyBottle();
//        waterSortGame.pour(5);
       // waterSortGame.selectNext();waterSortGame.selectNext();waterSortGame.selectNext();waterSortGame.selectNext();waterSortGame.selectNext();
        //waterSortGame.undo();

    }
}
