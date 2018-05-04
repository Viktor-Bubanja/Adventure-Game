import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.ImageIcon;

public class GameSetupGUI {

	private JFrame frame;
	private JTextField txtTeamName;
	private JLabel inputCheck;
	private JTextField inputHeroName;
	private String inputHeroType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSetupGUI window = new GameSetupGUI();
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
	public GameSetupGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Main setup frame");
		frame.setBounds(100, 100, 1109, 658);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWhatWouldYou = new JLabel("What would you like your super team to be called?");
		lblWhatWouldYou.setBounds(2, -7, 378, 59);
		frame.getContentPane().add(lblWhatWouldYou);
		
		txtTeamName = new JTextField();
		txtTeamName.setBounds(22, 38, 306, 25);
		txtTeamName.setText("Must be between 2 and 10 chars long!");
		txtTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeamName.setToolTipText("Wowwo cools!");
		frame.getContentPane().add(txtTeamName);
		txtTeamName.setColumns(10);
		
		inputCheck = new JLabel("");
		inputCheck.setBounds(423, 131, 181, 26);
		frame.getContentPane().add(inputCheck);
		
		
		
		JSlider numCities = new JSlider();
		numCities.setMajorTickSpacing(1);
		numCities.setSnapToTicks(true);
		numCities.setPaintTicks(true);
		numCities.setMinimum(3);
		numCities.setMaximum(6);
		numCities.setBounds(379, 46, 200, 16);
		frame.getContentPane().add(numCities);
		
		
		JLabel lblNewLabel = new JLabel("How many cities to explore?");
		lblNewLabel.setBounds(377, 6, 302, 31);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		JLabel label = new JLabel("3             4              5             6");
		label.setBounds(383, 64, 296, 26);
		frame.getContentPane().add(label);
		
		JLabel lblAddUpTo = new JLabel("Pick and name up to 3 heroes to your team");
		lblAddUpTo.setBounds(19, 67, 401, 39);
		frame.getContentPane().add(lblAddUpTo);
		
		/** This is the button to add a new hero **/
		JButton btnAddHero = new JButton("Add hero");
		btnAddHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				inputHeroName.getText();
				
				//heroes.add();
				
				//clear textbox
				
			}
		});
		btnAddHero.setBounds(379, 511, 145, 25);
		frame.getContentPane().add(btnAddHero);
		/**END**/
		
		inputHeroName = new JTextField();
		inputHeroName.setBounds(379, 454, 134, 31);
		frame.getContentPane().add(inputHeroName);
		inputHeroName.setColumns(10);
		
		JLabel picGambler = new JLabel("Pic Gambler");
		picGambler.setBounds(306, 183, 181, 206);
		frame.getContentPane().add(picGambler);
		
		/** Next HEROO BUTTON**/
		JButton btnNextHero = new JButton("Next Hero");
		btnNextHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picMedic.setVisible(false);
				
				
			}
		});
		btnNextHero.setBounds(625, 317, 145, 25);
		frame.getContentPane().add(btnNextHero);
		/**END HERE!!!!**/
		
		JButton btnPreviousHero = new JButton("Previous Hero");
		btnPreviousHero.setBounds(207, 317, 134, 25);
		frame.getContentPane().add(btnPreviousHero);
		
		
		
		
		JLabel picMedic = new JLabel("Medic Pic");
		picMedic.setBounds(379, 183, 181, 206);
		frame.getContentPane().add(picMedic);
		
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game_Environment.setTeamName(txtTeamName.getText());
				
				Game_Environment.setNumberCities(numCities.getValue());
				//Game_Environment.setNumberHeroes(heroes.size());//length of the list of heroes)
				
				System.out.println(Game_Environment.getTeamName());
			}
		});
		btnDone.setBounds(841, 570, 117, 25);
		frame.getContentPane().add(btnDone);
	}
}
