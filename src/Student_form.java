import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Student_form extends JFrame {

	private JPanel contentPane;
	private JTextField unametxt;
	private JTextField usntxt;
	private JPasswordField passtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_form frame = new Student_form();
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
	public Student_form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ob=new Login();
				Student_form.this.dispose();
				ob.setVisible(true);
			}
		});
		back.setForeground(Color.WHITE);
		back.setBackground(Color.RED);
		back.setFont(new Font("Bell MT", Font.BOLD, 15));
		back.setBounds(392, 0, 83, 20);
		desktopPane.add(back);
		
		JLabel main_title = new JLabel("Student Form For Library Entry");
		main_title.setFont(new Font("Georgia", Font.PLAIN, 20));
		main_title.setBounds(83, 10, 299, 25);
		desktopPane.add(main_title);
		
		JLabel info1 = new JLabel("Enter a UserName :");
		info1.setFont(new Font("Georgia", Font.PLAIN, 15));
		info1.setBounds(10, 45, 150, 18);
		desktopPane.add(info1);
		
		unametxt = new JTextField();
		unametxt.setFont(new Font("Arial", Font.PLAIN, 15));
		unametxt.setBounds(10, 67, 444, 25);
		desktopPane.add(unametxt);
		unametxt.setColumns(10);
		
		JLabel info2 = new JLabel("Enter Your University Seat Number :");
		info2.setFont(new Font("Georgia", Font.PLAIN, 15));
		info2.setBounds(10, 102, 273, 18);
		desktopPane.add(info2);
		
		usntxt = new JTextField();
		usntxt.setFont(new Font("Arial", Font.PLAIN, 15));
		usntxt.setColumns(10);
		usntxt.setBounds(10, 124, 444, 25);
		desktopPane.add(usntxt);
		
		JLabel info3 = new JLabel("Enter a suitable Password : (Check to make visible)");
		info3.setFont(new Font("Georgia", Font.PLAIN, 15));
		info3.setBounds(10, 159, 356, 18);
		desktopPane.add(info3);
		
		passtxt = new JPasswordField();
		passtxt.setEchoChar('*');
		passtxt.setFont(new Font("Arial", Font.PLAIN, 15));
		passtxt.setColumns(10);
		passtxt.setBounds(10, 181, 413, 25);
		desktopPane.add(passtxt);
		
		JButton create = new JButton("Create Form");
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = unametxt.getText().toUpperCase();
				String usn = usntxt.getText().toUpperCase();
				@SuppressWarnings("deprecation")
				String pass = passtxt.getText().toString().toUpperCase();
				
				if(JOptionPane.showConfirmDialog(null, "Are you sure you wanna Create Form") == JOptionPane.YES_OPTION) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
						Statement st = con.createStatement();
						String query = "insert into lib_login value('"+uname+"','"+usn+"','"+pass+"')";
						st.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "Form created\nLogin Again");
						Login ob=new Login();
						Student_form.this.dispose();
						ob.setVisible(true);
					}catch(Exception ae) {
						JOptionPane.showMessageDialog(null, ae);
					}
				}
			}
		});
		
		JCheckBox visible = new JCheckBox("");
		visible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(visible.isSelected()) {
					passtxt.setEchoChar('\u0000');
				}else {
					passtxt.setEchoChar('*');
				}
			}
		});
		visible.setHorizontalAlignment(SwingConstants.CENTER);
		visible.setBackground(Color.WHITE);
		visible.setBounds(429, 181, 25, 25);
		desktopPane.add(visible);
		create.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		create.setBounds(140, 216, 195, 25);
		desktopPane.add(create);
	}
}
