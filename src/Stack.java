public class Stack {
    private int size;
    private int top;
    private String[] colorStr;

    public Stack(int size){
        this.size = size;
        colorStr = new String[size];
        top = -1;

    }

    public boolean isEmpty(){

        return (top ==-1);
    }

    public void push(String color){

        colorStr[++top] = color;
    }

    public String peek(){

        return colorStr[top];
    }

    public String pop(){
        if(isEmpty()){
            return null;
        }
        return colorStr[top--];
    }






    public String getColor(int index){
        return colorStr[index];
    }
}
