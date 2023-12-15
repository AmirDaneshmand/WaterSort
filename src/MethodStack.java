

public class MethodStack {
    private MethodNode first;
    private MethodNode last;
    private int size;

    public MethodStack(){
        setFirst(null);
        setLast(null);
        setSize(0);
    }

    public void push(MethodNode newNode){
        if(size ==0){
            first = newNode;
            last = newNode;
            size++;
            return;
        }
        size++;
        MethodNode p = last;
        p.setNextNode(newNode);
        last = newNode;
    }

    public boolean isEmpty(){
        return (size==0);
    }

    public MethodNode peek(){
        if(isEmpty()){
           // System.out.println("You dont have any operation to undo");
            return null;
        }
        return last;
    }

    public MethodNode pop(){
        if(isEmpty()){
            //System.out.println("You dont have any operation to undo");
            return null;
        }
        size--;

        if(size==0){
            MethodNode p = first;
            first = last = null;
            return p;
        }
        MethodNode p = first;
        for (int i = 0; i < size - 1; i++) {
            p = p.getNextNode();
        }
        MethodNode str = p.getNextNode();
        p.setNextNode(null);
        last = p;
        return str;
    }



    public void clean(){
        first = last = null;
        size = 0;
    }

    public MethodNode getFirst() {
        return first;
    }

    public void setFirst(MethodNode first) {
        this.first = first;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public MethodNode getLast() {
        return last;
    }

    public void setLast(MethodNode last) {
        this.last = last;
    }
}
