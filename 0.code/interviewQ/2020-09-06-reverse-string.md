




```java

public class Reversestring {
    
    public String reverseString(String s){
        String result = "";
        for(int i = 0; i < s.length(); i++){
            result = s.charAt(i) + result;
        }
        return result;
    }
}
```