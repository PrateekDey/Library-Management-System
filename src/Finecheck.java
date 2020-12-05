import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Finecheck extends JFrame {

	private JPanel contentPane;
	private JTextField reciept;
	private JTextField usn;
	private JTextField isbn;
	private JRadioButton paid;
	private JRadioButton notpaid;
	private String paidoption = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Finecheck frame = new Finecheck();
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
	public Finecheck() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel header = new JLabel("Submit Reciept Number");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setFont(new Font("Consolas", Font.PLAIN, 20));
		header.setBounds(24, 10, 250, 31);
		panel.add(header);
		
		reciept = new JTextField();
		reciept.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				reciept.setText("");
				reciept.setForeground(Color.black);
			}
		});
		reciept.setForeground(Color.LIGHT_GRAY);
		reciept.setText("Enter the Reciept Number");
		reciept.setFont(new Font("Georgia", Font.PLAIN, 18));
		reciept.setBounds(20, 82, 434, 31);
		panel.add(reciept);
		reciept.setColumns(10);
		
		usn = new JTextField();
		usn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				usn.setText("");
				usn.setForeground(Color.black);
			}
		});
		usn.setForeground(Color.LIGHT_GRAY);
		usn.setText("Enter the USN Of the Student");
		usn.setFont(new Font("Georgia", Font.PLAIN, 18));
		usn.setColumns(10);
		usn.setBounds(20, 135, 434, 31);
		panel.add(usn);
		
		isbn = new JTextField();
		isbn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				isbn.setText("");
				isbn.setForeground(Color.black);
			}
		});
		isbn.setForeground(Color.LIGHT_GRAY);
		isbn.setText("Enter the ISBN of the Book");
		isbn.setFont(new Font("Georgia", Font.PLAIN, 18));
		isbn.setColumns(10);
		isbn.setBounds(20, 191, 434, 31);
		panel.add(isbn);
		
		paid = new JRadioButton("Paid");
		paid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(paid.isSelected()) {
					notpaid.setSelected(false);
					paidoption = "Paid";
				}
			}
		});
		paid.setFont(new Font("Arial", Font.PLAIN, 15));
		paid.setBounds(94, 261, 105, 21);
		panel.add(paid);
		
		notpaid = new JRadioButton("Not Paid");
		notpaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(notpaid.isSelected()) {
					paid.setSelected(false);
					paidoption = "Not Paid";
				}
			}
		});
		notpaid.setFont(new Font("Arial", Font.PLAIN, 15));
		notpaid.setBounds(268, 261, 105, 21);
		panel.add(notpaid);
		
		JButton done = new JButton("Done");
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rec = reciept.getText();
				String usnf = usn.getText();
				String isbnf = isbn.getText();
				
				if(rec.isEmpty()||usnf.isEmpty()||isbnf.isEmpty()||paidoption.isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Please Enter all the fields");
				}
				else {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
						Statement st = con.createStatement();
						String query = "insert into fine value('"+rec+"','"+usnf+"','"+isbnf+"','"+paidoption+"')";
						st.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "Receipt Updated");
						con.close();
						
					}catch(Exception ae) {
						JOptionPane.showMessageDialog(null, ae);
					}
					reciept.setForeground(Color.LIGHT_GRAY);
					usn.setForeground(Color.LIGHT_GRAY);
					isbn.setForeground(Color.LIGHT_GRAY);
					reciept.setText("Enter the reciept Number");
					usn.setText("Enter the USN of the Student");
					isbn.setText("Enter the ISBN of the book");
					paid.setSelected(false);
					notpaid.setSelected(false);
				}
			}
		});
		done.setFont(new Font("Tahoma", Font.PLAIN, 20));
		done.setBounds(94, 300, 279, 31);
		panel.add(done);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Finecheck.this.dispose();
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 15));
		back.setBounds(370, 14, 96, 37);
		panel.add(back);
	}
}
