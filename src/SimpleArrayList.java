/**
 * Created by wanghaitao on 16/9/5.
 */
public class SimpleArrayList<T> implements List<T> {
    private T[] array;
    private final int DEFAULT_CAPACITY = 12;
    private int size;

    public SimpleArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];

    }

    @Override
    public void add(T value) {
        array[size++] = value;
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return array[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("invalid index");
    }

    public static void main(String[] args) {
        SimpleArrayList<String> list = new SimpleArrayList<>();
        for (int index = 0; index < 6; index++) {
            list.add("index-" + index);
        }
        System.out.println(list.size());
        System.out.println(list.get(4));
    }
}
