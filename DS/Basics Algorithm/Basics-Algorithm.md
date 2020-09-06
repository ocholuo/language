




Binary Search

sorted array arr[] of n elements, to search a given element x in arr[].

linear search.
- The time complexity of above algorithm is O(n). 
  
Binary Search
- Search a sorted array by repeatedly dividing the search interval in half. 
- Begin with an interval covering the whole array. 
- If the value of the search key is less than the item in the middle of the interval, narrow the interval to the lower half. 
- Otherwise narrow it to the upper half. 
- Repeatedly check until the value is found or the interval is empty.
- Time Complexity: `T(n) = T(n/2) + c `    `O(Logn)`
- Auxiliary Space: 
  - iterative implementation. `O(1)`
  - recursive implementation, `O(Logn)` recursion call stack space.


```java
public class Solution {

    // Compare x with the middle element.
    // If x = middle element, return the mid index.
    // Else If x > mid element, then x can only lie in right half subarray after the mid element. So we recur for right half.
    // Else (x is smaller) recur for the left half.


    // 1. recursive Binary Search 
    public int binarySearch1(int arr[], int l, int r, int x){
        if(r >= l){
            int mid = (r+l)/2;
            if(x == arr[mid]){
                return mid;
            }
            else if(x > arr[mid]) {
                return binarySearch(arr, mid+1, r, x);
            }
            else {
                return binarySearch(arr, l, mid-1, x);
            }
        }
        return -1;
    }

    // 2. iterative Binary Search 
    int binarySearch2(int arr[], int x) { 
        int l = 0;
        int r = arr.length - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
            if (arr[m] == x){
                return m; 
            }
            if (arr[m] < x) {
                l = m + 1; 
            }
            else{
                r = m - 1; 
            }
        } 
        return -1; 
    } 


    public static void main(String[] args) {
        Solution pr = new Solution();
        int arr[] = { 2, 3, 4, 10, 40 }; 
        int x = 50; 

        int result = pr.binarySearch1(arr, 0, arr.length - 1, x); 
        if (result == -1) {
            System.out.println("Element not present"); 
        }
        else{
            System.out.println("Element found at index " + result); 
        }

        int result = pr.binarySearch2(arr, x); 
        if (result == -1) {
            System.out.println("Element not present"); 
        }
        else{
            System.out.println("Element found at index " + result); 
        }
    } 
}
```


