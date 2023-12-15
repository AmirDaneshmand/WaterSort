import java.util.Random;

public class WaterSortGame {
    private LinkedList linkedList;
    private String[] colors;
    private String[] chooseColor;
    private int maxBottleSize;
    private int selectBottle;
    private int firstPrint = 0;

    boolean usingExtraBottle = false;
    private int n;

    private boolean useUndo = false;

    private MethodStack methodStack;
    private MethodStack redoMethode;


    private int useUndoRedo = 0;

    public WaterSortGame(String[] colors , int maxBottleSize){
        this.methodStack = new MethodStack();
        this.redoMethode = new MethodStack();
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

    public void undo(){

        if(useUndoRedo>5){
            System.out.println("You use your chance");
            return;
        }

        if(methodStack.isEmpty()){
            System.out.println("You dont have any operation to undo");
            return;
        }

        MethodNode operateNode = methodStack.pop();
        String operateStr = operateNode.getOperation();
        redoMethode.push(operateNode);


        switch (operateStr){
            case "select":
                System.out.println("");
                System.out.println("select");
                System.out.println("");
                deSelect();
                redoMethode.push(operateNode);
                methodStack.pop();
                break;
            case "deSelect":
                System.out.println("");
                System.out.println("deSelect");
                System.out.println("");
                select(operateNode.getDeSelect());
                redoMethode.push(operateNode);
                methodStack.pop();
                break;
            case "selectNext":
                System.out.println("");
                System.out.println("selectNext");
                System.out.println("");
                selectPervious();
                redoMethode.push(operateNode);
                methodStack.pop();

                break;
            case "selectPervious":
                System.out.println("");
                System.out.println("selectPervious");
                System.out.println("");
                selectNext();
                redoMethode.push(operateNode);
                methodStack.pop();

                break;
            case "pour":
                System.out.println("");
                System.out.println("pour");
                System.out.println("");
                undoPour(operateNode);
                redoMethode.push(operateNode);
                methodStack.pop();
                break;
            case "swap":
                System.out.println("");
                System.out.println("swap");
                System.out.println("");
                deSelect();
                methodStack.pop();
                redoMethode.push(operateNode);
                select(operateNode.getSwapbottleNumber()+1);
                methodStack.pop();
                redoMethode.push(operateNode);
                swap(operateNode.getSwapSelect()+1);
                methodStack.pop();
                redoMethode.push(operateNode);
                deSelect();
                methodStack.pop();
                redoMethode.push(operateNode);
                select(operateNode.getSwapSelect()+1);
                methodStack.pop();
                redoMethode.push(operateNode);
                break;

            case "addEmptyBottle":
                System.out.println("");
                System.out.println("addEmptyBottle");
                System.out.println("");
                usingExtraBottle = false;
                Node p = linkedList.getNode(colors.length+1);
                p.setNextNode(null);
                linkedList.setAddBottle(false);
                linkedList.setSize(linkedList.getSize()-1);
                String str = linkedList.toString(maxBottleSize);
                System.out.println(str);
                break;

            case "replaceColor":
                System.out.println("");
                System.out.println("replaceColor");
                System.out.println("");
                undoReplaca(operateNode.getSecondColor(),operateNode.getFirstColor());
                break;
        }


        useUndo = true;
        useUndoRedo++;
    }

    public void redo(){

        if(!useUndo){
            methodStack.clean();
        }
        if(useUndoRedo>5){
            System.out.println("You use your chance");
            return;
        }
        if(redoMethode.isEmpty()){
            System.out.println("You dont have any operation to redo");
            return;
        }

        MethodNode redoNode = redoMethode.pop();
        String redoStr = redoNode.getOperation();

        switch (redoStr){
            case "select":
                System.out.println("");
                System.out.println("select redo");
                System.out.println("");
                select(redoNode.getDeSelect());
                break;
            case "deSelect":
                System.out.println("");
                System.out.println("deSelect redo");
                System.out.println("");
                deSelect();
                break;
            case "selectNext":
                System.out.println("");
                System.out.println("selectNext redo");
                System.out.println("");
                selectNext();
                break;
            case "selectPervious":
                System.out.println("");
                System.out.println("selectPervious redo");
                System.out.println("");
                selectPervious();
                break;
            case "pour":
                System.out.println("");
                System.out.println("pour redo");
                System.out.println("");
                pour(redoNode.getPourbottleNumber()+1);
                break;
            case "swap":
                System.out.println("");
                System.out.println("swap redo");
                System.out.println("");
                swap(redoNode.getSwapSelect()+1);
                break;

            case "addEmptyBottle":
                System.out.println("");
                System.out.println("addEmptyBottle redo");
                System.out.println("");
                addEmptyBottle();
                break;

            case "replaceColor":
                System.out.println("");
                System.out.println("replaceColor redo");
                System.out.println("");
                replaceColor(redoNode.getFirstColor(),redoNode.getSecondColor());
                break;


        }
        useUndoRedo++;
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

        MethodNode methodNode = new MethodNode("addEmptyBottle");
        methodStack.push(methodNode);

        String str = linkedList.toString(maxBottleSize);
        System.out.println(str);

        useUndo = false;
        redoMethode.pop();
    }
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

            MethodNode methodNode = new MethodNode("replaceColor");
            methodNode.setFirstColor(firstColor);
            methodNode.setSecondColor(secondColor);
            methodStack.push(methodNode);

            String str = linkedList.toString(maxBottleSize);
            System.out.println(str);
            redoMethode.pop();
            useUndo = false;
        }
    }
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

        MethodNode methodNode = new MethodNode("swap");
        methodNode.setSwapSelect(getSelectBottle());
        methodNode.setSwapbottleNumber(bottleNumber);
        methodStack.push(methodNode);

        linkedList.swap(selectBottle,bottleNumber);
        String str = linkedList.toString(maxBottleSize);
        System.out.println(str);



        redoMethode.pop();
        selectBottle = bottleNumber;
        useUndo = false;
    }

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

        MethodNode methodNode = new MethodNode("pour");

       do{
           linkedList.getNode(bottleNumber).getStackNode().push(takeColor);
           linkedList.getNode(getSelectBottle()).getStackNode().pop();
           giveColor = takeColor;
           takeColor = linkedList.getNode(getSelectBottle()).getStackNode().peek();
           methodNode.counterPour++;

       }while (!linkedList.getNode(bottleNumber).getStackNode().isFull() &&
               takeColor == giveColor);

        methodNode.setPourSelect(getSelectBottle());
        methodNode.setPourbottleNumber(bottleNumber);
        methodStack.push(methodNode);

       if(linkedList.getNode(getSelectBottle()).getStackNode().isEmpty()){
           deSelect();
       }
        String str = linkedList.toString(maxBottleSize);

        System.out.println(str);

        redoMethode.pop();


        useUndo = false;
        return true;

    }

    public boolean select(int bottleNumber){
        if(getSelectBottle()!=-1){
            System.out.println("something is selected");
            return false;
        }
        Node selectNode = linkedList.getNode(bottleNumber -1);
        if(selectNode.getStackNode().isCorrect() && !usingExtraBottle){
            System.out.println("The bottle is full");
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println();
            return false;
        }
        if(selectNode.getStackNode().isCorrect() && bottleNumber!=colors.length+1){
            System.out.println("The bottle is full!!!!!");
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
        if(bottleNumber>colors.length+1 && !usingExtraBottle){
            System.out.println("The bottle is not exist!!!!!");
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println();
            return false;
        }
         if(bottleNumber>colors.length +2  && usingExtraBottle){
            System.out.println("The bottle is not exist!!!!!");
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println();
            return false;
        }
        selectNode.setSelected(true);

        setSelectBottle(bottleNumber-1);
        String str = linkedList.toString(maxBottleSize);
        System.out.println(str);

        MethodNode methodNode = new MethodNode("select");
        methodStack.push(methodNode);

        redoMethode.pop();
        useUndo = false;
        return true;
    }

    public void deSelect(){
        if(getSelectBottle()==-1){
            return;
        }

        MethodNode methodNode = new MethodNode("deSelect");
        methodNode.setDeSelect(getSelectBottle());
        methodStack.push(methodNode);

        Node selectNode = linkedList.getNode(getSelectBottle());
        selectNode.setSelected(false);
        setSelectBottle(-1);

        if(firstPrint==0){
            String str = linkedList.toString(maxBottleSize);
            System.out.println(str);
        }
        firstPrint=0;
        useUndo = false;
        redoMethode.pop();
    }

    public void selectNext(){
        if(getSelectBottle()==-1){
            return;
        }

        int select = getSelectBottle();

        MethodNode methodNode = new MethodNode("selectNext");
        methodNode.setSelectNext(select);
        methodStack.push(methodNode);

        firstPrint = 1;
        redoMethode.push(new MethodNode("empty"));
        deSelect();

        methodStack.pop();

        if(!usingExtraBottle){


            if(select==colors.length){
                select = 0;
                System.out.println("end");
            }else{
                select++;


            }
        }else {

            if(select==colors.length +1 ){
                select = 0;
                System.out.println("end");
            }else{
                select++;


            }
        }


        if(!select(select +1)){
            methodStack.pop();
        }

        methodStack.pop();
        //System.out.println(getSelectBottle() + "r2ftwg");

        useUndo = false;
        redoMethode.pop();

    }

    public void selectPervious(){
        if(getSelectBottle()==-1){
            return;
        }
        int select = getSelectBottle();

        MethodNode methodNode = new MethodNode("selectPervious");
        methodNode.setSelectPervious(select);
        methodStack.push(methodNode);

        firstPrint = 1;
        redoMethode.push(new MethodNode("empty"));
        deSelect();
        methodStack.pop();



        if(!usingExtraBottle){
            if(select == 0){
                select = colors.length;
            }
            else{
                select --;
            }
        }else{
            if(select == 0){
                select = colors.length+1;
            }
            else{
                select --;
            }
        }

        if(!select(select +1)){
            methodStack.pop();
        }

        methodStack.pop();
        useUndo = false;
        redoMethode.pop();
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






    private void undoReplaca(String firstColor , String secondColor){

        Node changeColorNode =linkedList.getNode(0);
        Stack colorStack;
        String findColor;
        do {
            colorStack = changeColorNode.getStackNode();
            for (int i = 0; i < maxBottleSize ; i++) {
                findColor = colorStack.getColor(i);
                if(findColor==firstColor){
                    colorStack.setColor(secondColor,i);

                    // System.out.println(colorStack.getColor(i));
                }
                if(findColor==null){
                    break;
                }
            }
            changeColorNode = changeColorNode.getNextNode();
        }while (changeColorNode!= null);


            String str = linkedList.toString(maxBottleSize);
            System.out.println(str);



    }
    private void undoPour(MethodNode pourNode){
        int select = pourNode.getPourbottleNumber();
        int bottleNumber = pourNode.getPourSelect();
        int counter = pourNode.counterPour;



        String takeColor = linkedList.getNode(select).getStackNode().peek();

        for (int i = 0; i < counter; i++) {
            linkedList.getNode(bottleNumber).getStackNode().push(takeColor);
            linkedList.getNode(select).getStackNode().pop();
            takeColor = linkedList.getNode(select).getStackNode().peek();
        }

        deSelect();
        methodStack.pop();
        select(bottleNumber+1);
        methodStack.pop();

        String str = linkedList.toString(maxBottleSize);
        System.out.println(str);
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
            if(hasWon()){

                n = colors.length*maxBottleSize;

                linkedList = new LinkedList();
                display();
            }
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
