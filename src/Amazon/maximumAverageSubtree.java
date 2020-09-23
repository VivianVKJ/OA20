package Amazon;


import utils.TreeNode;

public class maximumAverageSubtree {
    public static TreeNode ans = null;
    public static double max = 0.0;

    public static void main(String[] args) {

    }
//    public TreeNode maximumAverageSubtree(TreeNode root) {
//        if (root == null) return null;
//        helper(root);
//        return ans;
//    }
//
//    private double[] helper(TreeNode root) {
//        if (root == null) return new double[]{0, 0};
//
//        double curTotal = root.val;
//        double count = 1;
//        for (TreeNode child : root.children) {
//            double[] cur = helper(child);
//            curTotal += cur[0];
//            count += cur[1];
//        }
//        double avg = curTotal / count;
//        if (count > 1 && avg > max) { //taking "at least 1 child" into account
//            max = avg;
//            maxNode = root;
//        }
//        return new double[]{curTotal, count};
//    }
}
