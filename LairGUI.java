import java.awt.BorderLayout;
import java.awt.EventQueue;

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
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnEnterTheLair = new JButton("Enter the lair!");
		contentPane.add(btnEnterTheLair, BorderLayout.WEST);
		
		JButton btnRunAwaaaaay = new JButton("Run awaaaaay!");
		contentPane.add(btnRunAwaaaaay, BorderLayout.EAST);
		
		JTextPane textPane = new JTextPane();
		contentPane.add(textPane, BorderLayout.SOUTH);
		
		JTextPane txtpnYouAreAbout = new JTextPane();
		txtpnYouAreAbout.setEditable(false);
		txtpnYouAreAbout.setText("You are about to enter the villains lair\n would you like too....");
		contentPane.add(txtpnYouAreAbout, BorderLayout.CENTER);
	}

}
