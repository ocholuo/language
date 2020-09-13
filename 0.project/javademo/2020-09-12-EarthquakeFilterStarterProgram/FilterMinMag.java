

/**
 * Filter MinMag
 *
 * @Grace JyL
 * @version 1.0, Sep 12 2020
 */


public class FilterMinMag implements Filter{

    private double magMin;

    public FilterMinMag(double min) {
        magMin = min;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin;
    }

    public String getFilterName(){
        return "FilterMinMag";
    }
}
