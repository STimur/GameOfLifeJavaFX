package core;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    public final boolean alive;
    private List<Cell> neighbours = new ArrayList<>();
    private int numOfAliveNeighbours = 0;

    public Cell(boolean isAlive) {
        this.alive = isAlive;
    }

    public Cell nextGeneration() {
        if (isAliveInNextGeneration())
            return new Cell(true);
        return new Cell(false);
    }

    private boolean isAliveInNextGeneration() {
        return willLive() || willRevive();
    }

    private boolean willLive() {
        return alive && (numOfAliveNeighbours == 2 || numOfAliveNeighbours == 3);
    }

    private boolean willRevive() {
        return !alive && numOfAliveNeighbours == 3;
    }

    public void addNeighbour(Cell cell) {
        if (cell.alive)
            numOfAliveNeighbours++;
        neighbours.add(cell);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (alive != cell.alive) return false;
        return true;
    }
}
