

/**
 * Magnitude Filter
 *
 * @Grace JyL
 * @version 1.0, Sep 12 2020
 */


public class FilterMagnitude implements Filter{
    private double minMagnitude;
    private double maxMagnitude;

    public FilterMagnitude(double minNum, double maxNum){
        minMagnitude = minNum;
        maxMagnitude = maxNum;
    }

    public boolean satisfies(QuakeEntry qe){
        // System.out.println(qe.getMagnitude());
        return minMagnitude <= qe.getMagnitude() && qe.getMagnitude() <= maxMagnitude;
    }

    public String getFilterName(){
        return "FilterMagnitude";
    }
}
