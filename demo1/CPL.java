package demo1;

import java.util.ArrayList;
import java.util.List;

public class CPL implements CP{
    @Override
    public int cPRun(List<Edge> edges,int size){
        List<Integer> empty=new ArrayList<>();
        for (int i=0;i<edges.size();i++){
            if(edges.get(i).isFree()){
                empty.add(i);
            }
        }
        int index=(int)( Math.random()*(empty.size()));
        return empty.get(index);
    }
}
