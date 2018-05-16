import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class PaperScissorsRockGUI {

	private JFrame frame;
	private Random random = new Random();
	private int numVillainWon = 0;
	private int numHeroWon = 0;
	private HandSign herosMove;
	private HandSign villainsMove;
	private JLabel lblPaperScissorsRock = new JLabel("Paper Scissors Rock");
	private JLabel winOrLoseGameLabel = new JLabel("");
	private JLabel lblVillainGot = new JLabel("Villain got:");
	private JLabel villainsMoveLabel = new JLabel("");;
	private JLabel winOrLoseRoundLabel = new JLabel("");


	//Launch the application.
	 
	public static void NewScreen() {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaperScissorsRockGUI window = new PaperScissorsRockGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	private int returnRoundResult(HandSign herosMove, HandSign villainsMove) {
		int loss = 0;
		int win = 1;
		int tie = 2;

        if (villainsMove == herosMove) {
            return tie;
        } else if (villainsMove == HandSign.SCISSORS && herosMove == HandSign.PAPER) {
        	numVillainWon++;
            return loss;
        } else if (villainsMove == HandSign.PAPER && herosMove == HandSign.ROCK) {
        	numVillainWon++;
            return loss;
        } else if (villainsMove == HandSign.ROCK && herosMove == HandSign.SCISSORS) {
        	numVillainWon++;
            return loss;
        } else {
            System.out.println("You won!");
            numHeroWon++;
            return win;
        }
	}
	
	private String returnRoundLabelText(int roundResult) {
		String labelText;
		if (roundResult == 0) {
			labelText = "You lose this round";
		} else if (roundResult == 1) {
			labelText = "You win this round";
		} else {
			labelText = "You draw this round";
		}
		return labelText;
	}
	

	//Create the application.
	 
	public PaperScissorsRockGUI() {
		initialize();
	}
	 //Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		lblVillainGot.setBounds(51, 121, 87, 15);
		frame.getContentPane().add(lblVillainGot);
		
		
		villainsMoveLabel.setBounds(178, 142, 70, 15);
		frame.getContentPane().add(villainsMoveLabel);
		
		
		winOrLoseRoundLabel.setBounds(349, 142, 70, 15);
		frame.getContentPane().add(winOrLoseRoundLabel);
		

		
		
		lblPaperScissorsRock.setBounds(139, 12, 151, 15);
		frame.getContentPane().add(lblPaperScissorsRock);
		
		JButton paperButton = new JButton("Paper");
		paperButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked(0);
			}
		});
		paperButton.setBounds(42, 64, 96, 25);
		frame.getContentPane().add(paperButton);
		
		JButton scissorsButton = new JButton("Scissors");
		scissorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked(1);
			}
		});
		scissorsButton.setBounds(167, 64, 96, 25);
		frame.getContentPane().add(scissorsButton);
		
		JButton rockButton = new JButton("Rock");
		rockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked(2);
			}
		});
		rockButton.setBounds(298, 64, 96, 25);
		frame.getContentPane().add(rockButton);
		
		
		winOrLoseGameLabel.setFont(new Font("Dialog", Font.BOLD, 55));
		winOrLoseGameLabel.setBounds(12, 39, 424, 165);
		winOrLoseGameLabel.setVisible(false);
		frame.getContentPane().add(winOrLoseGameLabel);
	}
	private void buttonClicked(int num) {
		herosMove = HandSign.parseType(num);
		int aRandomNumber = random.nextInt(3);
		villainsMove = HandSign.parseType(aRandomNumber);
		villainsMoveLabel.setText(villainsMove.toString());
		int roundResult = returnRoundResult(herosMove, villainsMove);
		if (numHeroWon == 3) {
			winOrLoseGameLabel.setText("You win!");
			winOrLoseGameLabel.setVisible(true);
		} else if (numVillainWon == 3) {
			winOrLoseGameLabel.setText("You Lose!");
			winOrLoseGameLabel.setVisible(true);
		}
		winOrLoseRoundLabel.setText(returnRoundLabelText(roundResult));
	}
}

