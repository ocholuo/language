

/**
 * Distance Filter
 *
 * @Grace JyL
 * @version 1.0, Sep 12 2020
 */


public class FilterDistance implements Filter{
    private float maxDistance;
    private Location loc;

    public FilterDistance(double lat, double lon, int maxDis){
        loc = new Location(lat,lon);
        maxDistance = maxDis;
    }

    public boolean satisfies(QuakeEntry qe){
        float currDis = qe.getLocation().distanceTo(loc);
        return currDis <= maxDistance;
    }

    public String getFilterName(){
        return "FilterDistance";
    }
}
