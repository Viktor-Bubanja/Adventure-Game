import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CiyGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CiyGUI window = new CiyGUI();
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
	public CiyGUI() {
		initialize();
	}
	
	public static void enterDistrict(String currentLocation) {
		if (currentLocation == "SHOP") {
			ShopGUI shop = new ShopGUI();
		} else if (currentLocation == "POWERUPDEN") {
			
		} else if (currentLocation == "HOSPITAL") {
			
		} else if (currentLocation == "VILLAINSLAIR") {
			
		} else if (currentLocation == "HOMEBASE") {

			
		}
		//moveBackToHome();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		int currentPosition = 0;
		List<String> positions = new ArrayList<String>();
		positions.add("SHOP");
		positions.add("POWERUPDEN");
		positions.add("HOSPITAL");
		positions.add("VILLAINSLAIR");
		Collections.shuffle(positions);
		positions.add(0,"HOMEBASE");
		String currentLocation = "HOMEBASE";
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 658, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton westButton = new JButton("West");
		westButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(4);
				enterDistrict(currentLocation);
				System.out.println(currentLocation);
				if (currentLocation == "SHOP") {
					ShopGUI shopGUI = new ShopGUI();
					shopGUI.NewScreen();
				}
			}
		});
		westButton.setBounds(59, 172, 120, 33);
		frame.getContentPane().add(westButton);
		
		JButton eastButton = new JButton("East");
		eastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(2);
				enterDistrict(currentLocation);
				System.out.println(currentLocation);
				if (currentLocation == "SHOP") {
					ShopGUI shopGUI = new ShopGUI();
					shopGUI.NewScreen();
				}
			}
		});
		eastButton.setBounds(463, 176, 117, 25);
		frame.getContentPane().add(eastButton);
		
		JButton northButton = new JButton("North");
		northButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(1);
				enterDistrict(currentLocation);
				System.out.println(currentLocation);
				if (currentLocation == "SHOP") {
					ShopGUI shopGUI = new ShopGUI();
					shopGUI.NewScreen();
				}
			}
		});
		northButton.setBounds(273, 53, 117, 25);
		frame.getContentPane().add(northButton);
		
		JButton southButton = new JButton("South");
		southButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentLocation = positions.get(3);
				enterDistrict(currentLocation);
				System.out.println(currentLocation);
				if (currentLocation == "SHOP") {
					ShopGUI shopGUI = new ShopGUI();
					shopGUI.NewScreen();
				}
			}
		});
		southButton.setBounds(273, 309, 117, 25);
		frame.getContentPane().add(southButton);
		
		JLabel lblHomeBase = new JLabel("Home Base");
		lblHomeBase.setBounds(273, 181, 110, 15);
		frame.getContentPane().add(lblHomeBase);
	}
}
