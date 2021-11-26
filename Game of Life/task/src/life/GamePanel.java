package life;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int boardSize;
    private Universe universe;

    public GamePanel(int boardSize) {
        super();
        this.boardSize = boardSize;
        universe = new Universe(boardSize);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawBoard(g);
    }

    private void drawGrid(Graphics g) {
        g.drawRect(0, 0, this.boardSize * 20, this.boardSize * 20);
        for (int i = 0; i < this.boardSize; i++) {
            g.drawLine(i * 20, 0, i * 20, this.boardSize * 20);
            g.drawLine(0, i * 20, this.boardSize * 20, i * 20);
        }
    }

    private void drawBoard(Graphics g) {
        for (int i = 0; i < this.boardSize; i++)  {
            for (int j = 0; j < this.boardSize; j++)  {
                g.setColor(Color.BLACK);
                if (this.universe.getCurrentGeneration()[i][j].getState() == State.ALIVE) {
                    g.fillRect(j * 20, i * 20, 20, 20);
                }
            }
        }
    }
    public int getBoardSize() {
        return boardSize;
    }

    public Universe getUniverse() {
        return universe;
    }
}
