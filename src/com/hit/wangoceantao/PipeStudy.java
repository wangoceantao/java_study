package com.hit.wangoceantao;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Created by wanghaitao on 16/9/4.
 */
public class PipeStudy {
    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        try {
            out.connect(in);
            new PrintReadThread(in).start();
            int receive = 0;
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        } finally {
            if (out != null)
                out.close();
        }
    }

    static class PrintReadThread extends Thread {
        private PipedReader in;

        public PrintReadThread(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.print((char) receive);
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
