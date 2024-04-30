import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameOverScreen extends JFrame implements ActionListener{
    GameOverScreen(){
        setTitle("Save The Ball");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setSize(800, 630);
        setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        addBackgroundImage(layeredPane);
        scoreBoard(layeredPane);
        restart_end(layeredPane);
        setVisible(true);        
    }
    void addBackgroundImage(Container container) {
        ImageIcon backgroundImage = new ImageIcon("outScreen.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 600);
        container.add(backgroundLabel, new Integer(0));
    }
    void scoreBoard(Container container){
        int  i = GameMainFrame.points;
        JLabel sc = new JLabel();
        sc.setText("TOTAL SCORE: "+i);
        sc.setBounds(250, 325, 500, 40);
        sc.setForeground(new Color(255,165,0));
        sc.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        container.add(sc, new Integer(1));
    }

    void restart_end(Container container){
        JButton restart = new JButton("Try Again");
        restart.setBounds(335, 450, 150, 30);
        restart.setFont(new Font(Font.SERIF,Font.BOLD,20));
        restart.setForeground(new Color(0,100,0));
        restart.setBackground(new Color(173, 216, 230)); 
        restart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        restart.addActionListener(this); 
        container.add(restart, new Integer(1));

        JButton endGame = new JButton("End the Game");
        endGame.setBounds(310, 500, 200, 30);
        endGame.setFont(new Font(Font.SERIF,Font.BOLD,20));
        endGame.setForeground(new Color(0,100,0));
        endGame.setBackground(new Color(173, 216, 230)); 
        endGame.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        endGame.addActionListener(this); 
        container.add(endGame, new Integer(1));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Try Again")) {
            dispose();
            GameOpenFrame g = new GameOpenFrame();
            g.setVisible(true);
        }

        if (e.getActionCommand().equals("End the Game")) {
            dispose();
            System.exit(ABORT);
        }
    }
    public static void main(String[] args) {
        new GameOverScreen();
    }
}