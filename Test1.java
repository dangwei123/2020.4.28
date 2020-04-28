给出一个区间的集合，请合并所有重叠的区间。

class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> list=new ArrayList<>();
        Arrays.sort(intervals,(a,b)->{
            return a[0]==b[0]?a[1]-b[1]:a[0]-b[0];
        });
        
        for(int i=0;i<intervals.length;i++){
            int left=intervals[i][0];
            int right=intervals[i][1];
            while(i<intervals.length-1&&right>=intervals[i+1][0]){
                right=Math.max(right,intervals[i+1][1]);
                i++;
            }
            list.add(new int[]{left,right});
        }
        return list.toArray(new int[list.size()][2]);
    }
}

班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。

给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
class Solution {
    public int findCircleNum(int[][] M) {
        int n=M.length;
        boolean[] isVisited=new boolean[n];
        int res=0;
        for(int i=0;i<n;i++){
            if(!isVisited[i]){
                dfs(M,isVisited,i,n);
                res++;
            }
        }
        return res;
    }
    private void dfs(int[][] arr,boolean[] isVisited,int j,int n){
        for(int i=0;i<n;i++){
            if(arr[j][i]==1&&!isVisited[i]){
                isVisited[i]=true;
                dfs(arr,isVisited,i,n);
            }
        }
    }
}

将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=mergeTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next=mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
}
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode fast=head;
        ListNode slow=head;
        ListNode pre=head;
        while(fast!=null&&fast.next!=null){
            pre=slow;
            fast=fast.next.next;
            slow=slow.next;
        }
        pre.next=null;
        ListNode left=sortList(head);
        ListNode right=sortList(slow);
        return merge(left,right);
    }
    private ListNode merge(ListNode l1,ListNode l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=merge(l1.next,l2);
            return l1;
        }else{
            l2.next=merge(l1,l2.next);
            return l2;
        }
    }
}

合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        return mergeSort(lists,0,lists.length-1);
    }
    private ListNode mergeSort(ListNode[] lists,int left,int right){
        if(left==right) return lists[left];
        int mid=left+(right-left)/2;
        ListNode l=mergeSort(lists,left,mid);
        ListNode r=mergeSort(lists,mid+1,right);
        return merge(l,r);
    }
    private ListNode merge(ListNode l1,ListNode l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=merge(l1.next,l2);
            return l1;
        }else{
            l2.next=merge(l1,l2.next);
            return l2;
        }
    }
}

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length==0) return 0;
        int res=nums[0];
        int sum=0;
        for(int i=0;i<nums.length;i++){
            if(sum>=0){
                sum+=nums[i];
            }else{
                sum=nums[i];
            }
            res=Math.max(res,sum);
        }
        return res;
    }
}

给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,(o1,o2)->{
            return o1[0]==o2[0]?o2[1]-o1[1]:o1[0]-o2[0];
        });
        int j=0;
        int[] arr=new int[envelopes.length];
        for(int i=0;i<envelopes.length;i++){
            int left=0;
            int right=j;
            while(left<right){
                int mid=left+(right-left)/2;
                if(arr[mid]<envelopes[i][1]){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }
            arr[left]=envelopes[i][1];
            if(left==j) j++;
        }
        return j;
    }
}

