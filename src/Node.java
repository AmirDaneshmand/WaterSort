public class Node {

    private Stack stackNode;
    private Node nextNode;
    private boolean isSelected;

    public Node(Stack stackNode){
        this.setStackNode(stackNode);
        this.setNextNode(null);
        this.setSelected(false);
    }



    public Stack getStackNode() {

        return stackNode;
    }

    public void setStackNode(Stack stackNode) {

        this.stackNode = stackNode;
    }

    public Node getNextNode() {

        return nextNode;
    }

    public void setNextNode(Node nextNode) {

        this.nextNode = nextNode;
    }

    public boolean isSelected() {
        return isSelected;
    }


    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
