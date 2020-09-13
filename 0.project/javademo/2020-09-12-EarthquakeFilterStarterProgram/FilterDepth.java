

/**
 * Depth Filter
 *
 * @Grace JyL
 * @version 1.0, Sep 12 2020
 */


public class FilterDepth implements Filter{
    private double minDepth;
    private double maxDepth;

    public FilterDepth(double minNum, double maxNum){
        minDepth = minNum;
        maxDepth = maxNum;
    }

    public boolean satisfies(QuakeEntry qe){
        return minDepth <= qe.getDepth() && qe.getDepth() <= maxDepth;
    }

    public String getFilterName(){
        return "FilterDepth";
    }
}
