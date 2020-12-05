import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Librarian_Login extends JFrame {

	private JPanel contentPane;
	private JTextField usntxt;
	private JPasswordField passtxt;
	private JTextField unametxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Librarian_Login frame = new Librarian_Login();
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
	public Librarian_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JToggleButton Login_Stud = new JToggleButton("Student Login");
		Login_Stud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ob=new Login();
				Librarian_Login.this.dispose();
				ob.setVisible(true);
			}
		});
		Login_Stud.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		menuBar.add(Login_Stud);
		
		JToggleButton help = new JToggleButton("Help");
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Identity Hint:\nUserName : Shakuntala Devi\nFaculty Number : 987654321\nPassword : Aambaras");
			}
		});
		help.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		menuBar.add(help);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel info1 = new JLabel("Librarian Login");
		info1.setFont(new Font("Georgia", Font.PLAIN, 25));
		info1.setBounds(165, 10, 180, 30);
		panel.add(info1);
		
		unametxt = new JTextField();
		unametxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				unametxt.setText("");
				unametxt.removeFocusListener(this);
			}
		});
		unametxt.setText("UserName");
		unametxt.setHorizontalAlignment(SwingConstants.CENTER);
		unametxt.setFont(new Font("Georgia", Font.PLAIN, 15));
		unametxt.setBounds(90, 60, 320, 30);
		panel.add(unametxt);
		unametxt.setColumns(10);
		
		usntxt = new JTextField();
		usntxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				usntxt.setText("");
				usntxt.removeFocusListener(this);
			}
		});
		usntxt.setText("Faculty Number");
		usntxt.setHorizontalAlignment(SwingConstants.CENTER);
		usntxt.setFont(new Font("Georgia", Font.PLAIN, 15));
		usntxt.setBounds(90, 110, 320, 30);
		panel.add(usntxt);
		usntxt.setColumns(10);
		
		passtxt = new JPasswordField();
		passtxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passtxt.setText("");
				passtxt.removeFocusListener(this);
			}
		});
		passtxt.setText("Password");
		passtxt.setHorizontalAlignment(SwingConstants.CENTER);
		passtxt.setFont(new Font("Georgia", Font.PLAIN, 15));
		passtxt.setBounds(90, 160, 320, 30);
		panel.add(passtxt);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String uname = unametxt.getText().toUpperCase();
				String usn = usntxt.getText().toUpperCase();
				String pass = passtxt.getText().toString().toUpperCase();
				
				if(uname.equalsIgnoreCase("Shakuntala Devi")&&usn.equalsIgnoreCase("987654321")&&pass.equalsIgnoreCase("Aambaras")) {
					JOptionPane.showMessageDialog(null, "Login Successfull\nWelcome "+uname);
					Librarian_display ob=new Librarian_display("Shankuntala Devi");
					Librarian_Login.this.dispose();
					ob.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Login Unsuccessfull");
					unametxt.setText("UserName");
					unametxt.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							unametxt.setText("");
							unametxt.removeFocusListener(this);
						}
					});
					usntxt.setText("Faculty Number");
					usntxt.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							usntxt.setText("");
							usntxt.removeFocusListener(this);
						}
					});
					passtxt.setText("Password");
					passtxt.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							passtxt.setText("");
							passtxt.removeFocusListener(this);
						}
					});
				}
			}
		});
		login.setFont(new Font("Georgia", Font.PLAIN, 20));
		login.setBounds(165, 210, 173, 30);
		panel.add(login);
	}
}
