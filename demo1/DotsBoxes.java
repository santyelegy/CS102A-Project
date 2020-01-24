package demo1;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DotsBoxes {
    //    private List<Edge> edges = new ArrayList<>();
//    private List<Dot> dots = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private ArrayList<Dot> dots = new ArrayList<>();
    private ArrayList<Surround> surrounds = new ArrayList<>();
    private Color currentColor = Color.RED;

    public DotsBoxes(int canvasWidth, int canvasHeight, int size) {
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 150 * size);
        StdDraw.setYscale(0, 150 * size);
        //   dots.add(new Dot(150 , 150 , 75));
        initialize(size);
    }

    // you should change it with multiple lines and change the upper and lower bound.
    // 3*3
    public void initialize(int size) {
        //  edges.add(new Edge(75, 70, 150, 10));
        //edges.add(new Edge(70, 75, 10, 150));

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                edges.add(new Edge(i,  j,true));
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                edges.add(new Edge(i,  j, false));
            }
        }
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                surrounds.add(new Surround(150 + 150 * i, 150 + 150 * j, 67));
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dots.add(new Dot(75 + 150 * i, 75 + 150 * j, 15));
            }
        }
    }


    public void update(int size, int mode,boolean[] isHumanChoose,int difficulty,int difficulty2) {
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        boolean isMousePressed = StdDraw.isMousePressed();
        boolean foundEdge = false;
        CP COMchoose;
        CP COMChoose2=new CPM();
        if (difficulty == 2){
            COMchoose = new CPM();
        }else {
            COMchoose = new CPL();
        }
        if(mode==3){
            if(difficulty2==2){
                COMChoose2=new CPM();
            }else {
                COMChoose2=new CPL();
            }
        }
        switch (mode) {
            case 1:
                for (Edge edge : edges) {
                    if (edge.isFree()) {
                        if (!foundEdge && edge.getBounds().contains(mousePoint)) {
                            edge.setColor(currentColor);
                            edge.setVisible(true);

                            if (isMousePressed) {
                                currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                                edge.setFree(false);

                            }
                            foundEdge = true; // Avoid multiple selections.
                            return;
                        } else {
                            edge.setVisible(false);
                        }
                    }
                }
                break;
            case 2:
                if (isHumanChoose[0]){
                    for (Edge edge : edges) {
                        if (edge.isFree()) {
                            if (!foundEdge && edge.getBounds().contains(mousePoint)) {
                                edge.setColor(currentColor);
                                edge.setVisible(true);

                                if (isMousePressed) {
                                    currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                                    edge.setFree(false);
                                    isHumanChoose[0] = false;
                                }
                                foundEdge = true; // Avoid multiple selections.
                            } else {
                                edge.setVisible(false);
                            }
                        }
                    }
                }else {
                    cpTurn(size,COMchoose);
                    isHumanChoose[0] = true;
                }
                break;
            case 3:
                if(isHumanChoose[0]) {
                    cpTurn(size,COMchoose);
                }else {
                    cpTurn(size,COMChoose2);
                    isHumanChoose[0]=false;
                }
        }
        boolean isSwitch = false;
        for (Surround surround : surrounds) {
            if (surround.isFree()) {
                switch (size) {
                    case 3:
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) {
                                if (surrounds.indexOf(surround) == i * 2 + j && edges.get(i * 3 + j).isFree() == false
                                        && edges.get(i * 3 + j + 1).isFree() == false && edges.get(i * 2 + j + 6).isFree() == false &&
                                        edges.get(i * 2 + j + 8).isFree() == false) {
                                    isSwitch = true;
                                    currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                                    surround.setColor(currentColor);
                                    surround.setFree(false);
                                    currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                                }
                            }
                        }
                        break;

                    case 4:
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (surrounds.indexOf(surround) == i * 3 + j && edges.get(i * 4 + j).isFree() == false
                                        && edges.get(i * 4 + j + 1).isFree() == false && edges.get(i * 3 + j + 12).isFree() == false &&
                                        edges.get(i * 3 + j + 15).isFree() == false) {
                                    isSwitch = true;
                                    currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                                    surround.setColor(currentColor);
                                    surround.setFree(false);
                                    currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                                }
                            }
                        }
                        break;
                    case 5:
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (surrounds.indexOf(surround) == i * 4 + j && edges.get(i * 5 + j).isFree() == false
                                        && edges.get(i * 5 + j + 1).isFree() == false && edges.get(i * 4 + j + 20).isFree() == false &&
                                        edges.get(i * 4 + j + 24).isFree() == false) {
                                    isSwitch = true;
                                    currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                                    surround.setColor(currentColor);
                                    surround.setFree(false);
                                    currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                                }
                            }
                        }
                        break;
                }
            }
        }
        if (isSwitch) {
            currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
            isHumanChoose[0] = isHumanChoose[0] == true ? false : true;
        }
    }

    public void paint(int size) {
        StdDraw.clear();
        // Paint edges first, so dots will be on the top layer.
        int width=size*150;
        StdDraw.picture(width/2,width/2,"Image2.jpg",width,width);
        for (Edge edge : edges) {
            edge.paint();
        }
        for (Dot dot : dots) {
            dot.paint();
        }
        for (Surround surround : surrounds) {
            if(!surround.isFree()) {
                surround.paint();
            }
        }
//      edges.forEach(Edge::paint);
//      dots.forEach(Dot::paint);
        StdDraw.show();
    }

    public void checkFull(String namea, String nameb,int size) {
        int blueAmount = 0;
        int redAmount = 0;
        for (Surround surround : surrounds) {
            if (surround.isFree()) {
                return;
            }

            if (surround.getColor() == Color.RED) {
                redAmount++;
            }
            if (surround.getColor() == Color.blue) {
                blueAmount++;
            }
        }


        boolean flag = true;
        while (flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StdDraw.clear();
            int width=size*150;
            StdDraw.picture(width/2,width/2,"Image2.jpg",width,width);
            StdDraw.setPenColor(StdDraw.BLACK);
            Font font=new Font("Arial",Font.BOLD,20);
            StdDraw.setFont(font);
            StdDraw.text(width/2,width/2,"Game over! Score -- ");
            StdDraw.text(width/2,width/2-50,namea+":"+redAmount+"    "+ nameb +":"+ blueAmount);

            if (blueAmount > redAmount) {
                //printf to rename
                StdDraw.text(width/2,width/2-100,nameb +"Wins");
            } else if (redAmount > blueAmount) {
                //printf to rename
                StdDraw.text(width/2,width/2-100,namea +"Wins");
            } else {
                StdDraw.text(width/2,width/2-100,"Tie!");
            }
            StdDraw.show();
        }
    }

    public void cpTurn(int size,CP COMchoose){
        int edgeIndex = COMchoose.cPRun(edges, size);
        edges.get(edgeIndex).setColor(currentColor);
        currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
        edges.get(edgeIndex).setFree(false);
        edges.get(edgeIndex).setVisible(true);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void main(int size,int mode,boolean isFirst,int difficulty,int difficulty2) {
        String namea ="PlayerA";
        String nameb ="PlayerB";

        boolean[] isHumanChoose = new boolean[]{true};

        switch (mode) {
            case 1:
                break;
            case 2:
                if (isFirst){
                    isHumanChoose = new boolean[]{true};
                }else {
                    isHumanChoose = new boolean[]{false};
                }
                nameb = "COM";
                if (!isFirst) {
                    nameb = namea;
                    namea = "COM";
                }
                break;
            case 3:
                namea = "1COM";
                nameb = "2COM";
                break;
        }
        DotsBoxes game = new DotsBoxes(150 * size, 150 * size, size);


        while (true) {
            game.update(size, mode, isHumanChoose, difficulty,difficulty2);
            game.paint(size);
            game.checkFull(namea, nameb,size);
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

