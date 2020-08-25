




public class HashSet {

    public hashSet() {
        map = new HashMap<>();
    }

    

    public HashSet(Collection<? extends E> c) {
        map = new HashMap<>( Math.max((int) (c.size()/.75f) + 1, 16) );
        addAll(c);
    }




    
    public static void main (String[] args){

        Set<Interger> mySet = new HashSet<Interger>();

        mySet.add(1);
        mySet.add(2);
        mySet.add(3);
        mySet.add(4);  // return boolean true of false
        mySet.add(5);
        mySet.add(6);
        mySet.add(7);
        mySet.add(8);
        mySet.add(2);

        Set<String> mySet2 = new HashSet<String>();

        mySet.add("hello");
        mySet.add("world");

        // mySet.get(); not allow

        for(int num : mySet) {
            System.out.println(num);
        }
    }
}