import java.awt.EventQueue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DiceGame{
	private String name;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiceGame window = new DiceGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DiceGame() {
		initialize();
	}
	
	public String getName() {
		return name;
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		JLabel labelRoll = new JLabel("0");
		labelRoll.setBounds(177, 107, 70, 15);
		frame.getContentPane().add(labelRoll);
		JButton buttonRollDice = new JButton("Roll Dice!");
		buttonRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random random = new Random();
				int roll = random.nextInt(6) + 1;
				labelRoll.setText(Integer.toString(roll));
				
			}
		});
		buttonRollDice.setBounds(149, 167, 117, 25);
		frame.getContentPane().add(buttonRollDice);
	}
}
