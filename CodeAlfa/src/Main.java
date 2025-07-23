

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JOptionPane;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	String[] questions = {
			"What is the capital of France?",
			"Which is the largest desert in the world?",
			"Who is the founder of Microsoft?",
			"What is the currency of the United Kingdom?",
			"Which gas do plants absorb?",
			"What is the smallest continent?",
			"Which country has the most population?",
			"Which element has the symbol ‘O’?",
			"What is the largest mammal?",
			"What is the fastest land animal?"
	};

	String[][] options = {
			{"Paris", "Berlin", "London", "Madrid"},
			{"Gobi", "Kalahari", "Sahara", "Arabian"},
			{"Jobs", "Gates", "Bezos", "Zuckerberg"},
			{"Euro", "Pound", "Dollar", "Rupee"},
			{"Oxygen", "Carbon", "Nitrogen", "Hydrogen"},
			{"Asia", "Europe", "Australia", "Africa"},
			{"India", "USA", "China", "Russia"},
			{"Oxygen", "Gold", "Iron", "Zinc"},
			{"Elephant", "Bluewhale", "Shark", "Giraffe"},
			{"Tiger", "Horse", "Lion", "Cheetah"}
	};

	String[] answers = {
			"Paris",
			"Sahara",
			"Gates",
			"Pound",
			"Carbon",
			"Australia",
			"China",
			"Oxygen",
			"Bluewhale",
			"Cheetah"
	};

	int count = 0;
	int score = 0;

	private JTextPane questionText;
	private JCheckBox[] checkBoxes = new JCheckBox[4];
	private JButton submitBtn;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Main frame = new Main();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Student Quiz");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0, 20, 61));

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
				"Student Quiz",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				new Font("Segoe UI", Font.BOLD, 14),
				new Color(41, 128, 185)
		));
		panel.setBounds(97, 80, 595, 400);
		panel.setBackground(new Color(0, 20, 61));
		panel.setLayout(null);
		contentPane.add(panel);

		questionText = new JTextPane();
		questionText.setBounds(50, 50, 490, 100);
		questionText.setEditable(false);
		questionText.setForeground(new Color(0, 255, 0));
		questionText.setFont(new Font("Arial", Font.PLAIN, 20));
		questionText.setBackground(new Color(0, 20, 61));
		panel.add(questionText);

		
		for (int i = 0; i < 4; i++) {
			checkBoxes[i] = new JCheckBox();
			checkBoxes[i].setBounds(50 + i * 130, 180, 120, 25);
			checkBoxes[i].setBackground(new Color(0, 20, 61));
			checkBoxes[i].setForeground(new Color(0, 255, 0));
			panel.add(checkBoxes[i]);
		}

		submitBtn = new JButton("Submit");
		submitBtn.setBounds(400, 320, 130, 30);
		submitBtn.setBackground(new Color(0, 100, 0));
		submitBtn.setForeground(Color.WHITE);
		panel.add(submitBtn);

	
		submitBtn.addActionListener(e -> checkAnswer());

		
		loadQuestion();
	}

	
	private void loadQuestion() {
		if (count < questions.length) {
			questionText.setText((count + 1) + ". " + questions[count]);
			for (int i = 0; i < 4; i++) {
				checkBoxes[i].setText(options[count][i]);
				checkBoxes[i].setSelected(false);
			}
		} else {
			showResult();
		}
	}


	private void checkAnswer() {
		String selected = null;
		for (JCheckBox box : checkBoxes) {
			if (box.isSelected()) {
				if (selected != null) {
					JOptionPane.showMessageDialog(this, "Please select only one option.");
					return;
				}
				selected = box.getText();
			}
		}

		if (selected == null) {
			JOptionPane.showMessageDialog(this, "Please select an option.");
			return;
		}

		if (selected.equals(answers[count])) {
			score++;
		}

		count++;
		loadQuestion();
	}

	
	private void showResult() {
		JOptionPane.showMessageDialog(this,
				"Quiz Completed!\nYour Score: " + score + " out of " + questions.length,
				"Result",
				JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
}
