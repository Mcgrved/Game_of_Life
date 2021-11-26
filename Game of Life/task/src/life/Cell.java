package life;

public class Cell {

    private int aliveNeighborsCount;
    private State state;

    Cell(int aliveNeighborsCount) {
        this.aliveNeighborsCount = aliveNeighborsCount;
    }

    Cell(int aliveNeighborsCount, State state) {
        this.aliveNeighborsCount = aliveNeighborsCount;
        this.state = state;
    }
    public int getAliveNeighborsCount() {
        return aliveNeighborsCount;
    }

    public void setAliveNeighborsCount(int aliveNeighborsCount) {
        this.aliveNeighborsCount = aliveNeighborsCount;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
