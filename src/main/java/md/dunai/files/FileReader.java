package md.dunai.files;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileReader {
    private final String fileName;
    BufferedReader reader;

    public FileReader(String fileName) {
        this.fileName = fileName;
        ClassLoader classLoader = FileReader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(this.fileName);
        if(inputStream != null) {
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            this.reader = new BufferedReader(streamReader);
        }

    }

    public String getFileName() {
        return fileName;
    }

    public BufferedReader getReader() {
        return reader;
    }
}
