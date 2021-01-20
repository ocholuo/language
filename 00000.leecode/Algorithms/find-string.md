


# find string

finds each occurrence of “abc_” in a String input (where _ is a single character) and prints “bc_” for each such occurrence. 

```
For example, 
findAbc(“abcdefabcghi”) should print:
bcd
bcg
```


```java
public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1) {
            break;
        }
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+4);
    }
}
   public void test() {
    //no code yet
    findAbc("abcdabc");
}
```




