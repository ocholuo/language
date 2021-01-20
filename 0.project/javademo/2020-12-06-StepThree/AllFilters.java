
// The class AllFilters combines several filters. 

import java.util.ArrayList;

public class AllFilters implements Filter {

    // - A private variable named filters that is an ArrayList of type Filter.
    ArrayList<Filter> filters;
    
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }

    // - An addFilter method 
    // has one parameter named f of type Filter. 
    // This method allows one to add a Filter to the ArrayList filters. 
    public void addFilter(Filter f) {
        filters.add(f);
    }

    // - A satisfies method
    // has one parameter named id, a movie ID. 
    // This method returns true if the movie satisfies the criteria of all the filters in the filters ArrayList. Otherwise this method returns false.
    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (! f.satisfies(id)) {
                return false;
            }
        }
        return true;
    }
}
