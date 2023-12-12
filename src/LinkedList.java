import javax.swing.*;

public class LinkedList {
    private Node firstNode;
    private int size;
    private boolean addBottle = false;

    public LinkedList(){
        firstNode = null;
        size = 0;
    }

    public Node getNode(int index){
        if(isEmpty()){
            return null;
        }
        if(index>=size){
            return null;
        }
        Node p = firstNode;
        for (int i = 0; i < index; i++) {
            p = p.getNextNode();
        }
        return p;
    }
    public void swap(int selectBottle,int bottleNumber){
        Node swapNode;
        Node swapNodePer = firstNode;
        Node selectBo;
        Node selectBoPerv = firstNode;

        if(selectBottle==0){
            if(bottleNumber==1){
                selectBo = firstNode;
                swapNode = selectBo.getNextNode();
                selectBo.setNextNode(swapNode.getNextNode());
                swapNode.setNextNode(selectBo);
                firstNode = swapNode;
                return;
            }

            for (int i = 0; i < bottleNumber -1; i++) {
                swapNodePer = swapNodePer.getNextNode();
            }
            swapNode = swapNodePer.getNextNode();
            selectBo = firstNode;
            Node p = selectBo.getNextNode();
            selectBo.setNextNode(swapNode.getNextNode());
            swapNode.setNextNode(p);
            swapNodePer.setNextNode(selectBo);
            firstNode = swapNode;
            return;
        }

        if(bottleNumber==0){
            if(selectBottle==1){
                swapNode =firstNode;
                selectBo = swapNode.getNextNode();
                swapNode.setNextNode(selectBo.getNextNode());
                selectBo.setNextNode(swapNode);
                firstNode = selectBo;
                return;
            }
            for (int i = 0; i < selectBottle -1; i++) {
                selectBoPerv = selectBoPerv.getNextNode();
            }
            selectBo = selectBoPerv.getNextNode();
            swapNode = firstNode;
            Node p = swapNode.getNextNode();
            swapNode.setNextNode(selectBo.getNextNode());
            selectBoPerv.setNextNode(swapNode);
            selectBo.setNextNode(p);
            firstNode = selectBo;
            return;
        }

        if(selectBottle - bottleNumber ==1){
            swapNodePer = getNode(bottleNumber-1);
            swapNode = swapNodePer.getNextNode();

            selectBo = getNode(selectBottle);
            Node p = selectBo.getNextNode();

            swapNodePer.setNextNode(selectBo);
            swapNode.setNextNode(p);
            selectBo.setNextNode(swapNode);
            return;
        }
        if(bottleNumber - selectBottle==1){
            selectBoPerv = getNode(selectBottle-1);
            selectBo = selectBoPerv.getNextNode();

            swapNode = getNode(bottleNumber);
            Node p = swapNode.getNextNode();

            selectBoPerv.setNextNode(swapNode);
            selectBo.setNextNode(p);
            swapNode.setNextNode(selectBo);
            return;
        }

        if(bottleNumber>selectBottle){
            swapNodePer = getNode(bottleNumber - 1);
            swapNode = swapNodePer.getNextNode();

            selectBoPerv = getNode(selectBottle-1);
            selectBo = selectBoPerv.getNextNode();
            Node p = selectBo.getNextNode();

            selectBo.setNextNode(swapNode.getNextNode());
            swapNodePer.setNextNode(selectBo);
            selectBoPerv.setNextNode(swapNode);
            swapNode.setNextNode(p);
            return;
        }
        if(selectBottle>bottleNumber){
            swapNodePer = getNode(bottleNumber - 1);
            swapNode = swapNodePer.getNextNode();
            Node p = swapNode.getNextNode();

            selectBoPerv = getNode(selectBottle-1);
            selectBo = selectBoPerv.getNextNode();


            swapNodePer.setNextNode(selectBo);
            swapNode.setNextNode(selectBo.getNextNode());
            selectBoPerv.setNextNode(swapNode);
            selectBo.setNextNode(p);
            return;
        }
    }
    public boolean isEmpty(){

        return (firstNode==null);
    }

    public void addLink(Node addNode){
        size ++;
        if(isEmpty()){
            firstNode = addNode;
            return;
        }

        Node p = firstNode;
        while (p.getNextNode()!=null){
         //   System.out.println(p==null);
            p = p.getNextNode();
        }
       // System.out.println(p==null);
        p.setNextNode(addNode);
    }



    public String toString(int maxBottleSize){
        String str ="";
        int step = maxBottleSize - 1;
        if(!isAddBottle()){
            while (step>-1){
                for (int i = 0; i < size ; i++) {
                    String color =getNode(i).getStackNode().getColor(step);

                    if(color == null){
                        str += "Empty    ";
                    }else if(color =="Yellow"){
                        str += color+"   ";
                    }else if(color =="Red"){
                        str += color+"      ";
                    }else if(color =="Blue"){
                        str += color+"     ";
                    }else{
                        str += color+"   ";
                    }
                }
                step--;
                str +="\n";
            }
        }
        else{
            while (step>-1){
//                System.out.println("ATTT"+step);
//                System.out.println("(size/2)+(size%2)"+(size/2));

                //System.out.println(size/2+"  sizeee   ");

                   // System.out.println("ATTT"+step);
                    for (int i = 0; i < size-1 ; i++) {
                        String color =getNode(i).getStackNode().getColor(step);

                        if(color == null){
                            str += "Empty    ";
                        }else if(color =="Yellow"){
                            str += color+"   ";
                        }else if(color =="Red"){
                            str += color+"      ";
                        }else if(color =="Blue"){
                            str += color+"     ";
                        }else{
                            str += color+"   ";
                        }
                    }

                    if(maxBottleSize/2 >step ){
                        String color =getNode(size - 1).getStackNode().getColor(step);

                        if(color == null){
                            str += "Empty    ";
                        }else if(color =="Yellow"){
                            str += color+"   ";
                        }else if(color =="Red"){
                            str += color+"      ";
                        }else if(color =="Blue"){
                            str += color+"     ";
                        }else{
                            str += color+"   ";
                        }
                    }



                step--;
                str +="\n";
            }

        }

        for (int i = 0; i < size ; i++) {
            if(getNode(i).isSelected()){
                str += "---#--- ";
            }
            else{
                str += "         ";
            }
        }
        str +="\n";
        str +="-----------------------------------------------";
        return str;
    }

    public boolean isAddBottle() {
        return addBottle;
    }

    public void setAddBottle(boolean addBottle) {
        this.addBottle = addBottle;
    }
}
