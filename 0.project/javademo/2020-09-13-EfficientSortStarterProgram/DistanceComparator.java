
/**
 * Write a description of class DistanceComparator here.
 * 
 * @Grace Jyl
 * @version 1
 */


import java.util.*;

public class DistanceComparator implements Comparator<QuakeEntry> {

    Location fromWhere;
    
    public DistanceComparator(Location where) {
        fromWhere = where;
    }
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        double dist1 = q1.getLocation().distanceTo(fromWhere);
        double dist2 = q2.getLocation().distanceTo(fromWhere);
        return Double.compare(dist1, dist2);   // pos, neg, 0
    }
    
}
 