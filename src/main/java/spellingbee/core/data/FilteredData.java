package spellingbee.core.data;

import java.util.List;

public class FilteredData {
    private final List<String> words;
    private final String letters;
    private final List<String> pangramWords;

    public FilteredData(List<String> words, List<String> pangramWords, String letters) {
        this.words = words;
        this.pangramWords = pangramWords;
        this.letters = letters;
    }

    public List<String> getWords() {
        return words;
    }

    public String getLetters() {
        return letters;
    }

    public List<String> getPangramWords() {
        return pangramWords;
    }
}