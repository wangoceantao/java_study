package concurrent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wanghaitao on 2016/12/7.
 */
public class WaitAndNotify {

    private static Object lock = new Object();
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws ParseException {
        new OddThread("odd", count, lock).start();
        new OddThread("even", count, lock).start();
//        String data="2016-11-20";
//        System.out.println(getDataTime(strToDate(data)));

    }

    private static String getDataTime(Date data) {
        String dateFormat = "M月d日";
        return new SimpleDateFormat(dateFormat, Locale.CHINESE).format(data);
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date strtodate = formatter.parse(strDate);
        return strtodate;
    }

    static class OddThread extends Thread {
        private final Object lock;
        AtomicInteger count;

        public OddThread(String name, AtomicInteger count, Object lock) {
            super(name);
            this.count = count;
            this.lock = lock;
        }


        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "-" + count.getAndAdd(1));
                    if (count.get() > 100) {
                        break;
                    }
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
