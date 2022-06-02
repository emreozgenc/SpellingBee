package spellingbee.components;

import javafx.scene.layout.Pane;
import spellingbee.core.constants.Colors;

import java.util.Random;

public class Beehive extends Pane {

    private final BeehiveCell[] cells;
    private final double CELL_EDGE_WIDTH = 50;
    private final boolean[][] cellMap = {
            {false, true, false},
            {true, false, true},
            {false, true, false},
            {true, false, true},
            {false, true, false}
    };

    public Beehive(String letters) {
        cells = new BeehiveCell[letters.length()];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new BeehiveCell(Character.toString(letters.charAt(i)),
                    CELL_EDGE_WIDTH,
                    i == cells.length / 2 ? Colors.CELL_CENTER_POLYGON : Colors.CELL_POLYGON,
                    i == cells.length / 2 ? Colors.CELL_CENTER_OUTPUT : Colors.CELL_OUTPUT);
        }
        int cellIndex = 0;
        for (int i = 0; i < cellMap.length; i++) {
            for (int j = 0; j < cellMap[i].length; j++) {
                if (cellMap[i][j]) {
                    BeehiveCell cell = cells[cellIndex];
                    cell.setLayoutX(j * 1.5 * CELL_EDGE_WIDTH);
                    cell.setLayoutY(i * Math.sqrt(3) * 0.5 * CELL_EDGE_WIDTH);
                    cellIndex++;
                }
            }
        }
        getChildren().addAll(cells);
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cells.length; i++) {
            if (i != 3) cells[i].playShuffleAnimation();
        }
        for (int i = 0; i < cells.length; i++) {
            int randomIndex = random.nextInt(cells.length);
            if (randomIndex == cells.length / 2 || i == cells.length / 2) continue;

            String temp = cells[i].getCellValue();
            cells[i].setCellValue(cells[randomIndex].getCellValue());
            cells[randomIndex].setCellValue(temp);
        }
    }

    public BeehiveCell[] getCells() {
        return cells;
    }
}
