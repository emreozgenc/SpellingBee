package spellingbee.core.results;

public class PointResult {
    private String word;
    private int point;
    private int currentPoint;

    public PointResult(String word, int point, int currentPoint) {
        this.word = word;
        this.point = point;
        this.currentPoint = currentPoint;
    }

    public String getWord() {
        return word;
    }

    public int getPoint() {
        return point;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }
}
