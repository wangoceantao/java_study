/**
 * Created by wanghaitao on 16/8/26.
 */
public class PairStudy {

    public static void main(String... args) {
        Pair<?> pair = new Pair<>();
        if (pair.getName() == null)
            System.out.println("is null");
    }

    public boolean isNull(Pair<?> pair) {
        return pair.getName() == null;
    }
}
