package concurrent;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;

/**
 * Created by zhangnannan on 17/2/22.
 */
public class PipeWriterAndPipeReader {

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);
    }

    static class Printer implements Runnable {
        private PipedReader in;

        public Printer(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive;
            try {
                while ((receive = in.read()) != -1) {
                }
            } catch (Exception e) {
            }
        }

    }
}
