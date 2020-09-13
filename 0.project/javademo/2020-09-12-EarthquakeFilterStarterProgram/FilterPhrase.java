
/**
 * Filter Phrase
 *
 * @Grace JyL
 * @version 1.0, Sep 12 2020
 */


public class FilterPhrase implements Filter{

    private String where;
    private String phrase;

    public FilterPhrase(String type, String searchPhrase) {
        where = type;
        phrase = searchPhrase;
    }

    public boolean satisfies(QuakeEntry qe) {
        if (where.equals("start")) {
            if (qe.getInfo().startsWith(phrase)) {
                return true;
            }
        } else if (where.equals("end")) {
            if (qe.getInfo().endsWith(phrase)) {
                return true;
            }
        } else if (where.equals("any")) {
            if (qe.getInfo().contains(phrase)) {
                return true;
            }
        }
        return false;
    }

    public String getFilterName(){
        return "FilterPhrase";
    }
}
