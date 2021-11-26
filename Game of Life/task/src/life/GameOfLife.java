package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOfLife extends JFrame {

    private int generationNumber;
    private int aliveNumber;
    private final GamePanel gamePanel;
    private final JPanel statPanel;
    private final JLabel generationsNumberLabel;
    private final JLabel aliveNumberLabel;
    private JToggleButton pauseResume;
    private JButton restart;
    private Thread gameThread;
    private boolean enabledButton;

    public GameOfLife(){
        super("Game of Life");
        gamePanel = new GamePanel(25);
        statPanel = new JPanel();

        setSize(gamePanel.getBoardSize() * 20 + 25, gamePanel.getBoardSize() * 20 + 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        generationNumber = gamePanel.getUniverse().getGenerationNumber();
        aliveNumber = gamePanel.getUniverse().getAliveNumber();

        generationsNumberLabel = new JLabel("Generation #" + generationNumber);
        generationsNumberLabel.setName("GenerationLabel");
        statPanel.add(generationsNumberLabel);

        aliveNumberLabel = new JLabel("Alive #" + aliveNumber);
        aliveNumberLabel.setName("AliveLabel");
        statPanel.add(aliveNumberLabel);

        pauseResume = new JToggleButton("\u2BC8");
        pauseResume.setName("PlayToggleButton");
        enabledButton = false;
        pauseResume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enabledButton) {
                    enabledButton = true;
                    startAnimation();
                } else {
                    gameThread.interrupt();
                    enabledButton = false;
                }
            }
        });
        statPanel.add(pauseResume);

        restart = new JButton("\u27F2");
        restart.setName("ResetButton");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.getUniverse().createBoard(gamePanel.getBoardSize());
                updateStats();
            }
        });
        statPanel.add(restart);

        statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(statPanel);
        add(gamePanel);
        setVisible(true);

    }

    private void startAnimation() {
        gameThread = new Thread(() -> {
            while(!gameThread.isInterrupted()) {
                try {
                    gamePanel.getUniverse().playGame();
                    Thread.sleep(150);
                    updateStats();
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        gameThread.start();
    }

    public void updateStats() {
        generationNumber = gamePanel.getUniverse().getGenerationNumber();
        aliveNumber = gamePanel.getUniverse().getAliveNumber();

        generationsNumberLabel.setText("Generation #" + generationNumber);
        aliveNumberLabel.setText("Alive #" + aliveNumber);

        gamePanel.repaint();
        gamePanel.revalidate();
        statPanel.revalidate();
    }

}
