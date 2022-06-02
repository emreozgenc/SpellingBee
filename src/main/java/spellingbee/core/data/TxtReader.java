package spellingbee.core.data;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtReader implements DataReader {
    private final String FILE_PATH = "data.txt";
    private final File file;
    private List<String> data;

    public TxtReader() {
        file = new File(getClass().getClassLoader().getResource(FILE_PATH).getFile());
    }

    @Override
    public List<String> read() {
        List<String> words = new ArrayList<>();
        try {
            if (data != null) return data;

            Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 3) {
                    line = replaceCharacters(line);
                    words.add(line);
                }
            }

        } catch (Exception exception) {
            // To do
        }

        return data = words;
    }

    private String replaceCharacters(String str) {
        str = str.toLowerCase();
        str = str.replaceAll("â", "a");
        str = str.replaceAll("û", "u");
        str = str.replaceAll("î", "ı");

        return str;
    }

}



