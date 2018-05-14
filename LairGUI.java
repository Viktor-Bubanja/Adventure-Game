import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class LairGUI extends JFrame {

	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LairGUI frame = new LairGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LairGUI() {
		setTitle("Villains Lair");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnEnterTheLair = new JButton("Enter the lair!");
		btnEnterTheLair.setBounds(5, 5, 132, 443);
		btnEnterTheLair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battleWindow.NewScreen();
			}
		});
		contentPane.setLayout(null);
		
		contentPane.add(btnEnterTheLair);
		
		JButton btnRunAwaaaaay = new JButton("Run awaaaaay!");
		btnRunAwaaaaay.setBounds(650, 5, 144, 443);
		btnRunAwaaaaay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		contentPane.add(btnRunAwaaaaay);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(5, 448, 789, 21);
		contentPane.add(textPane);
		
		JTextPane txtpnYouAreAbout = new JTextPane();
		txtpnYouAreAbout.setBounds(137, 5, 513, 443);
		txtpnYouAreAbout.setEditable(false);
		txtpnYouAreAbout.setText("You are about to enter the villains lair\n would you like too....");
		contentPane.add(txtpnYouAreAbout);
	}

}
