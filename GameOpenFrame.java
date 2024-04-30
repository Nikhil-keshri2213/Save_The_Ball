import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOpenFrame extends JFrame implements ActionListener {
    GameOpenFrame() {
        setTitle("Save The Ball");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(800, 640);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        addBackgroundImage(layeredPane);
        start_button(layeredPane);
        setVisible(true);
    }

    void start_button(Container container){
        JButton startButton = new JButton("Let's Go");
        startButton.setBounds(335, 450, 150, 50);
        startButton.setFont(new Font(Font.DIALOG,Font.BOLD,28));
        startButton.setForeground(Color.white);
        startButton.setBackground(new Color(255, 155, 55));
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        startButton.addActionListener(this); 
        container.add(startButton, new Integer(1));
    }

    void addBackgroundImage(Container container) {
        ImageIcon backgroundImage = new ImageIcon("picture_open.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 800, 610);
        container.add(backgroundLabel, new Integer(0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Let's Go")) {
            dispose();
            GameMainFrame g = new GameMainFrame();
            g.setVisible(true);
        }  
    }

    public static void main(String[] args) {
        new GameOpenFrame();
    }
}