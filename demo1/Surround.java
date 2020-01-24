package demo1;

import java.awt.*;

public class Surround {
    private int x, y;
    private int size;
    private Color color = Color.WHITE;
    private boolean free = true;

    public Surround(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public void paint() {
        StdDraw.setPenColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
        StdDraw.filledCircle(x, y, size);
    }
}
