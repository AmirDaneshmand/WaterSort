public class Main {
    public static void main(String[] args) {
        String[] colors = {"Yellow" , "Red" , "Blue"};
        WaterSortGame waterSortGame = new WaterSortGame(colors,4 );

        waterSortGame.select(2);
        waterSortGame.selectNext();
        //waterSortGame.selectNext();
        waterSortGame.selectPervious();
        waterSortGame.selectPervious();
        //waterSortGame.selectNext();
        waterSortGame.pour(4);
        //waterSortGame.undo();
        waterSortGame.swap(2);


        waterSortGame.replaceColor("Red","Green");
        //waterSortGame.undo();

        waterSortGame.addEmptyBottle();
        waterSortGame.pour(5);
       // waterSortGame.selectNext();waterSortGame.selectNext();waterSortGame.selectNext();waterSortGame.selectNext();waterSortGame.selectNext();
        //waterSortGame.undo();

    }
}
