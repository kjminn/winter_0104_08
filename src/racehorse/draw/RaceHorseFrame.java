package racehorse.draw;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RaceHorseFrame extends JFrame {
	JPanel[] horses = new JPanel[5];
	
	public RaceHorseFrame() {		
		JPanel pan = new JPanel(null);

		
		for (int i = 0; i < horses.length; i++) {
			horses[i] = new Horse("horse"+(i+1));
			horses[i].setLocation(0, 20 + i*85);
			horses[i].setSize(60, 40);
			pan.add(horses[i]);
		}
		
		add(pan, "Center");
		
		setTitle("경주마게임");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50, 30, 600, 500);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new RaceHorseFrame();
	}

}
