

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Description: ${TODO}
 * <p>
 * Created by wanghaitao on 16/9/1 17:43.
 * <p>
 * Email：wanghaitao01@hecom.cn
 */
public class DateUtils {

    public static void main(String[] args) {
        System.out.println("-- 24小时制 --");
        System.out.println(getTimestampString(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12),true));
        System.out.println(getTimestampString(new Date(System.currentTimeMillis()),true));
        System.out.println(getTimestampString(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 10),true));
        System.out.println(getTimestampString(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 12),true));
        System.out.println(getTimestampString(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24),true));
        System.out.println(getTimestampString(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 48),true));
        System.out.println("-- 12小时制 --");
        System.out.println(getTimestampString(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12),false));
        System.out.println(getTimestampString(new Date(System.currentTimeMillis()),false));
        System.out.println(getTimestampString(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 10),false));
        System.out.println(getTimestampString(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 12),false));
        System.out.println(getTimestampString(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24),false));
        System.out.println(getTimestampString(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 48),false));
    }

    public static String getTimestampString(Date var0,boolean is24HourFormat) {
        String var1 = null;
        String var2 = Locale.getDefault().getLanguage();
        boolean var3 = var2.startsWith("zh");
        long var4 = var0.getTime();
        if (is24HourFormat) {
            if (isSameDay(var4)) {
                if (var3) {
                    var1 = "HH:mm";
                } else {
                    var1 = "HH:mm";
                }
            } else if (isYesterday(var4)) {
                if (!var3) {
                    return "Yesterday " + (new SimpleDateFormat("HH:mm", Locale.ENGLISH)).format(var0);
                }

                var1 = "昨天 HH:mm";
            } else if (var3) {
                var1 = "M月d日 HH:mm";
            } else {
                var1 = "MMM dd HH:mm";
            }

        } else {
            if (isSameDay(var4)) {
                if (var3) {
                    var1 = "aa hh:mm";
                } else {
                    var1 = "hh:mm aa";
                }
            } else if (isYesterday(var4)) {
                if (!var3) {
                    return "Yesterday " + (new SimpleDateFormat("hh:mm aa", Locale.ENGLISH)).format(var0);
                }

                var1 = "昨天aa hh:mm";
            } else if (var3) {
                var1 = "M月d日aa hh:mm";
            } else {
                var1 = "MMM dd hh:mm aa";
            }

        }

        return var3 ? (new SimpleDateFormat(var1, Locale.CHINESE)).format(var0) : (new SimpleDateFormat(var1, Locale.ENGLISH)).format(var0);
    }

    private static boolean isSameDay(long var0) {
        TimeInfo var2 = getTodayStartAndEndTime();
        return var0 > var2.getStartTime() && var0 < var2.getEndTime();
    }

    public static TimeInfo getTodayStartAndEndTime() {
        Calendar var0 = Calendar.getInstance();
        var0.set(Calendar.HOUR_OF_DAY, 0);
        var0.set(Calendar.MINUTE, 0);
        var0.set(Calendar.SECOND, 0);
        var0.set(Calendar.MILLISECOND, 0);
        Date var1 = var0.getTime();
        long var2 = var1.getTime();
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
        Calendar var5 = Calendar.getInstance();
        var5.set(Calendar.HOUR_OF_DAY, 23);
        var5.set(Calendar.MINUTE, 59);
        var5.set(Calendar.SECOND, 59);
        var5.set(Calendar.MILLISECOND, 999);
        Date var6 = var5.getTime();
        long var7 = var6.getTime();
        TimeInfo var9 = new TimeInfo();
        var9.setStartTime(var2);
        var9.setEndTime(var7);
        return var9;
    }

    private static boolean isYesterday(long var0) {
        TimeInfo var2 = getYesterdayStartAndEndTime();
        return var0 > var2.getStartTime() && var0 < var2.getEndTime();
    }


    public static TimeInfo getYesterdayStartAndEndTime() {
        Calendar var0 = Calendar.getInstance();
        var0.add(Calendar.DAY_OF_MONTH, -1);
        var0.set(Calendar.HOUR_OF_DAY, 0);
        var0.set(Calendar.MINUTE, 0);
        var0.set(Calendar.SECOND, 0);
        var0.set(Calendar.MILLISECOND, 0);
        Date var1 = var0.getTime();
        long var2 = var1.getTime();
        Calendar var4 = Calendar.getInstance();
        var4.add(Calendar.DAY_OF_MONTH, -1);
        var4.set(Calendar.HOUR_OF_DAY, 23);
        var4.set(Calendar.MINUTE, 59);
        var4.set(Calendar.SECOND, 59);
        var4.set(Calendar.MILLISECOND, 999);
        Date var5 = var4.getTime();
        long var6 = var5.getTime();
        TimeInfo var8 = new TimeInfo();
        var8.setStartTime(var2);
        var8.setEndTime(var6);
        return var8;
    }

}
