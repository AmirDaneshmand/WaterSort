public class MethodNode {
    private String operation;
    private MethodNode nextNode;

    public MethodNode(String operation){
        setOperation(operation);
        setNextNode(null);
    }


    private int deSelect;

    private int selectNext;

    private int selectPervious;


    private String firstColor;
    private String SecondColor;

    private int pourSelect;
    private int pourbottleNumber;
    public int counterPour = 0;

    private int swapSelect;
    private int swapbottleNumber;
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public MethodNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(MethodNode nextNode) {
        this.nextNode = nextNode;
    }

    public int getDeSelect() {
        return deSelect;
    }

    public void setDeSelect(int deSelect) {
        this.deSelect = deSelect;
    }

    public int getSelectNext() {
        return selectNext;
    }

    public void setSelectNext(int selectNext) {
        this.selectNext = selectNext;
    }

    public int getSelectPervious() {
        return selectPervious;
    }

    public void setSelectPervious(int selectPervious) {
        this.selectPervious = selectPervious;
    }

    public int getPourbottleNumber() {
        return pourbottleNumber;
    }

    public void setPourbottleNumber(int pourbottleNumber) {
        this.pourbottleNumber = pourbottleNumber;
    }

    public int getPourSelect() {
        return pourSelect;
    }

    public void setPourSelect(int pourSelect) {
        this.pourSelect = pourSelect;
    }

    public int getSwapSelect() {
        return swapSelect;
    }

    public void setSwapSelect(int swapSelect) {
        this.swapSelect = swapSelect;
    }

    public int getSwapbottleNumber() {
        return swapbottleNumber;
    }

    public void setSwapbottleNumber(int swapbottleNumber) {
        this.swapbottleNumber = swapbottleNumber;
    }

    public String getFirstColor() {
        return firstColor;
    }

    public void setFirstColor(String firstColor) {
        this.firstColor = firstColor;
    }

    public String getSecondColor() {
        return SecondColor;
    }

    public void setSecondColor(String secondColor) {
        SecondColor = secondColor;
    }
}
