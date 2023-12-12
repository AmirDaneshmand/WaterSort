import java.util.Random;

public class WaterSortGame {
    private LinkedList linkedList;
    private String[] colors;
    private String[] chooseColor;
    private int maxBottleSize;
    private int selectBottle;
    private int firstPrint = 0;
    private int beforSelect;

    boolean selectFun = false;
    boolean selectPervFun = false;
    boolean selectNextFun = false;
    boolean deselectFun = false;
    boolean addEmptyFun = false;
    boolean replaceColorFun = false;
    boolean swapFun = false;
    boolean pourFun = false;

    boolean usingExtraBottle = false;
    private int n;

    public WaterSortGame(String[] colors , int maxBottleSize){
        this.colors = colors;
        this.maxBottleSize = maxBottleSize;
        linkedList = new LinkedList();
        this.setSelectBottle(-1);
        n = colors.length*maxBottleSize;

        chooseColor = new String[n];
        int count = 0;
        for (String color : colors) {
            for (int j = 0; j < maxBottleSize; j++) {
                chooseColor[count + j] = color;
            }
            count += maxBottleSize;
        }

        display();
        String str = linkedList.toString(maxBottleSize);
        System.out.println(str);
    }

    public void addEmptyBottle(){
        if(usingExtraBottle){
            System.out.println("you use your chance to add an empty Bottle");
            return;
        }
        usingExtraBottle = true;

        Stack emptyStack = new Stack((maxBottleSize/2));
        Node emptyNode = new Node(emptyStack);
        linkedList.addLink(emptyNode);
        linkedList.setAddBottle(true);
        String str = linkedList.toString(maxBottleSize);
        System.out.println(str);
        setTrue("addEmptyFun");
    }

    String firstColor;
    String secondColor;
    public void replaceColor(String firstColor , String secondColor){
        boolean secondHas = false;
        boolean firstHas = false;

        boolean changing = false;
        for (String color : colors) {
            if (secondColor == color) {
                secondHas = true;
                break;
            }
        }
        if(secondHas){
            System.out.println("second color is exist");
            return;
        }
        for (String color : colors) {
            if (firstColor == color) {
                firstHas = true;
                break;
            }
        }
        if(!firstHas){
            System.out.println("first color is not exist");
            return;
        }

        Node changeColorNode =linkedList.getNode(0);
        Stack colorStack;
        String findColor;
        do {
            colorStack = changeColorNode.getStackNode();
            for (int i = 0; i < maxBottleSize ; i++) {
                findColor = colorStack.getColor(i);
                if(findColor==firstColor){
                    colorStack.setColor(secondColor,i);
                    changing = true;
                   // System.out.println(colorStack.getColor(i));
                }
                if(findColor==null){
                    break;
                }
            }
            changeColorNode = changeColorNode.getNextNode();
        }while (changeColorNode!= null);

        if(changing){
            String str = linkedList.toString(maxBottleSize);
            System.out.println(str);
            setTrue("replaceColorFun");
            this.firstColor = firstColor;
            this.secondColor = secondColor;
        }

    }
    private int firstSwap;
    private int secondSwap;
    public void swap(int bottleNumber){
        bottleNumber--;
       if(usingExtraBottle){
           if(selectBottle == colors.length+1 || bottleNumber == colors.length+1){
               System.out.println("cant swap the extra bottle that add");
               return;
           }
       }

        if(getSelectBottle()==-1){
            System.out.println("nothing is selected");
            return;
        }
        if(getSelectBottle()==bottleNumber){
            System.out.println("cant swap with itself");
            return;
        }
        if(bottleNumber>=colors.length){
            System.out.println("bottle is not found");
            return;
        }

        linkedList.swap(selectBottle,bottleNumber);
        String str = linkedList.toString(maxBottleSize);
        System.out.println(str);

        selectBottle = bottleNumber;
        setTrue("swapFun");
        firstSwap = getSelectBottle();
        secondSwap = bottleNumber;
    }


    private int giverPour;
    private int takerPour;
    public boolean pour(int bottleNumber){
        bottleNumber--;
        if(getSelectBottle()==-1){
            System.out.println("nothing is selected");
            return false;
        }

        if(linkedList.getNode(bottleNumber).isSelected()){
            System.out.println("can not pour something to itself");
            return false;
        }

        if(linkedList.getNode(bottleNumber).getStackNode().isFull()){
            System.out.println("the bottle is full");
            return false;
        }

        String takeColor = linkedList.getNode(getSelectBottle()).getStackNode().peek();
        String giveColor = linkedList.getNode(bottleNumber).getStackNode().peek();

        if (takeColor != giveColor && giveColor!=null){
            System.out.println("can not pour because the colors are not the same");
            return false;
        }

       do{
           linkedList.getNode(bottleNumber).getStackNode().push(takeColor);
           linkedList.getNode(getSelectBottle()).getStackNode().pop();
           giveColor = takeColor;
           takeColor = linkedList.getNode(getSelectBottle()).getStackNode().peek();


       }while (!linkedList.getNode(bottleNumber).getStackNode().isFull() &&
               takeColor == giveColor);

       if(linkedList.getNode(getSelectBottle()).getStackNode().isEmpty()){
           deSelect();
       }
        String str = linkedList.toString(maxBottleSize);
        System.out.println(str);
        setTrue("pourFun");

        giverPour = getSelectBottle() ;
        takerPour = bottleNumber;
        return true;
    }

