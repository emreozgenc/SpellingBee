package spellingbee.core.data;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtReader implements DataReader {
    private final String FILE_PATH = "dictionary.txt";
    private File file;

    public TxtReader() {
        file = new File(getClass().getClassLoader().getResource(FILE_PATH).getFile());
    }

    @Override
    public List<String> read() {
        List<String> words = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                words.add(line);
            }
        } catch (Exception exception) {
            // To do
        }

        return words;
    }

}



