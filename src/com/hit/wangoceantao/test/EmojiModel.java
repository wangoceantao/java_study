package com.hit.wangoceantao.test;


/**
 * @Description: ${TODO}
 * <p>
 * Created by wanghaitao on 2016/10/11 13:52.
 * <p>
 * Email：wanghaitao01@hecom.cn
 */

public class EmojiModel {

    public boolean isNeedUpdate(String localVersion, String newVersion) {
//        if (TextUtils.isEmpty(localVersion) || TextUtils.isEmpty(newVersion)) {
//            return true;
//        }
        String[] localVersionArray = localVersion.split("\\.");
        String[] newVersionArray = newVersion.split("\\.");
        int size = Math.min(localVersionArray.length, newVersionArray.length);
        for (int index = 0; index < size; index++) {
            if (Integer.valueOf(localVersionArray[index]).compareTo(Integer.valueOf(newVersionArray[index])) < 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        EmojiModel emojiModel = new EmojiModel();
        boolean needUpdate = emojiModel.isNeedUpdate("2.0.0.0", "2.0.1");
        System.out.println("needUpdate:" + needUpdate);
    }
}
