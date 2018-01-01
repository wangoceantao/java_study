package algorithm;

/**
 * Created by wanghaitao on 17/12/22.
 */
public class SortFinder {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 4, 6, 7, 10, 12, 15, 19, 40};
        SortFinder finder = new SortFinder();
        System.out.println("length:" + array.length);
        int length = array.length;
        System.out.println("key = 4 ;pos:" + finder.binarySearch(array, 4));
        System.out.println("key = 4 ;pos:" + finder.binarySearch(array, 4, 0, length - 1));
        System.out.println("key = 15;pos:" + finder.binarySearch(array, 15));
        System.out.println("key = 15;pos:" + finder.binarySearch(array, 15, 0, length - 1));
        System.out.println("key = 1;pos:" + finder.binarySearch(array, 1));
        System.out.println("key = 1;pos:" + finder.binarySearch(array, 1, 0, length - 1));
        System.out.println("key = 40 ;pos:" + finder.binarySearch(array, 40));
        System.out.println("key = 40 ;pos:" + finder.binarySearch(array, 40, 0, length - 1));
        System.out.println("key = 50 ;pos:" + finder.binarySearch(array, 50));
        System.out.println("key = 50 ;pos:" + finder.binarySearch(array, 50, 0, length - 1));
    }

    public int binarySearch(int[] array, int key) {
        int low = 0;
        int top = array.length - 1;
        int pos = -1;
        while (low <= top) {
            int middle = (low + top) / 2;
            if (key == array[middle]) {
                pos = middle;
                break;
            } else if (array[middle] < key) {
                low = middle + 1;
            } else {
                top = middle - 1;
            }
        }
        return pos;
    }

    public int binarySearch(int[] array, int key, int low, int top) {
        if (low > top) {
            return -1;
        }
        int middle = (low + top) / 2;
        if (key == array[middle]) {
            return middle;
        } else if (array[middle] < key) {
            return binarySearch(array, key, middle + 1, top);
        } else {
            return binarySearch(array, key, low, middle - 1);
        }
    }
}
