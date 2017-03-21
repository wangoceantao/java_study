package test;

/**
 * Created by wanghaitao on 2017/1/28.
 */
public enum SeasonWithValue {
    SPRING(0), SUMMER(1), AUTUMN(2), WINTER(3);
    private int value;

    private SeasonWithValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
