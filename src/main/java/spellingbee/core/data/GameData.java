package spellingbee.core.data;

import java.util.List;

public class GameData {
    private final List<String> words;
    private final List<String> pangramWords;
    private final String letters;

    public GameData(List<String> words, List<String> pangramWords, String letters) {
        this.words = words;
        this.pangramWords = pangramWords;
        this.letters = letters;
    }

    public List<String> getWords() {
        return words;
    }

    public List<String> getPangramWords() {
        return pangramWords;
    }

    public String getLetters() {
        return letters;
    }
}

