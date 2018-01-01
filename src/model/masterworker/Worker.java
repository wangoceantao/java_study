package model.masterworker;

import java.util.Map;
import java.util.Queue;

/**
 * Created by zhangnannan on 16/11/6.
 */
public class Worker implements Runnable {
    private Queue<Object> workQuene;
    private Map<String, Object> resultMap;

    public void setWorkerQuene(Queue<Object> workerQuene) {
        this.workQuene = workerQuene;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public Object handle(Object input) {
        return input;
    }

    @Override
    public void run() {
        while (true) {
            Object input = workQuene.poll();
            if (input == null) {
                break;
            }
            Object result = handle(input);
            resultMap.put(Integer.toString(input.hashCode()), result);
        }

    }
}
