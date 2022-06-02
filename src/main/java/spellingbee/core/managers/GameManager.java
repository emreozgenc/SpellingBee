package spellingbee.core.managers;

import spellingbee.core.constants.Messages;
import spellingbee.core.data.GameData;
import spellingbee.core.exceptions.*;
import spellingbee.core.results.PointResult;

import java.util.ArrayList;
import java.util.List;

public class GameManager implements GameService {

    private final int MIN_WORD_LENGTH = 4;
    private final GameData data;
    private int currentPoint = 0;
    private final List<String> foundWords;

    private final int maximumPoint;

    public GameManager(GameData data) {
        this.data = data;
        foundWords = new ArrayList<>();
        maximumPoint = calculateMaximumPoint();
    }

    @Override
    public PointResult check(String inputWord) throws DictionaryDoesNotContainWordException, IllegalWordLengthException, WordContainsIllegalLetterException, WordDoesNotContainCenterLetterException, WordAlreadyFoundException {

        checkLength(inputWord);
        checkCenterLetter(inputWord);
        checkIllegalLetter(inputWord);
        checkDictionary(inputWord);
        checkFound(inputWord);
        int point = calculatePoint(inputWord);

        currentPoint += point;
        foundWords.add(inputWord);

        return new PointResult(inputWord, point, currentPoint);
    }

    private void checkFound(String inputWord) throws WordAlreadyFoundException {
        if (foundWords.contains(inputWord)) {
            throw new WordAlreadyFoundException(Messages.WORD_ALREADY_FOUND);
        }
    }

    public int getCurrentPoint() {
        return currentPoint;
    }

    private int calculatePoint(String inputWord) {
        if (data.getPangramWords().contains(inputWord))
            return inputWord.length() + MIN_WORD_LENGTH;

        return inputWord.length() - MIN_WORD_LENGTH + 1;
    }

    private void checkIllegalLetter(String inputWord) throws WordContainsIllegalLetterException {
        String letters = data.getLetters();

        for (int i = 0; i < inputWord.length(); i++) {
            char ch = inputWord.charAt(i);
            if (!letters.contains(Character.toString(ch)))
                throw new WordContainsIllegalLetterException(Messages.WORD_CONTAINS_ILLEGAL_LETTER);
        }
    }

    private void checkCenterLetter(String inputWord) throws WordDoesNotContainCenterLetterException {
        char centerLetter = data.getLetters().charAt(data.getLetters().length() / 2);


        if (!inputWord.contains(Character.toString(centerLetter)))
            throw new WordDoesNotContainCenterLetterException(Messages.WORD_DOES_NOT_CONTAIN_CENTER_LETTER);

    }

    private void checkLength(String inputWord) throws IllegalWordLengthException {
        if (inputWord.length() < MIN_WORD_LENGTH)
            throw new IllegalWordLengthException(Messages.ILLEGAL_WORD_LENGTH);
    }

    private void checkDictionary(String inputWord) throws DictionaryDoesNotContainWordException {
        if (!data.getWords().contains(inputWord))
            throw new DictionaryDoesNotContainWordException(Messages.DICTIONARY_DOES_NOT_CONTAIN_WORD);
    }

    private int calculateMaximumPoint() {
        int point = 0;

        for (String word : data.getWords()) {
            point += calculatePoint(word);
        }

        return point;
    }

    @Override
    public int getMaximumPoint() {
        return maximumPoint;
    }
}
