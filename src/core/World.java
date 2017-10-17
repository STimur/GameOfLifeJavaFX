package core;

public class World {
    private Cell[][] cells;
    private final int h;
    private final int l;

    public World(String input) {
        String[] lines = input.split("\n");
        h = lines.length;
        l = lines[0].length();
        cells = new Cell[h][l];
        boolean isAlive = false;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                if (lines[i].charAt(j) == '*')
                    isAlive = true;
                else
                    isAlive = false;
                cells[i][j] = new Cell(isAlive);
            }
        }
        addNeighbours();
    }

    private void addNeighbours() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                addTopLeftNeighbourForCell(i, j);
                addTopNeighbourForCell(i, j);
                addTopRightNeighbourForCell(i, j);
                addLeftNeighbourForCell(i, j);
                addRightNeighbourForCell(i, j);
                addBottomLeftNeighbourForCell(i, j);
                addBottomNeighbourForCell(i, j);
                addBottomRightNeighbourForCell(i, j);
            }
        }
    }

    private void addBottomRightNeighbourForCell(int i, int j) {
        if (i + 1 < h && j + 1 < l)
            cells[i][j].addNeighbour(cells[i + 1][j + 1]);
        else if (i + 1 >= h && j + 1 < l)
            cells[i][j].addNeighbour(cells[0][j + 1]);
        else if (i + 1 < h && j + 1 >= l)
            cells[i][j].addNeighbour(cells[i + 1][0]);
        else if (i + 1 >= h && j + 1 >= l)
            cells[i][j].addNeighbour(cells[0][0]);
    }

    private void addBottomNeighbourForCell(int i, int j) {
        if (i + 1 < h)
            cells[i][j].addNeighbour(cells[i + 1][j]);
        else
            cells[i][j].addNeighbour(cells[0][j]);
    }

    private void addBottomLeftNeighbourForCell(int i, int j) {
        if (i + 1 < h && j - 1 >= 0)
            cells[i][j].addNeighbour(cells[i + 1][j - 1]);
        else if (i + 1 >= h && j - 1 >= 0)
            cells[i][j].addNeighbour(cells[0][j - 1]);
        else if (i + 1 < h && j - 1 < 0)
            cells[i][j].addNeighbour(cells[i + 1][l - 1]);
        else if (i + 1 >= h && j - 1 < 0)
            cells[i][j].addNeighbour(cells[0][l - 1]);
    }

    private void addRightNeighbourForCell(int i, int j) {
        if (j + 1 < l)
            cells[i][j].addNeighbour(cells[i][j + 1]);
        else
            cells[i][j].addNeighbour(cells[i][0]);
    }

    private void addLeftNeighbourForCell(int i, int j) {
        if (j - 1 >= 0)
            cells[i][j].addNeighbour(cells[i][j - 1]);
        else
            cells[i][j].addNeighbour(cells[i][l - 1]);
    }

    private void addTopRightNeighbourForCell(int i, int j) {
        if (i - 1 >= 0 && j + 1 < l)
            cells[i][j].addNeighbour(cells[i - 1][j + 1]);
        else if (i - 1 < 0 && j + 1 < l)
            cells[i][j].addNeighbour(cells[h - 1][j + 1]);
        else if (i - 1 >= 0 && j + 1 >= l)
            cells[i][j].addNeighbour(cells[i - 1][0]);
        else if (i - 1 < 0 && j + 1 >= l)
            cells[i][j].addNeighbour(cells[h - 1][0]);
    }

    private void addTopNeighbourForCell(int i, int j) {
        if (i - 1 >= 0)
            cells[i][j].addNeighbour(cells[i - 1][j]);
        else
            cells[i][j].addNeighbour(cells[h - 1][j]);
    }

    private void addTopLeftNeighbourForCell(int i, int j) {
        if (i - 1 >= 0 && j - 1 >= 0)
            cells[i][j].addNeighbour(cells[i - 1][j - 1]);
        else if (i - 1 < 0 && j - 1 >= 0)
            cells[i][j].addNeighbour(cells[h - 1][j - 1]);
        else if (i - 1 >= 0 && j - 1 < 0)
            cells[i][j].addNeighbour(cells[i - 1][l - 1]);
        else if (i - 1 < 0 && j - 1 < 0)
            cells[i][j].addNeighbour(cells[h - 1][l - 1]);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                if (cells[i][j].alive)
                    str += "*";
                else
                    str += ".";
            }
            str += "\n";
        }
        return str.trim();
    }

    public World nextGeneration() {
        Cell[][] newCells = new Cell[h][l];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < l; j++)
                newCells[i][j] = cells[i][j].nextGeneration();
        cells = newCells;
        addNeighbours();
        return this;
    }
}
