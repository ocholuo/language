import lib.edu.duke.*;

public class FindingWebLinks {
    
    // Part 4: Finding Web Links
    public void findURL(){
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String linkstring : ur.lines()) {
            String s = linkstring.toLowerCase();
            int pos = s.indexOf("youtube.com");
            if(pos != -1){
                // System.out.println(s);
                int beginIndex = s.lastIndexOf("\"", pos);
                if(beginIndex != -1){
                    int endIndex = s.indexOf("\"", beginIndex+1);
                    // System.out.println(beginIndex);
                    // System.out.println(endIndex);
                    String resultlink = linkstring.substring(beginIndex+1, endIndex);
                    System.out.println(resultlink);
                }
            }
        }
    }

    
    public StorageResource findURLs(String url) {
		URLResource page = new URLResource(url);
		String source = page.asString();
		StorageResource store = new StorageResource();
		int start = 0;
		while (true) {
			int index = source.indexOf("href=", start);
			if (index == -1) {
				break;
			}
			int firstQuote = index+6; // after href="
			int endQuote = source.indexOf("\"", firstQuote);
			String sub = source.substring(firstQuote, endQuote);
			if (sub.startsWith("http")) {
                store.add(sub);
                System.out.println(sub);
			}
			start = endQuote + 1;
        }
		return store;
	}

	public void testURL() {
		StorageResource s1 = findURLs("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		// StorageResource s2 = findURLs("http://www.doctorswithoutborders.org");
		// for (String link : s1.data()) {
		// 	System.out.println(link);
		// }
		// System.out.println("size = " + s1.size());
		// System.out.println("size = " + s2.size());
    }
    

    public static void main(String[] args) {
        // FindingWebLinks pr = new FindingWebLinks();
        // pr.findURL();

    }


}
