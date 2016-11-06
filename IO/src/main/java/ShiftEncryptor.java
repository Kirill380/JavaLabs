import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ShiftEncryptor {

    public ShiftEncryptor() {

    }

    public List<String> decode(InputStream sourFile,  OutputStream destFile, Character shiftSymbol) throws IOException {
        List<String> decodedContent = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new ShiftDecoder(new InputStreamReader(sourFile), shiftSymbol))) {
            String line;
            while ((line = br.readLine()) != null) {
                decodedContent.add(line);
            }
        }
        FileUtils.writeLines(destFile, decodedContent, Charset.defaultCharset());

        return decodedContent;
    }


    public void encode(InputStream sourFile, OutputStream destFile, Character shiftSymbol) throws IOException {
        List<String> content = FileUtils.readLines(sourFile, Charset.defaultCharset());
        try(BufferedWriter bw = new BufferedWriter(new ShiftEncoder(new OutputStreamWriter(destFile), shiftSymbol))) {
            for (String string : content) {
                bw.write(string);
            }
         }
    }


    private static class ShiftEncoder extends FilterWriter {
        private Character shiftSymbol;
        protected ShiftEncoder(Writer out, Character shiftSymbol) {
            super(out);
            this.shiftSymbol = shiftSymbol;
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            for (int i = 0; i < cbuf.length; i++) {
                cbuf[i] = (char) (cbuf[i] + shiftSymbol);
            }
            super.write(cbuf, off, len);
        }
    }

    private  static class ShiftDecoder extends FilterReader {
        private Character shiftSymbol;

        protected ShiftDecoder(Reader in, Character shiftSymbol) {
            super(in);
            this.shiftSymbol = shiftSymbol;
        }
        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            int n = super.read(cbuf, off, len);
            for (int i = 0; i < cbuf.length; i++) {
                cbuf[i] = (char) (cbuf[i] - shiftSymbol);
            }
            return n;
        }
    }

}
