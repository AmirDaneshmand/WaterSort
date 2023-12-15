import java.io.Console;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size;
        String[] colors;
        int maxBottleSize;

        String command;
        System.out.println("Please enter the number of colors");

        size = scanner.nextInt();
        colors = new String[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Please enter color number "+(i+1));
            colors[i] = scanner.next();
        }
        System.out.println("Please enter the size of your bottle");
        maxBottleSize = scanner.nextInt();

        System.out.println("Please type something to start");
        System.out.println("if you want to exit just type it");

        command = scanner.next().toLowerCase();
        if(command.equals("exit")){
            System.out.println(command);

            System.exit(1);
        }

        WaterSortGame waterSortGame = new WaterSortGame(colors,maxBottleSize);
        int input;
        while (!waterSortGame.hasWon()){
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("please enter number of operation that you want");
            System.out.println("1) select");
            System.out.println("2) deSelect");
            System.out.println("3) selectNext");
            System.out.println("4) selectPervious");
            System.out.println("5) pour");
            System.out.println("6) swap");
            System.out.println("7) replaceColor");
            System.out.println("8) addEmptyBottle");
            System.out.println("9) undo");
            System.out.println("10) redo");
            System.out.println();

            input = scanner.nextInt();

            System.out.println();
            System.out.println();
            System.out.println();



            switch (input){
                case 1:
                    System.out.println("enter number of bottle that you want to select");
                    System.out.println();
                    waterSortGame.select(scanner.nextInt());
                    break;
                case 2:
                    waterSortGame.deSelect();
                    break;
                case 3:
                    waterSortGame.selectNext();
                    break;
                case 4:
                    waterSortGame.selectPervious();
                    break;
                case 5:
                    System.out.println("enter number of bottle that you want to pour into it");
                    System.out.println();
                    waterSortGame.pour(scanner.nextInt());
                    break;
                case 6:
                    System.out.println("enter number of bottle that you want to swap");
                    System.out.println();
                    waterSortGame.swap(scanner.nextInt());
                    break;
                case 7:
                    System.out.println("enter the first && then the second one");
                    System.out.println();
                    waterSortGame.replaceColor(scanner.next(),scanner.next());
                    break;
                case 8:
                    waterSortGame.addEmptyBottle();
                    break;
                case 9:
                    waterSortGame.undo();
                    break;
                case 10:
                    waterSortGame.redo();
                    break;

            }


        }
        System.out.println("YOU winnnnnnnnnnnn!!!!!!!!!");
    }
}
