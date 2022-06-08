package spellingbee.core.managers;

import spellingbee.core.constants.Messages;
import spellingbee.core.data.DataFilter;
import spellingbee.core.data.DataReader;
import spellingbee.core.data.FilteredData;
import spellingbee.core.data.GameData;
import spellingbee.core.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCreatorManager implements GameCreatorService {

    private final DataReader dataReader;
    private final DataFilter dataFilter;
    private final List<String> selectedWords;
    private final Random r;
    private final String TURKISH_LETTERS = "abcçdefgğhıijklmnoöprsştuüvyz";

    public GameCreatorManager(DataFilter dataFilter, DataReader dataReader) {
        this.dataFilter = dataFilter;
        this.dataReader = dataReader;

        selectedWords = new ArrayList<>();
        r = new Random();
    }

    public GameData create(String letters) throws PangramNotFoundException, IllegalWordCountException, IllegalPointRangeException, IllegalLettersLengthException, NotUniqueLettersException, IllegalLetterException {

        lettersCheck(letters);

        FilteredData filteredData = filterWords(letters);

        firstStatusChecks(filteredData);

        List<String> filteredWords = filteredData.getWords();
        List<String> pangramWords = filteredData.getPangramWords();


        secondStatusChecks(filteredWords, pangramWords);

        return new GameData(filteredWords, pangramWords, letters);

    }

    public GameData create() throws PangramNotFoundException, IllegalWordCountException, IllegalPointRangeException {

        FilteredData filteredData = filterWords();

        firstStatusChecks(filteredData);
        String letters = filteredData.getLetters();
        List<String> filteredWords = filteredData.getWords();
        List<String> pangramWords = filteredData.getPangramWords();

        secondStatusChecks(filteredWords, pangramWords);

        return new GameData(filteredWords, pangramWords, letters);
    }

    private boolean totalPointAcceptable(int totalPoint) {
        return totalPoint >= 100 && totalPoint <= 400;
    }

    private boolean wordCountAcceptable(int wordCount) {
        return wordCount >= 20 && wordCount <= 80;
    }

    private boolean isPangram(List<String> pangramWords, String word) {
        return pangramWords.contains(word);
    }

    private int getWordPoint(List<String> pangramWords, String word) {
        int sum = 0;
        if (!isPangram(pangramWords, word)) {
            sum += word.length() - 3;
        } else {
            sum += word.length() + 4;
        }
        return sum;
    }

    private int filteredWordsTotal(List<String> pangramWords, List<String> words) {
        int totalPoint = 0;
        for (String word : words) {
            totalPoint += getWordPoint(pangramWords, word);
        }
        return totalPoint;
    }

    private void lettersCheck(String letters) throws IllegalLettersLengthException, NotUniqueLettersException, IllegalLetterException {
        if (letters.length() != 7) {
            throw new IllegalLettersLengthException(Messages.ILLEGAL_LETTERS_LENGTH);
        }
        checkIllegalLetter(letters);
        checkUniqueLetters(letters);
    }

    private void checkUniqueLetters(String letters) throws NotUniqueLettersException {
        for (int i = 0; i < letters.length(); i++) {
            for (int j = i + 1; j < letters.length(); j++) {
                if (letters.charAt(i) == letters.charAt(j)) {
                    throw new NotUniqueLettersException(Messages.NOT_UNIQUE_LETTERS);
                }
            }
        }
    }

    private void checkIllegalLetter(String letters) throws IllegalLetterException {
        boolean contains;
        String alphabet = TURKISH_LETTERS;
        for (int i = 0; i < letters.length(); i++) {
            contains = false;
            for (int j = 0; j < alphabet.length(); j++) {
                if (letters.charAt(i) == alphabet.charAt(j)) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                throw new IllegalLetterException(Messages.ILLEGAL_LETTER);
            }
        }
    }

    private void firstStatusChecks(FilteredData filteredData) throws PangramNotFoundException, IllegalWordCountException {
        if (filteredData.getPangramWords().size() == 0) {
            throw new PangramNotFoundException(Messages.PANGRAM_NOT_FOUND);
        }
        if (!wordCountAcceptable(filteredData.getWords().size())) {
            throw new IllegalWordCountException(Messages.ILLEGAL_WORD_COUNT);
        }
    }

    private void secondStatusChecks(List<String> filteredWords, List<String> pangramWords) throws IllegalPointRangeException {
        if (!totalPointAcceptable(filteredWordsTotal(pangramWords, filteredWords))) {
            throw new IllegalPointRangeException(Messages.ILLEGAL_POINT_RANGE);
        }
    }

    private FilteredData filterWords(String letters) {
        return dataFilter.filter(readWords(), letters);
    }

    private FilteredData filterWords() {
        return dataFilter.filter(readWords());
    }

    private List<String> readWords() {
        return dataReader.read();
    }

}



