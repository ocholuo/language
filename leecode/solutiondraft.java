
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class solutiondraft {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> rangeSet = new HashSet<>();
        Set<Integer> numSet = new HashSet<>();
        
        for(int i = 1; i <= nums.length; i++)
            rangeSet.add(i);
        for(int num : nums)
            numSet.add(num);
            
        for(int n : rangeSet)
            if(!numSet.contains(n))
                ans.add(n);
        return ans;
    }


    

    public static void main (String[] args) {
        int arr[] = {4,3,2,7,8,2,3,1};
        Solution method = new Solution();
        List<Integer> result = method.findDisappearedNumbers(arr);
        System.out.println(result);

    }

}