import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;


public class LairGUI {
	/**
	 * Attributes:
	 * JFrame for the Lair GUI.
	 * The city the team is currently in.
	 * The team.
	 * The game environment handling the game.
	 */
	private JFrame lairFrame;
	private CityGUI cityGui;
	private Team team;
	private GameEnvironment gameEnvironment;

	/**
	 * Makes the lair window visible when first initialized.
	 */
	public void makeVisible() {
		this.lairFrame.setVisible(true);
	}

	/**
	 * Calls initialize()
	 * @param Team teamInput
	 * @param GameEnvironment gameEnvironmentInput
	 * @param CityGUI cityGuiInput
	 */
	public LairGUI(Team teamInput, GameEnvironment gameEnvironmentInput, CityGUI cityGuiInput) {
		team = teamInput;
		gameEnvironment = gameEnvironmentInput;
		cityGui = cityGuiInput;
		initialize();
	}
	/**
	 * If the team chooses to go back to the city, makes city visible again and closes the lair window.
	 */
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
		lairFrame.setBounds(100, 100, 1200, 800);
		lairFrame.getContentPane().setLayout(null);
		
		int currentCity = gameEnvironment.getCurrentCityIndex();
		Villain villain = gameEnvironment.getVillains().get(currentCity); //gets the villain corresponding to the current city.
		
		JButton enterBattleButton = new JButton("Fight!");
		enterBattleButton.setBounds(510, 450, 180, 60);
		enterBattleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.openBattleWindow(team, cityGui);
				lairFrame.dispose();
			}
		});
		lairFrame.getContentPane().add(enterBattleButton);
		
		JButton backToHomeBaseButton = new JButton("Back to Home base");
		backToHomeBaseButton.setBounds(980, 680, 180, 60);
		backToHomeBaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		lairFrame.getContentPane().add(backToHomeBaseButton);
		
		JLabel toFightLabel = new JLabel("The Villain you will be fighting is:");
		toFightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		toFightLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		toFightLabel.setBounds(425, 265, 350, 68);
		lairFrame.getContentPane().add(toFightLabel);
		
		JLabel VillainLabel = new JLabel("");
		VillainLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		VillainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		VillainLabel.setText(villain.getName());
		VillainLabel.setBounds(475, 345, 250, 50);
		lairFrame.getContentPane().add(VillainLabel);
		
		JLabel lairTitleLabel = new JLabel("Villain's Lair");
		lairTitleLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		lairTitleLabel.setBounds(450, 40, 300, 100);
		lairFrame.getContentPane().add(lairTitleLabel);
		
		JLabel backgroundPic = new JLabel("");
		backgroundPic.setIcon(new ImageIcon(ShopGUI.class.getResource("/Images/notsodarkScroll.jpg")));
		backgroundPic.setBounds(0, 0, 1200, 800);
		lairFrame.getContentPane().add(backgroundPic);
		
	}
}
