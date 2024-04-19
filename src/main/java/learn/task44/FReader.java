package learn.task44;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FReader implements DataReader {
    private String path;

    public FReader(@Value("${spring.application.pathinput}") String path) {
        this.path = path;
    }
    public List<String> dtread() {
        List<String> res = new ArrayList<>();

        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                res.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {e.printStackTrace();
        } catch (IOException e) { e.printStackTrace();
    }
        return res;
    }

}
