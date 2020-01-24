package demo2;

import java.awt.*;
import java.util.ArrayList;

public class Test {
    private Button button;
    private Button buttonStart;
    private static boolean nextstep=false;
    private ArrayList<Button> sizeButton=new ArrayList<>();
    private ArrayList<Button> playerButton=new ArrayList<>();
    private ArrayList<Button> cpButton1=new ArrayList<>();
    private ArrayList<Button> cpButton2=new ArrayList<>();
    public Test(){
        StdDraw.setCanvasSize(750, 500);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 150 * 5);
        StdDraw.setYscale(0, 150 *5);
        button = new Button(375, 200, "Start");
        buttonStart=new Button(600,150,"Start");
        for (int i=0;i<3;i++) {
            String text=String.format("%dX%d",i+3,i+3);
            Button button1 = new Button(200+150*i, 500, text);
            sizeButton.add(button1);
        }
        String[] whoToPlay={"PVP","PVE","EVP","EVE"};
        for (int i=0;i<4;i++) {
            Button button1 = new Button(200+150*i, 400, whoToPlay[i]);
            playerButton.add(button1);
        }
        String[] cpLevel={"Low","Advanced"};
        for (int i=0;i<2;i++) {
            Button button1 = new Button(200+200*i, 200, cpLevel[i]);
            Button button2=new Button(200+200*i,100,cpLevel[i]);
            cpButton1.add(button1);
            cpButton2.add(button2);
        }
    }

    public String run() {
        Test test=new Test();
        while (true){
            test.paint();
            if (nextstep){
                StdDraw.clear();
                StdDraw.show();
                break;
            }
        }
        nextstep=false;
        while (true){
            test.paintTwo();
            if (nextstep){
                StdDraw.clear();
                StdDraw.show();
                break;
            }
        }
        nextstep=false;
        return (test.returnOut(test.sizeButton)+" "+test.returnOut(test.playerButton)+" "+test.returnOut(test.cpButton1)+" "+test.returnOut(test.cpButton2));
    }

    public void paint(){
        StdDraw.clear();
        StdDraw.text(375,300,"Dot and Box Game");
        StdDraw.picture(375,500,"image.png");
        button.paint();
        mouseMove(button);
        if (button.getPressed()){
            nextstep=true;
        }
        StdDraw.show();
    }
    public void paintTwo(){
        StdDraw.clear();
        StdDraw.picture(375,600,"image.png");
        StdDraw.text(100,500,"Choose size");
        buttonOperation(sizeButton);
        StdDraw.text(75,400,"Choose Player Type");
        buttonOperation(playerButton);
        StdDraw.text(300,300,"Choose CP level");
        StdDraw.text(300,350,"if you choose PVE or PVP,you will play against CP1");
        StdDraw.text(100,200,"CP1 level");
        StdDraw.text(100,100,"CP2 level");
        buttonOperation(cpButton1);
        buttonOperation(cpButton2);
        buttonStart.paint();
        mouseMove(buttonStart);
        if (buttonStart.getPressed()){
            if(checkInput()){
                nextstep=true;
            }
        }
        StdDraw.show();
    }
    private void mouseMove(Button button){
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        boolean isMousePressed = StdDraw.isMousePressed();
        if (button.getBounds().contains(mousePoint)) {
            button.setColor(StdDraw.BLUE);
            if(isMousePressed&&!button.getPressed()) {
                button.setPressed(true);
            }
        } else {
            button.setColor(StdDraw.GREEN);
        }
        if (button.getPressed()){
            button.setColor(StdDraw.BLUE);
        }
    }
    private void buttonOperation(ArrayList<Button> typeButton){
        for (Button button:typeButton){
            boolean press=button.getPressed();
            button.paint();
            mouseMove(button);
            if(!press&&button.getPressed()){
                for (Button button1:typeButton){
                    button1.setPressed(false);
                }
                button.setPressed(true);
            }
        }
    }
    private boolean checkInput(){
        boolean result=false;
        if(checkButtonPressed(sizeButton)&&checkButtonPressed(playerButton)&&checkButtonPressed(cpButton1)) {
            String text = null;
            for (Button button : playerButton) {
                if (button.getPressed()) {
                    text = button.getText();
                }
            }
            if (!text.equals("EVE")) {
                result = true;
            } else if (checkButtonPressed(cpButton2)) {
                result = true;
            }
        }
        return result;
    }
    private boolean checkButtonPressed(ArrayList<Button> buttons){
        boolean result=true;
        int cal=0;
        for(Button button:buttons){
            if(button.getPressed()){
                cal++;
            }
        }
        if(cal!=1){
            result=false;
        }
        return result;
    }
    private String returnOut(ArrayList<Button> buttons){
        String out=null;
        for(Button button:buttons){
            if(button.getPressed()){
                out=button.getText();
            }
        }
        return out;
    }
}


