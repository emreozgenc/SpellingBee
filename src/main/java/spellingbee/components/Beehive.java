package spellingbee.components;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;

public class Beehive extends Pane {

    // Temporary
    private String letters;
    private BeehiveCell[] cells;
    private final double CELL_EDGE_WIDTH = 50;
    private final Color CENTER_CELL_BACKGROUND_OUTPUT = Color.rgb(255, 209, 0);
    private final Color CELL_OUTPUT = Color.BLACK;
    private final Color CELL_BACKGROUND = Color.rgb(225, 225, 225);
    private final int[][] cellMap = {
            {0, 1, 0},
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1},
            {0, 1, 0}
    };

    public Beehive(String letters) {
        this.letters = letters;
        cells = new BeehiveCell[letters.length()];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new BeehiveCell(Character.toString(letters.charAt(i)),
                    CELL_EDGE_WIDTH,
                    i == cells.length / 2 ? CENTER_CELL_BACKGROUND_OUTPUT : CELL_BACKGROUND,
                    i == cells.length / 2 ? CENTER_CELL_BACKGROUND_OUTPUT : CELL_OUTPUT);
        }
        int cellIndex = 0;
        for (int i = 0; i < cellMap.length; i++) {
            for (int j = 0; j < cellMap[i].length; j++) {
                if (cellMap[i][j] == 1) {
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
        for(int i=0;i<cells.length;i++) {
            if(i != 3) cells[i].playShuffleAnimation();
        }
        for (int i = 0; i < cells.length; i++) {
            int randomIndex = random.nextInt(cells.length);
            if (randomIndex == cells.length / 2 || i == cells.length / 2) continue;

            String temp = cells[i].getCellValue();
            cells[i].setCellValue(cells[randomIndex].getCellValue());
            cells[randomIndex].setCellValue(temp);
        }
    }
}
