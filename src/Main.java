public class Main {
    public static void main(String[] args) {
        String[] colors = {"Yellow" , "Red" , "Blue"};
        WaterSortGame waterSortGame = new WaterSortGame(colors,3);

        waterSortGame.select(2);
        waterSortGame.selectNext();
        //waterSortGame.selectNext();
        waterSortGame.selectPervious();
        waterSortGame.selectPervious();
        //waterSortGame.selectNext();
        waterSortGame.pour(4);
        waterSortGame.swap(2);
        waterSortGame.replaceColor("Red","Green");
    }
}
