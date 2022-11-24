package cn.foofun.forge;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 从文件流中读取分行文本
 */
public class FileLinesStringSource implements Source<String> {

    LinesStringSource stringSource;

    public FileLinesStringSource(InputStream fis, Charset charset, int nextLines) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[64 * 1024];
        int len = fis.read(buf);
        while (len > 0) {
            bos.write(buf, 0, len);
            len = fis.read(buf);
        }
        String input = new String(bos.toByteArray(), charset);

        this.stringSource = new LinesStringSource(input, nextLines);
    }

    @Override
    public String next() {
        return this.stringSource.next();
    }
}
