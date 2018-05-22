import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LairGUI {

	private JFrame lairFrame;
	private CityGUI cityGui;
	private Team team;
	private GameEnvironment gameEnvironment;

	/**
	 * Launch the application.
	 */

	public void makeVisible() {
		this.lairFrame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public LairGUI(Team teamInput, GameEnvironment gameEnvironmentInput, CityGUI cityGuiInput) {
		team = teamInput;
		gameEnvironment = gameEnvironmentInput;
		cityGui = cityGuiInput;
		initialize();
	}
	
	private void finishedWindow() {
		cityGui.makeCityVisible();
		lairFrame.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		lairFrame = new JFrame();
		lairFrame.setTitle("Villains Lair");
		lairFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lairFrame.setBounds(100, 100, 1000, 700);
		lairFrame.getContentPane().setLayout(null);
		
		int currentCity = gameEnvironment.getCurrentCityIndex();
		Villain villain = gameEnvironment.getVillains().get(currentCity);
		
		JButton btnEnterTheLair = new JButton("Enter the lair!");
		btnEnterTheLair.setBounds(5, 152, 149, 68);
		btnEnterTheLair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.openBattleWindow(team, cityGui);
				lairFrame.dispose();
			}
		});
		lairFrame.getContentPane().add(btnEnterTheLair);
		
		JButton btnRunAwaaaaay = new JButton("Run awaaaaay!");
		btnRunAwaaaaay.setBounds(591, 171, 173, 90);
		btnRunAwaaaaay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		lairFrame.getContentPane().add(btnRunAwaaaaay);
		
		JLabel toFightLabel = new JLabel("The Villain you will be fighting issssss....");
		toFightLabel.setBounds(189, 152, 351, 68);
		lairFrame.getContentPane().add(toFightLabel);
		
		JLabel VillainLabel = new JLabel("");
		VillainLabel.setText(villain.getName());
		VillainLabel.setBounds(211, 229, 306, 150);
		lairFrame.getContentPane().add(VillainLabel);
	}
}
