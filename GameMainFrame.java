import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameMainFrame extends JFrame implements KeyListener {
    private JLabel label1;
    private JLabel label2;
    private Timer timer; 
    private int xVel = 10;
    private int yVel = 10;
    static int points = 0;
    static int level = 1;

    public GameMainFrame() {
        setTitle("Save The Ball");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setSize(800, 640);

        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        addBackgroundImage(layeredPane);
        rect_base(layeredPane);
        ball_(layeredPane);
        score_board(layeredPane);

        addKeyListener(this);
        setFocusable(true);

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ball_move();
            }
        });
        timer.start();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void addBackgroundImage(Container container) {
        ImageIcon backgroundImage = new ImageIcon("picture_bg.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);
        container.add(backgroundLabel, new Integer(0));
    }

    void rect_base(Container container) {
        ImageIcon base = new ImageIcon("base_shape.png");
        label1 = new JLabel(base);
        label1.setBounds(300, 540, 100, 15);
        container.add(label1, new Integer(1));
    }

    void ball_(Container container) {
        ImageIcon ball = new ImageIcon("ball_Img.png");
        label2 = new JLabel(ball);
        label2.setBounds(100, 100, 100, 100);
        container.add(label2, new Integer(1));
    }

    void ball_move() {
        int ballX = label2.getX();
        int ballY = label2.getY();
        int ballWidth = label2.getWidth();
        int ballHeight = label2.getHeight();
    
        int baseX = label1.getX();
        int baseY = label1.getY();
        int baseWidth = label1.getWidth();
        int baseHeight = label1.getHeight();
    
        int frameWidth = getContentPane().getWidth();
        int frameHeight = getContentPane().getHeight();
    
        int newX = ballX + xVel;
        int newY = ballY + yVel;
    
        if (newX < 0 || newX + ballWidth > frameWidth) {
            xVel = -xVel;
            newX = Math.max(0, Math.min(frameWidth - ballWidth, newX));
        }
    
        if (newY < 0 || newY + ballHeight > frameHeight) {
            if (newY + ballHeight > frameHeight) {
                gameOver_();
                return;
            }
            yVel = -yVel;
            newY = Math.max(0, Math.min(frameHeight - ballHeight, newY));
        }
    
        if (newY + ballHeight >= baseY && ballY <= baseY + baseHeight && newX + ballWidth >= baseX
                && ballX <= baseX + baseWidth) {
            yVel = -yVel;
            newY = baseY - ballHeight;
            points++;

            if (points % 10 == 0) {
                xVel += (xVel > 0) ? 2 : -2; 
                yVel += (yVel > 0) ? 2 : -2; 
                level++;
            }
        }
        label2.setLocation(newX, newY);
    }
    
    public void score_board(Container container) {
        JLabel Jl = new JLabel();
        Jl.setBounds(10, 10, 100, 25);
        Jl.setForeground(Color.WHITE);
        Jl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        container.add(Jl, new Integer(1));

        JLabel Jl2 = new JLabel();
        Jl2.setBounds(105, 10, 100, 25);
        Jl2.setForeground(Color.WHITE);
        Jl2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        container.add(Jl2, new Integer(1));

        Thread th = new Thread(() -> {
            while (true) {
                Jl.setText("Score: " + points);
                Jl2.setText("Level: "+ level);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
    }

    public void gameOver_() {
        timer.stop();
        //JOptionPane.showMessageDialog(this, "--Game Over-- \nTotal Score: " + points);
        GameOverScreen go = new GameOverScreen();
        go.setVisible(true);
        this.setVisible(false);
        //System.exit(ABORT);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int x = label1.getX();
        int y = label1.getY();
        int step = 15;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (x - step >= 5) {
                    label1.setLocation(x - step, y);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (x + label1.getWidth() + step <= getContentPane().getWidth()) {
                    label1.setLocation(x + step, y);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this implementation
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this implementation
    }

    public static void main(String[] args) {
        new GameMainFrame();
    }
}
