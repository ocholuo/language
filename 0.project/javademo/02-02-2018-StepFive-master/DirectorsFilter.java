

public class DirectorsFilter implements Filter {
    private String myDirector;
    public DirectorsFilter(String director){
        myDirector = director;
    }
    
    public boolean satisfies(String id){
        return myDirector.contains(MovieDatabase.getDirector(id));
    }
}
