假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>=nums[left]){
                if(target>=nums[left]&&target<=nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            } else{
                if(target>=nums[mid]&&target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return -1;
    }
}

给定一个包含了一些 0 和 1 的非空二维数组 grid 。

一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
class Solution {
    private int res;
    public int maxAreaOfIsland(int[][] grid) {
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                res=Math.max(res,dfs(grid,i,j));
            }
        }
        return res;
    }
    private int dfs(int[][] grid,int i,int j){
        if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]==0){
            return 0;
        }
        grid[i][j]=0;
        return 1+dfs(grid,i+1,j)+dfs(grid,i-1,j)+dfs(grid,i,j+1)+dfs(grid,i,j-1);
    }
}

给定一个未经排序的整数数组，找到最长且连续的的递增序列。
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length==0) return 0;
        int[] dp=new int[nums.length];
        Arrays.fill(dp,1);
        int res=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                dp[i]=dp[i-1]+1;
                res=Math.max(res,dp[i]);
            }
        }
        return res;
    }
}

在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums,0,nums.length-1,nums.length-k);
    }
    private int quickSort(int[]nums,int left,int right,int k){
        int pivot=partition(nums,left,right);
        if(pivot==k){
            return nums[pivot];
        }else if(pivot>k){
            return quickSort(nums,left,pivot-1,k);
        }else{
            return quickSort(nums,pivot+1,right,k);
        }
    }
    private int partition(int[] nums,int left,int right){
        int pivot=nums[left];
        int i=left;
        int j=right;
        while(i<j){
            while(i<j&&nums[j]>=pivot){
            j--;
        }
        nums[i]=nums[j];
        while(i<j&&nums[i]<=pivot){
            i++;
        }
        nums[j]=nums[i];
        }
        nums[i]=pivot;
        return i;
        
    }
}

给定一个未排序的整数数组，找出最长连续序列的长度。

要求算法的时间复杂度为 O(n)。
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int num:nums){
            set.add(num);
        }
        int res=0;
        for(int num:nums){
            int left=1;
            int l=num-1;
            while(set.contains(l)){
                left++;
                l--;
            }
            int right=0;
            int r=num+1;
            while(set.contains(r)){
                right++;
                r++;
            }
            res=Math.max(res,left+right);
        }
        return res;
    }
}

给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。
class Solution {
    public String getPermutation(int n, int k) {
        int[] arr=new int[n];
        List<Integer> list=new LinkedList<>();
        arr[0]=1;
        for(int i=1;i<n;i++){
            list.add(i);
            arr[i]=arr[i-1]*i;
        }
        list.add(n);
        k--;
        StringBuilder sb=new StringBuilder();
        for(int i=n-1;i>=0;i--){
            int index=k/arr[i];
            sb.append(list.remove(index));
            k-=index*arr[i];
        }
        return new String(sb);
    }
}

