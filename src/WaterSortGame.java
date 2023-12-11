import java.util.Random;

public class WaterSortGame {
    private LinkedList linkedList;
    private String[] colors;
    private String[] chooseColor;
    private int maxBottleSize;
    private int selectBottle;

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
               takeColor == giveColor

       );

       if(linkedList.getNode(getSelectBottle()).getStackNode().isEmpty()){
           deSelect();
       }
        String str = linkedList.toString(maxBottleSize);
        System.out.println(str);
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
        return true;
    }

    public void deSelect(){
        if(getSelectBottle()==-1){
            return;
        }
        Node selectNode = linkedList.getNode(getSelectBottle());
        selectNode.setSelected(false);
        setSelectBottle(-1);

        String str = linkedList.toString(maxBottleSize);
        System.out.println(str);
    }

    public void selectNext(){
        if(getSelectBottle()==-1){
            return;
        }

        int select = getSelectBottle();
        deSelect();
        if(select==colors.length){
            select = 0;
            System.out.println("end");
        }else{
           select++;


        }

        select(select +1);

        //System.out.println(getSelectBottle() + "r2ftwg");


    }

    public void selectPervious(){
        if(getSelectBottle()==-1){
            return;
        }

        deSelect();
        if(getSelectBottle()==1){
            setSelectBottle(colors.length);
        }
        else{
            setSelectBottle(selectBottle--);
        }
        select(getSelectBottle());


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
