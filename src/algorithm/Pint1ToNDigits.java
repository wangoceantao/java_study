package algorithm;

/**
 * Created by zhangnannan on 2017/12/23.
 */
public class Pint1ToNDigits {
    public static void main(String[] args) {
        Pint1ToNDigits instance = new Pint1ToNDigits();
        instance.print1ToNDigits(4);
    }

    public void print1ToNDigits(int n) {
        char[] number = new char[n];
        recursive1ToNDigits(number, n, 0);
    }

    private void recursive1ToNDigits(char[] number, int length, int index) {
        if (index == length) {
            printCharNumber(number);
            return;
        }

        for (int i = 0; i < 10; i++) {
            number[index] = (char) (i + '0');
            recursive1ToNDigits(number, length, index + 1);
        }
    }

    private void printCharNumber(char[] number) {
        int lenght = number.length;
        boolean notStartWithZero = false;
        for (int index = 0; index < lenght; index++) {
            if (number[index] != '0' && !notStartWithZero) {
                notStartWithZero = true;
            }
            if (notStartWithZero) {
                System.out.print(number[index]);
                if (index == lenght - 1) {
                    System.out.println();
                }
            }
        }


    }
}
