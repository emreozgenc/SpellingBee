package spellingbee.components;

import javafx.scene.layout.Pane;

public class Beehive extends Pane {

    // Temporary
    private String[] values = {"A", "B", "C", "D", "E", "F", "G"};
    private BeehiveCell[] cells;
    private final double CELL_EDGE_WIDTH = 50;
    private final int[][] cellMap = {
            {0, 1, 0},
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1},
            {0, 1, 0}
    };

    public Beehive() {
        int polygonCount = 0;
        cells = new BeehiveCell[7];
        for(int i=0;i<cells.length;i++) {
            cells[i] = new BeehiveCell(values[i], CELL_EDGE_WIDTH);
        }
        int cellIndex = 0;
        for(int i=0;i<cellMap.length;i++) {
            for(int j=0;j<cellMap[i].length;j++) {
                if(cellMap[i][j] == 1) {
                    BeehiveCell cell = cells[cellIndex];
                    cell.setLayoutX(j*1.5*CELL_EDGE_WIDTH);
                    cell.setLayoutY(i*Math.sqrt(3)*0.5*CELL_EDGE_WIDTH);
                    cellIndex++;
                }
            }
        }

        getChildren().addAll(cells);
    }
}
