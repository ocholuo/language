

/**
 * Match All Filter
 *
 * @Grace JyL
 * @version 1.0, Sep 12 2020
 */


import java.util.*;
import edu.duke.*;

public class MatchAllFilter implements Filter{

    private ArrayList<Filter> matchAllList;

    public MatchAllFilter(){
        matchAllList = new ArrayList<Filter>();
    }

    public void addFilter(Filter f){
        matchAllList.add(f);
    }

    public boolean satisfies(QuakeEntry qe){
        for(Filter f : matchAllList){
            if( !f.satisfies(qe) ){
                return false;
            }

        }
        return true;
    }

    public String getFilterName(){
        ArrayList<String> nameAll = new ArrayList<String>();
        for (Filter f : matchAllList){
            nameAll.add(f.getFilterName());
        }
        String New = "";
        for (int i=0; i< nameAll.size(); i++){
            New = New + " " + nameAll.get(i);
        }
        return New ;
    }

}
