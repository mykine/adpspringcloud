package cn.mykine.ad.study.suanfa;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class T1 {

    static int[] twoSum(int[] nums, int target) {
        int[] indexs = new int[2];
        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            hash.put(target-nums[i],i);
        }
        return indexs;
    }

   static int[] twoSum2(int[] nums, int target) {
        int[] arr = new int[2];
        Map<Integer,Integer> map = new HashMap();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int j=0;j<nums.length;j++){
            Integer otherValue = target - nums[j];
            if(map.containsKey(otherValue) && !map.get(otherValue).equals(j) ){
                arr[0] = j;
                arr[1] = map.get(otherValue);
                return arr;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
//        String str = "PAYPALISHIRING";
//        int num = 3;
//        System.out.println(convert(str,num));

//        int[] nums = new int[]{3,2,4};
        int[] nums = new int[]{2,1,6,0,4};
        int[] res = twoSum(nums,6);
        int[] res2 = twoSum2(nums,6);
        System.out.println(JSON.toJSON(res));
        System.out.println(JSON.toJSON(res2));
    }

    public static String convert(String s, int numRows) {
        if( 1 == numRows ){
            return s;
        }
        char[] charArr = s.toCharArray();
        int cycleNum = numRows+(numRows-2);
        String[] resArr = new String[numRows];
        for(int i=0;i<resArr.length;i++){
            resArr[i] = "";
        }
        for(int i=1;i<=charArr.length;i++){
            int y = i % cycleNum;
            y = y== 0 ? cycleNum : y;
            int targetIndex = ( y <= numRows ? (y-1) : (numRows-(y-numRows)-1));
            resArr[targetIndex] += String.valueOf(charArr[i-1]);

        }
        String res="";
        for(int i=0; i<resArr.length;i++){
            res+=resArr[i];
        }
        return res;
    }

}
