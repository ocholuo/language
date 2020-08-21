


import java.util.*;


public class Hashtable {

   public static void main(String args[]) {

      // Create a hash map
      Hashtable ht = new Hashtable();
      Enumeration names;
      String str;
      double bal;

      ht.put("Zara", new Double(3434.34));
      ht.put("Mahnaz", new Double(123.22));
      ht.put("Ayan", new Double(1378.00));
      ht.put("Daisy", new Double(99.22));
      ht.put("Qadir", new Double(-19.08));

      // Show all hts in hash table.
      names = ht.keys();
      while(names.hasMoreElements()) {
         str = (String) names.nextElement();
         System.out.println(str + ": " + ht.get(str));
      }
      System.out.println();

      // Deposit 1,000 into Zara's account
      bal = ((Double)ht.get("Zara")).doubleValue();
      ht.put("Zara", new Double(bal+1000));
      System.out.println("Zara's new ht: " + ht.get("Zara"));
   }
}

// 结果如下：

// Qadir: -19.08
// Zara: 3434.34
// Mahnaz: 123.22
// Daisy: 99.22
// Ayan: 1378.0

// Zara's new balance: 4434.34