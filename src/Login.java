import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JButton login;
	private JTextField nametf;
	private JTextField usntf;
	private JPasswordField passtf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setTitle("Library Management System");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JToggleButton librarian_login = new JToggleButton("Librarian Login");
		librarian_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Librarian_Login ob=new Librarian_Login();
				Login.this.dispose();
				ob.setVisible(true);
			}
		});
		menuBar.add(librarian_login);
		librarian_login.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		
		JToggleButton help = new JToggleButton("Help");
		menuBar.add(help);
		help.setFont(new Font("Bookman Old Style", Font.PLAIN, 10));
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Made By Prateek Dey");
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel Main_Title = new JLabel("Login page");
		Main_Title.setHorizontalAlignment(SwingConstants.CENTER);
		Main_Title.setFont(new Font("Georgia", Font.PLAIN, 20));
		Main_Title.setBounds(206, 20, 97, 24);
		panel.add(Main_Title);
		
		nametf = new JTextField();
		nametf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				nametf.setText("");
				nametf.removeFocusListener(this);
			}
		});
		nametf.setHorizontalAlignment(SwingConstants.CENTER);
		nametf.setText("UserName");
		nametf.setFont(new Font("Georgia", Font.PLAIN, 15));
		nametf.setBounds(125, 60, 270, 25);
		panel.add(nametf);
		nametf.setColumns(10);
		
		usntf = new JTextField();
		usntf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				usntf.setText("");
				usntf.removeFocusListener(this);
			}
		});
		usntf.setHorizontalAlignment(SwingConstants.CENTER);
		usntf.setText("University Seat Number");
		usntf.setFont(new Font("Arial", Font.PLAIN, 15));
		usntf.setColumns(10);
		usntf.setBounds(125, 100, 270, 25);
		panel.add(usntf);
		
		passtf = new JPasswordField();
		passtf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passtf.setText("");
				passtf.removeFocusListener(this);
			}
		});
		passtf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String name = nametf.getText().toUpperCase();
					String usn = usntf.getText().toUpperCase();
					@SuppressWarnings("deprecation")
					String pass = passtf.getText().toString().toUpperCase();
					
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
						Statement st = con.createStatement();
						String query = "Select * from lib_login where usn = '"+usn+"'and pass = '"+pass+"'";
						
						ResultSet rs =st.executeQuery(query);
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Login Successfull\nWelcome "+name);
							Login.this.dispose();
							UserDisplay ob=new UserDisplay(name,usn);
							ob.setVisible(true);
						}else
						{
							JOptionPane.showMessageDialog(null, "Incorrect usn or pass");
							nametf.setText("UserName");
							nametf.addFocusListener(new FocusAdapter() {
								@Override
								public void focusGained(FocusEvent e) {
									nametf.setText("");
									nametf.removeFocusListener(this);
								}
							});
							usntf.setText("University Seat Number");
							usntf.addFocusListener(new FocusAdapter() {
								@Override
								public void focusGained(FocusEvent e) {
									usntf.setText("");
									usntf.removeFocusListener(this);
								}
							});
							passtf.setText("Password");
							passtf.addFocusListener(new FocusAdapter() {
								@Override
								public void focusGained(FocusEvent e) {
									passtf.setText("");
									passtf.removeFocusListener(this);
								}
							});
						}
						con.close();
						
					}catch(Exception ae) {
						JOptionPane.showMessageDialog(null, ae);
					}
				}
			}
		});
		passtf.setText("Password");
		passtf.setHorizontalAlignment(SwingConstants.CENTER);
		passtf.setToolTipText("");
		passtf.setFont(new Font("Georgia", Font.PLAIN, 15));
		passtf.setColumns(10);
		passtf.setBounds(125, 140, 270, 25);
		panel.add(passtf);
		
		login = new JButton("Login !");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nametf.getText().toUpperCase();
				String usn = usntf.getText().toUpperCase();
				@SuppressWarnings("deprecation")
				String pass = passtf.getText().toString().toUpperCase();
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
					Statement st = con.createStatement();
					String query = "Select * from lib_login where usn = '"+usn+"'and pass = '"+pass+"'";
					
					ResultSet rs =st.executeQuery(query);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Login Successfull\nWelcome "+name);
						Login.this.dispose();
						UserDisplay ob=new UserDisplay(name,usn);
						ob.setVisible(true);
					}else
					{
						JOptionPane.showMessageDialog(null, "Incorrect usn or pass");
						nametf.setText("UserName");
						nametf.addFocusListener(new FocusAdapter() {
							@Override
							public void focusGained(FocusEvent e) {
								nametf.setText("");
								nametf.removeFocusListener(this);
							}
						});
						usntf.setText("University Seat Number");
						usntf.addFocusListener(new FocusAdapter() {
							@Override
							public void focusGained(FocusEvent e) {
								usntf.setText("");
								usntf.removeFocusListener(this);
							}
						});
						passtf.setText("Password");
						passtf.addFocusListener(new FocusAdapter() {
							@Override
							public void focusGained(FocusEvent e) {
								passtf.setText("");
								passtf.removeFocusListener(this);
							}
						});
					}
					con.close();
					
				}catch(Exception ae) {
					JOptionPane.showMessageDialog(null, ae);
				}
			}
		});
		login.setFont(new Font("Georgia", Font.PLAIN, 18));
		login.setBounds(200, 180, 120, 25);
		panel.add(login);
		
		JButton Reset = new JButton("Reset");
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nametf.setText("UserName");
				nametf.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						nametf.setText("");
						nametf.removeFocusListener(this);
					}
				});
				usntf.setText("University Seat Number");
				usntf.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						usntf.setText("");
						usntf.removeFocusListener(this);
					}
				});
				passtf.setText("Password");
				passtf.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						passtf.setText("");
						passtf.removeFocusListener(this);
					}
				});
			}
		});
		Reset.setFont(new Font("Georgia", Font.PLAIN, 15));
		Reset.setBounds(429, 10, 87, 20);
		panel.add(Reset);
		
		JButton btnNewStudentForm = new JButton("New Student Form");
		btnNewStudentForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student_form ob=new Student_form();
				Login.this.dispose();
				ob.setVisible(true);
				
			}
		});
		btnNewStudentForm.setFont(new Font("Georgia", Font.PLAIN, 18));
		btnNewStudentForm.setBounds(125, 225, 270, 25);
		panel.add(btnNewStudentForm);
	}
}
