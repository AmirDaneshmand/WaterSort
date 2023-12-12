public class Stack {
    private int size;
    private int top;
    private String[] colorStr;

    public Stack(int size){
        this.size = size;
        colorStr = new String[size];
        top = -1;

    }

    public boolean isCorrect(){
        if(isEmpty()){
            return false;
        }
        String color = colorStr[0];
        for (int i = 1; i < colorStr.length; i++) {
            if(colorStr[i]!=color || colorStr[i]==null){
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(){

        return (top ==-1);
    }

    public void push(String color){

        if(color==null){
            return;
        }

        colorStr[++top] = color;
    }

    public String peek(){

        if(top == -1){
            return null;
        }
        return colorStr[top];
    }

    public String pop(){
        if(isEmpty()){
            return null;
        }
        colorStr[top] = null;
        return colorStr[top--];
    }


    public boolean isFull(){
        return (top == size -1);
    }




    public String getColor(int index){
        return colorStr[index];
    }
    public void setColor(String color , int index){
        colorStr[index] = color;
//        System.out.println("cooool" +color);
//        System.out.println("isss" +colorStr[index]);
    }
}
