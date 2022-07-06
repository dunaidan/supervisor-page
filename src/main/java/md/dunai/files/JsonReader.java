package md.dunai.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.List;

public class JsonReader<T> extends FileReader {
    private final List<T> data;

    public JsonReader(String fileName, Type type) {
        super(fileName);

        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy")
                .create();
        this.data = gson.fromJson(getReader(), type);
    }

    public List<T> getData() {
        return data;
    }
}
