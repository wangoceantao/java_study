package lamabda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wanghaitao on 17/3/18.
 */
public class Test {
    public static void main(String[] args) {
        final List<Integer> input = new ArrayList<>();
        for (int index = 0; index < 20; index++) {
            input.add(index);
        }
        long count = input.stream().filter(integer -> integer % 2 == 0).count();
        System.out.println("count:" + count);
        input.stream().filter(integer -> integer % 2 == 1);
        List<String> collect = Stream.of("a", "b", "c", "dbdffdf").map(string -> string.toUpperCase()).
                filter(string ->
                        string.startsWith("A")
                                || string.startsWith("D"))
                .collect(Collectors.toList());
        for (String ele : collect) {
            System.out.println(ele);
        }

        Integer reduce = Stream.of(1, 2, 3, 4).reduce(0, (ac, ele) -> ac + ele);
        System.out.println("reduce:" + reduce);
        Stream.of(1, 2, 3, 4, 5).forEach(integer -> System.out.println(integer));

    }
}