    public boolean select(int bottleNumber){
        if(getSelectBottle()!=-1){
            System.out.println("something is selected");
            return false;
        }
        Node selectNode = linkedList.getNode(bottleNumber -1);
        if(selectNode.getStackNode().isCorrect()){
            System.out.println("The bottle is full");
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println();
            return false;
        }
        if(selectNode.getStackNode().isEmpty()){
            System.out.println("The bottle is empty!!!!!");
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println();
            return false;
        }
        selectNode.setSelected(true);

        setSelectBottle(bottleNumber-1);
        String str = linkedList.toString(maxBottleSize);
        System.out.println(str);
        setTrue("selectFun");
        return true;
    }

    public void deSelect(){
        if(getSelectBottle()==-1){
            return;
        }
        beforSelect = getSelectBottle()+1;
        Node selectNode = linkedList.getNode(getSelectBottle());
        selectNode.setSelected(false);
        setSelectBottle(-1);

        if(firstPrint==0){
            String str = linkedList.toString(maxBottleSize);
            System.out.println(str);
        }
        firstPrint=0;
        setTrue("deselectFun");
    }

    public void selectNext(){
        if(getSelectBottle()==-1){
            return;
        }

        int select = getSelectBottle();

        if(!usingExtraBottle){
            firstPrint = 1;
            deSelect();
            if(select==colors.length){
                select = 0;
                System.out.println("end");
            }else{
                select++;


            }
        }else {

            firstPrint = 1;
            deSelect();
            if(select==colors.length +1 ){
                select = 0;
                System.out.println("end");
            }else{
                select++;


            }
        }


        select(select +1);
        setTrue("selectNextFun");
        //System.out.println(getSelectBottle() + "r2ftwg");


    }

    public void selectPervious(){
        if(getSelectBottle()==-1){
            return;
        }
        int select = getSelectBottle();
        firstPrint = 1;
        deSelect();

        if(!usingExtraBottle){
            if(select == 0){
                select = colors.length - 1;
            }
            else{
                select --;
            }
        }else{
            if(select == 0){
                select = colors.length;
            }
            else{
                select --;
            }
        }

        select(select + 1);

        setTrue("selectPervFun");

    }

    public boolean hasWon(){
        Node check = linkedList.getNode(0);
        boolean win = true;
        while (check!=null){
            if(check.getStackNode().isCorrect() || check.getStackNode().isEmpty()){
                check = check.getNextNode();
                continue;
            }
            win = false;
            break;
        }
        return win;
    }



    public void undo(){
        if(selectFun){
            deSelect();
            selectFun = false;

        }
        if(deselectFun){
            select(beforSelect);
            deselectFun = false;

        }
        if(selectNextFun){
            selectPervious();
            selectNextFun = false;

        }
        if(selectPervFun){
            selectNext();
            selectPervFun = false;

        }
        if(pourFun){
            deSelect();
            select(takerPour+1);
            pour(giverPour+1);
            deSelect();
            select(giverPour);
            pourFun = false;

        }
        if (swapFun){
            deSelect();
            select(secondSwap);
            swap(firstSwap+1);
            deSelect();
            select(firstSwap);
            swapFun = false;

        }

//        if(replaceColorFun){
//            System.out.println("first color is " +secondColor);
//            System.out.println("second color is " +firstColor);
//            System.out.println();
//            replaceColor(secondColor,firstColor);
//            replaceColorFun = false;
//
//        }

        if(addEmptyFun){
            usingExtraBottle = false;
            Node p = linkedList.getNode(colors.length);
            p.setNextNode(null);
            addEmptyFun = false;

        }
    }



    private void setTrue(String function){
        selectFun = false;
        selectPervFun = false;
        selectNextFun = false;
        deselectFun = false;
        addEmptyFun = false;
        replaceColorFun = false;
        swapFun = false;
        pourFun = false;
        switch (function) {
            case "selectFun" -> selectFun = true;
            case "selectPervFun" -> selectPervFun = true;
            case "selectNextFun" -> selectNextFun = true;
            case "deselectFun" -> deselectFun = true;
            case "addEmptyFun" -> addEmptyFun = true;
            case "replaceColorFun" -> replaceColorFun = true;
            case "swapFun" -> swapFun = true;
            case "pourFun" -> pourFun = true;
        }
    }


    private void sort(int index , String[] array){
        for (int i = index; i < array.length -1; i++) {
            array[i] = array[i+1];

        }
        array[array.length-1] = null;
    }

    private void display(){
        Random random = new Random();
        Node node;
        Stack stack;
        for (int i = 0; i < colors.length; i++) {
            stack = new Stack(maxBottleSize);
            for (int j = 0; j < maxBottleSize; j++) {
                int num = random.nextInt(n);
                stack.push(chooseColor[num]);
                //System.out.println(chooseColor[num]);
                sort(num,chooseColor);
                n--;
            }
            node = new Node(stack);
            linkedList.addLink(node);
        }
        Stack stack1 = new Stack(maxBottleSize);
        node = new Node(stack1);
        linkedList.addLink(node);
    }

    public int getSelectBottle() {

        return selectBottle;
    }

    public void setSelectBottle(int selectBottle) {

        this.selectBottle = selectBottle;
    }
}
