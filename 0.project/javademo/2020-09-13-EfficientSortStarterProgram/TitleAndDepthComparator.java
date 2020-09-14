import java.util.Comparator;

/**
 * Title And Depth Comparator
 * 
 * @Grace Jyl
 * @version 1
 */


public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    

    public int compare(QuakeEntry q1, QuakeEntry q2) {

        // q1’s title comes before q2’s title
        // if( q1.getInfo().compareTo(q2.getInfo()) == -1){
        //     return -1;
        // }

        // if( q1.getInfo().compareTo(q2.getInfo()) == 1){
        //     return 1;
        // }

        int compString = q1.getInfo().compareTo(q2.getInfo());
        if (compString == 0){
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
        return compString;
    }
    

}
