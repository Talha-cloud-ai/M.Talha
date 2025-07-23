

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;



public class Quiz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Quiz frame = new Quiz();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Quiz() {
		setTitle("Login Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		setResizable(false);
		setLocationRelativeTo(null);
	
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245)); 
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel titleLabel = new JLabel("Student Login");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(150, 20, 200, 40);
		contentPane.add(titleLabel);

		JLabel lblName = new JLabel("Username:");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblName.setBounds(100, 90, 80, 25);
		contentPane.add(lblName);

		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPassword.setBounds(100, 130, 80, 25);
		contentPane.add(lblPassword);

		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setBounds(190, 90, 200, 25);
		textField.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
		contentPane.add(textField);

		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passwordField.setBounds(190, 130, 200, 25);
		passwordField.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
		contentPane.add(passwordField);

		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLogin.setBackground(new Color(66, 133, 244)); 
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBounds(190, 180, 200, 35);
		btnLogin.setFocusPainted(false);
		btnLogin.setBorder(new RoundBorder(10));
		contentPane.add(btnLogin);
		
		btnLogin.addActionListener(e->{
			String username =textField.getText();
			String password = new String(passwordField.getPassword());
			
			if(username.equals("Admin")&&password.equals("admin123")) {
				new Main().setVisible(true);
				}else {
			        JOptionPane.showMessageDialog(null, "Invalid username or password");
			        setVisible(true);
			        }
		});
		
	}
}


class RoundBorder extends AbstractBorder {
	private int radius;

	public RoundBorder(int radius) {
		this.radius = radius;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(c.getBackground());
		g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
	}
}
