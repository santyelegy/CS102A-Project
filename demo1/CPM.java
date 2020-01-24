package demo1;

import java.util.ArrayList;
import java.util.List;

public class CPM implements CP{
    @Override
    public int cPRun(List<Edge> edges,int size){
        int twoSide=0;
        for (int x=0;x<size-1;x++){
            for(int y=0;y<size-1;y++){
                List<Edge>edges1=checkEmpytSide(edges,x,y);
                if(edges1.size()==1){
                    return returnPosition(edges1.get(0),size);
                }else if(edges1.size()==2||edges1.size()==0){
                    twoSide++;
                }
            }
        }
        List<Edge>cantChoose=new ArrayList<>();
        for (int x=0;x<size-1;x++) {
            for (int y = 0; y < size - 1; y++) {
                List<Edge>edges1=checkEmpytSide(edges,x,y);
                if(edges1.size()==2){
                    for (Edge edge:edges1) {
                        if (edge.isFree()) {
                            cantChoose.add(edge);
                        }
                    }
                }
            }
        }
        ArrayList<Integer> falsePosition=new ArrayList<>();
        for (Edge edge:cantChoose){
            falsePosition.add(returnPosition(edge,size));
        }
        if((size-1)*(size-1)!=twoSide){
            ArrayList<Integer> canchoose=new ArrayList<>();
            for (Edge edge:edges){
                boolean choose=true;
                for (int position:falsePosition){
                    if(position==returnPosition(edge,size)){
                        choose=false;
                    }
                }
                if (choose&&!edge.isVisible()){
                    canchoose.add(returnPosition(edge,size));
                }
            }
            if (canchoose.size()!=0) {
                int index = (int) (Math.random() * canchoose.size());
                return canchoose.get(index);
            }else {
                CPL cpl=new CPL();
                return cpl.cPRun(edges,size);
            }
        }else {
            CPL cpl=new CPL();
            return cpl.cPRun(edges,size);
        }
    }
    private List<Edge> checkEmpytSide(List<Edge> edges,int x,int y){
        List<Edge> empty=new ArrayList<>();
        List<Edge> surround=getSurroundEdge(edges,x,y);
        for (Edge edge:surround){
            if(edge.isFree()){
                empty.add(edge);
            }
        }
        return empty;
    }
    private List<Edge> getSurroundEdge(List<Edge> edges,int x,int y){
        List<Edge> surround=new ArrayList<>();
        for(Edge edge:edges){
            if (edge.getX1()==x&&edge.getY1()==y){
                surround.add(edge);
            }else if(edge.getX1()==x+1&&edge.getY1()==y&&!edge.getHorizontal()){
                surround.add(edge);
            }else if(edge.getX1()==x&&edge.getY1()==y+1&&edge.getHorizontal()){
                surround.add(edge);
            }
        }
        return surround;
    }
    private int returnPosition(Edge edge,int size){
        int x=edge.getX1();
        int y=edge.getY1();
        boolean horizontal=edge.getHorizontal();
        int index;
        if(horizontal){
            index=y+x*size;
        }else {
            index=size*(size-1)+y+x*(size-1);
        }
        return index;
    }
}
