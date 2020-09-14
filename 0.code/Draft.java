
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Draft {
    
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++){
            int data = Math.abs(nums[i]) - 1;
            if(nums[data] > 0){
                nums[data] = -nums[data];
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                result.add(i+1);
                System.out.println(i+1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Draft pr = new Draft();
        int[] num = {4,3,2,7,8,2,3,1};
        List<Integer> result = pr.findDisappearedNumbers(num);
    }
}
