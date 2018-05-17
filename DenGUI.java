import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DenGUI {

	private JFrame denFrame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DenGUI window = new DenGUI();
					window.denFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DenGUI() {
		denFrame = new JFrame();
		denFrame.setTitle("Hospital");
		denFrame.setBounds(100, 100, 745, 408);
		denFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		denFrame.getContentPane().setLayout(null);
		
		JLabel lblWhatsUppppIm = new JLabel("Whats upppp im a place to use your powerups");
		lblWhatsUppppIm.setBounds(12, 27, 369, 79);
		denFrame.add(lblWhatsUppppIm);
		
		JButton btnClose = new JButton("Back to home base");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CityGUI.CityScreen.setVisible(true);
				denFrame.dispose();
			}
		});
		btnClose.setBounds(254, 237, 168, 25);
		denFrame.add(btnClose);
	}
}
