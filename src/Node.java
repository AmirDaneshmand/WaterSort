public class Node {

    private Stack stackNode;
    private Node nextNode;

    public Node(Stack stackNode){
        this.setStackNode(stackNode);
        this.setNextNode(null);
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
}
