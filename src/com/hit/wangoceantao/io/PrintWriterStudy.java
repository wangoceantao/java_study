package com.hit.wangoceantao.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhangnannan on 16/12/3.
 */
public class PrintWriterStudy {
    public static void main(String[] args) throws IOException {
        File outputFile = getOutputFile();
//        PrintWriter printWriter = new PrintWriter(new FileWriter(outputFile), true);
        PrintWriter printWriter = new PrintWriter(outputFile);
//        PrintWriter printWriter = new PrintWriter("printwriter.txt");
        long startTime = System.currentTimeMillis();
        try {
            for (int index = 0; index < 10000; index++) {
                printWriter.print("index:" + index);
            }
//            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("duration time:" + (endTime - startTime));
        }

    }

    private static File getOutputFile() throws IOException {
        File file = new File("input.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}
