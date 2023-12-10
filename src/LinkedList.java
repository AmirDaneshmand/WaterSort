import javax.swing.*;

public class LinkedList {
    private Node firstNode;
    private int size;

    public LinkedList(){
        firstNode = null;
        size = 0;
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

    public String toString(int maxBottleSize){
        String str ="";
        int step = maxBottleSize - 1;
        while (step>-1){
            for (int i = 0; i < size ; i++) {
                String color =getNode(i).getStackNode().getColor(step);

                if(color == null){
                    str += "Empty     ";
                }else if(color =="Yellow"){
                    str += color+"  ";
                }else if(color =="Red"){
                    str += color+"     ";
                }else if(color =="Blue"){
                    str += color+"    ";
                }else{
                    str += color+"  ";
                }
            }
            step--;
            str +="\n";
        }

        return str;
    }
}
