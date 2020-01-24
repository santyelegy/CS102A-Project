package demo1;

import demo2.Test;

public class Main {
    public static void main(String args[]){
        Test test=new Test();
        String[] input= test.run().split(" ");
        String stringsize = String.valueOf(input[0].charAt(0));
        int size = Integer.parseInt(stringsize);
        int mode,difficulty,difficulty1;
        boolean isFirst;
        DotsBoxes dotsBoxes=new DotsBoxes(150 * size, 150 * size, size);
        if(input[1].equals("PVP")){
            isFirst=true;
            mode=1;
        }else if(input[1].equals("PVE")){
            isFirst=true;
            mode=2;
        }else if(input[1].equals("EVP")){
            isFirst=false;
            mode=2;
        }else {
            isFirst=true;
            mode=3;
        }
        if(input[2].equals("Low")){
            difficulty=1;
        }else {
            difficulty=2;
        }
        if(input[3].equals("Low")){
            difficulty1=1;
        }else {
            difficulty1=2;
        }
        dotsBoxes.main(size,mode,isFirst,difficulty,difficulty1);

    }
}
