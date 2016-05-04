package practice.first.array;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import practice.first.util.PrettyPrint;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null||nums.length==0) return result;
        build(nums,0 , new ArrayList<Integer>(),result);
        return result;
    }
    
    private void build(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if(current.size()==nums.length) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        for(int i=0;i<=current.size();i++) {
            if(current.size()==0&&index>0&&nums[index]==nums[index-1]) continue;
            current.add(i, nums[index]);
            build(nums, index+1, current, result);
            current.remove(i);
        }
    }
    
    @Test
    public void test0() {
        int[] nums = new int[]{1,1,3};
        List<List<Integer>> result = permuteUnique(nums);
        for(List<Integer> list : result) {
            PrettyPrint.print(list);
        }
    }
}
