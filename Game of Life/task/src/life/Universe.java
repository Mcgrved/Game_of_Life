package life;

import java.util.Random;

public class Universe {

    private int size;
    private int aliveNumber;
    private int generationNumber;
    private Cell[][] currentGeneration;
    private Cell[][] nextGeneration;
    private Random random;

    public Universe(int size) {
        createBoard(size);
    }

    public void createBoard(int size) {
        this.size = size;
        this.generationNumber = 0;
        this.aliveNumber = 0;
        currentGeneration = new Cell [size][size];
        nextGeneration = currentGeneration;
        random = new Random();

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.currentGeneration[i][j] = new Cell(0, random.nextBoolean() ? State.ALIVE : State.DEAD);
                aliveNumber += this.currentGeneration[i][j].getState() == State.ALIVE ? 1 : 0;
            }
        }
    }
    public void playGame() {
        changeGeneration();
        determineBoardState();
    }

    public void determineBoardState() {
        int neighbor;
        this.nextGeneration = new Cell[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                neighbor = checkNeighborCells(i, j);
                nextGeneration[i][j] = new Cell(neighbor, determineState(neighbor,i ,j));
            }
        }
    }

    private void changeGeneration() {
        this.aliveNumber = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.currentGeneration[i][j] = this.nextGeneration[i][j];
                aliveNumber += this.currentGeneration[i][j].getState() == State.ALIVE ? 1 : 0;
            }
        }
        this.generationNumber++;
    }

    public State determineState(int cells, int iCord, int jCord) {
        if ((this.currentGeneration[iCord][jCord].getState() == State.ALIVE &&
                (cells == 2 || cells == 3)) ||
                (this.currentGeneration[iCord][jCord].getState() == State.DEAD && cells == 3)) {
            return State.ALIVE;
        } else {
            return State.DEAD;
        }
    }

    public int checkNeighborCells(int iCord, int jCord) {
        int aliveNeighborsCount = 0;
        int xValue = 0;
        int yValue = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    xValue = iCord + i >= 0 ? (iCord + i < this.currentGeneration.length ? iCord + i : 0) : this.size - 1;
                    yValue = jCord + j >= 0 ? (jCord + j < this.currentGeneration.length ? jCord + j : 0) : this.size - 1;
                    aliveNeighborsCount += this.currentGeneration[xValue][yValue].getState() == State.ALIVE ? 1 : 0;
                }
            }
        }
        return aliveNeighborsCount;
    }

    public Cell[][] getCurrentGeneration() {
        return currentGeneration;
    }

    public int getGenerationNumber() {
        return generationNumber;
    }

    public int getAliveNumber() {
        return aliveNumber;
    }
}
