import com.hit.wangoceantao.entity.Pair;

/**
 * Created by zhangnannan on 16/8/26.
 */
public class PatternStudy {
    public static void main(String[] args) {
        Pair<String>[] table = (Pair<String>[]) new Pair<?>[10];
        table[0] = new Pair<>();
        table[0].setValue("pair 1");
        System.out.println(table[0].getValue());
    }

    @SuppressWarnings("unchecked")
    private static void test() {
    }
}
