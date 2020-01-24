package demo2;

import java.awt.*;

public class Button {
    private int x, y;
    private int width, height;
    private Color color = Color.GREEN;
    private String text;
    private boolean isPressed;
    public Button(double x,double y,String text){
        this.x=(int) x;
        this.y=(int) y;
        this.text=text;
        isPressed=false;
        width=100;
        height=50;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public void setPressed(boolean pressed){
        isPressed=pressed;
    }
    public boolean getPressed(){
        return isPressed;
    }
    public String getText(){
        return text;
    }
    public void paint() {
        StdDraw.rectangle(x,y,width/2,height/2);
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(x,y,width/2,height/2);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(x,y,text);
    }
}
