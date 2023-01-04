package racehorse.jlabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RaceHorseFrame extends JFrame {
	JLabel[] horses = new JLabel[5];
	HorseThread[] hts = new HorseThread[horses.length];
	int[] winnerIndex = new int[horses.length];
	int index;
	
	public RaceHorseFrame() {		
		JPanel pan = new JPanel(null);
		ImageIcon icon = null;
		JLabel lineLbl = new JLabel(new ImageIcon("images/line.png"));
		lineLbl.setBounds(540, 27, 5, 420);
		JLabel flagLbl = new JLabel(new ImageIcon("images/flag.png"));
		flagLbl.setBounds(530, 5, 20, 27);
		pan.add(lineLbl);
		pan.add(flagLbl);
		
		JButton btnStart = new JButton("게임시작");
		btnStart.addActionListener(btnL);
		
		for (int i = 0; i < horses.length; i++) {
			icon = new ImageIcon("images/small_horse"+(i+1)+".gif");
			horses[i] = new JLabel(icon);
			horses[i].setLocation(0, 50 + i*85);
			horses[i].setSize(60, 40);
			pan.add(horses[i]);
		}
		
		
		add(pan, "Center");
		add(btnStart, "North");
		setTitle("경주마게임");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50, 30, 620, 500);
		setVisible(true);
		setResizable(false);		
	}

	public static void main(String[] args) {
		new RaceHorseFrame();
	}
	
	ActionListener btnL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < horses.length; i++) {
				HorseThread t = new HorseThread(horses[i],"small_horse"+(i+1), "stop_horse"+(i+1), i);
				t.start();
			}
		}
	};

	public class HorseThread extends Thread{
		JLabel lblHorse;
		String moveImageName;
		String stopImageName;
		int randomValue;
		int horseIndex;
		
		public HorseThread(JLabel lblHorse, String moveImageName, String stopImageName, int horseIndex) {
			this.lblHorse = lblHorse;
			this.moveImageName = moveImageName;
			this.stopImageName = stopImageName;
			this.horseIndex = horseIndex;
		}

		@Override
		public void run() {
			while (true) {
				lblHorse.setLocation(lblHorse.getX()+5, lblHorse.getY());
				if(lblHorse.getX()==540) {
					lblHorse.setIcon(new ImageIcon("images/"+stopImageName+".gif"));
					winnerIndex[index++] = horseIndex;
					if(horseIndex == horses.length-1) {
						JOptionPane.showMessageDialog(RaceHorseFrame.this, "축하합니다. "+(winnerIndex[0]+1)+"말이 우승!!!");
						index = 0;
					}
					lblHorse.setLocation(0, lblHorse.getY());
					lblHorse.setIcon(new ImageIcon("images/"+moveImageName+".gif"));
					break;
				}
				try {
					Random random = new Random();
					randomValue = random.nextInt(10);
					sleep(10 * randomValue);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}
	}
}
