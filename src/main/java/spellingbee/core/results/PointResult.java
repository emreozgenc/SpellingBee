package spellingbee.core.results;

public class PointResult {
    private String word;
    private int point;

    public PointResult(String word, int point) {
        this.word = word;
        this.point = point;
    }

    public String getWord() {
        return word;
    }

    public int getPoint() {
        return point;
    }
}
