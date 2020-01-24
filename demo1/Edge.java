package demo1;

import java.awt.*;

public class Edge {
    private int x, y;
    private int x1,y1;
    private int width, height;
    private boolean isHorizontal;

    private Color color = Color.WHITE;
    private boolean visible = false;
    private boolean free = true;

    public Edge(int x1, int y1, boolean isHorizontal) {
        this.x1 = x1;
        this.y1 = y1;
        this.isHorizontal=isHorizontal;
        if(isHorizontal){
            width=150;
            height=10;
            x=75 + 150 * x1;
            y=70 + 150 * y1;
        }else {
            width=10;
            height=150;
            x=70 + 150 * x1;
            y=75 + 150 * y1;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getHorizontal(){
        return isHorizontal;
    }

    public Color getColor() {
        return color;
    }

    public int getX1(){
        return x1;
    }

    public int getY1(){
        return y1;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void paint() {
        if (!isVisible()) {
            return;
        }

        int midValue = (isHorizontal ? getHeight() : getWidth()) / 2;
        int alphaStep = free ? 255 / midValue : 0;
        for (int i = 0; i < midValue; i+=1) {
            StdDraw.setPenColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 255 - alphaStep * i));
            if (isHorizontal) {
                StdDraw.filledRectangle(x + getWidth() / 2.0, y + getHeight() / 2.0 + i, getWidth() / 2.0, i);
                StdDraw.filledRectangle(x + getWidth() / 2.0, y + getHeight() / 2.0 - i, getWidth() / 2.0, i);
            } else {
                StdDraw.filledRectangle(x + getWidth() / 2.0 + i, y + getHeight() / 2.0, i, getHeight() / 2.0);
                StdDraw.filledRectangle(x + getWidth() / 2.0 - i, y + getHeight() / 2.0, i, getHeight() / 2.0);
            }
        }
    }
}