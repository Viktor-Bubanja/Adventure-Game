import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class battleWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					battleWindow window = new battleWindow();
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
	public battleWindow() {
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 724, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		GameEnvironment gameEnvironment = new GameEnvironment();
		int currentCity = gameEnvironment.getCurrentCity();
		Villain villain = gameEnvironment.getVillains().get(currentCity);
		
		JLabel lblTaunt = new JLabel(villain.getTaunt());
		lblTaunt.setBounds(303, 62, 70, 15);
		frame.getContentPane().add(lblTaunt);
		
		JLabel lblName = new JLabel(villain.getName());
		lblName.setBounds(303, 35, 70, 15);
		frame.getContentPane().add(lblName);
		
		JComboBox comboBox = new JComboBox(Team.getHeroNames());
		comboBox.setBounds(267, 108, 161, 29);
		frame.getContentPane().add(comboBox);
		
		JLabel lblSelectAHero = new JLabel("Select a hero to battle!");
		lblSelectAHero.setBounds(31, 115, 195, 22);
		frame.getContentPane().add(lblSelectAHero);
	}
}
