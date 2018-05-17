import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LairGUI {

	private JFrame lairFrame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LairGUI window = new LairGUI();
					window.lairFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LairGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		lairFrame = new JFrame();
		lairFrame.setTitle("Villains Lair");
		lairFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lairFrame.setBounds(100, 100, 801, 500);
		lairFrame.getContentPane().setLayout(null);
		
		int currentCity = GameEnvironment.getCurrentCity();
		Villain villain = GameEnvironment.getVillains().get(currentCity);
		
		JButton btnEnterTheLair = new JButton("Enter the lair!");
		btnEnterTheLair.setBounds(5, 152, 149, 68);
		btnEnterTheLair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BattleWindow battleWindow = new BattleWindow(villain);
				battleWindow.NewScreen(villain);
			}
		});
		lairFrame.getContentPane().add(btnEnterTheLair);
		
		JButton btnRunAwaaaaay = new JButton("Run awaaaaay!");
		btnRunAwaaaaay.setBounds(591, 171, 173, 90);
		btnRunAwaaaaay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lairFrame.dispose();
				CityGUI.CityScreen.setVisible(true);
			}
		});
		lairFrame.getContentPane().add(btnRunAwaaaaay);
		
		JLabel toFightLabel = new JLabel("The Villain you will be fighting issssss....");
		toFightLabel.setBounds(189, 152, 351, 68);
		lairFrame.getContentPane().add(toFightLabel);
		
		JLabel VillainLabel = new JLabel("");
		VillainLabel.setText("" + villain.getName());
		VillainLabel.setBounds(211, 229, 227, 15);
		lairFrame.getContentPane().add(VillainLabel);
	}

}
