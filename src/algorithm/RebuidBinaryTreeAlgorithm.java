package algorithm;

/**
 * Created by zhangnannan on 2017/12/22.
 */
public class RebuidBinaryTreeAlgorithm<T> {

    public static void main(String[] args) {
        RebuidBinaryTreeAlgorithm<Integer> rebuidBinaryTreeAlgorithm = new RebuidBinaryTreeAlgorithm();
        int[] preOrder = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] middleOrder = new int[]{3, 2, 4, 1, 6, 5, 7};
        BinaryNode<Integer> integerBinaryNode = (BinaryNode<Integer>) rebuidBinaryTreeAlgorithm.rebuidTree(preOrder, middleOrder, preOrder.length);

    }

    public BinaryNode<T> rebuidTree(int[] preOrder, int[] middleOrder, int length) {
        if (preOrder == null
                || preOrder.length == 0
                || middleOrder == null
                || middleOrder.length == 0
                || preOrder.length != middleOrder.length) {
            return null;
        }
        return null;
    }

    private BinaryNode<T> rebuidCore(int[] preOrder, int preStart, int preEnd, int[] middleOrder, int middleStart, int middleEnd) {
        return null;
    }

    static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;
    }
}
