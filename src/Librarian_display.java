import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.UIManager;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Librarian_display extends JFrame {

	private JPanel contentPane;
	private static String name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Librarian_display frame = new Librarian_display(name);
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
	public Librarian_display(String name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Librarian Panel Display");
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 10, 273, 43);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setFont(new Font("Georgia", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Librarian_Login ob=new Librarian_Login();
				Librarian_display.this.dispose();
				ob.setVisible(true);
			}
		});
		btnNewButton.setBounds(368, 10, 108, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome");
		lblNewLabel_1.setFont(new Font("Georgia", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(20, 52, 108, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel(name);
		label.setFont(new Font("Georgia", Font.PLAIN, 20));
		label.setBounds(118, 52, 376, 20);
		contentPane.add(label);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Book ob=new Add_Book(name);
				ob.setVisible(true);
			}
		});
		btnAddBook.setFont(new Font("Georgia", Font.PLAIN, 20));
		btnAddBook.setBounds(24, 105, 170, 35);
		contentPane.add(btnAddBook);
		
		JButton btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Isbn = JOptionPane.showInputDialog(btnDeleteBook, "Enter the ISBN of the book");
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
					Statement st = con.createStatement();
					String query = "delete from lib_books where ISBN ='"+Isbn+"'";
					st.executeUpdate(query);
					con.close();
				}catch(Exception ae) {
					JOptionPane.showMessageDialog(null, ae);
				}
			}
		});
		btnDeleteBook.setFont(new Font("Georgia", Font.PLAIN, 20));
		btnDeleteBook.setBounds(259, 105, 170, 35);
		contentPane.add(btnDeleteBook);
		
		JButton btnSearchBook = new JButton("Search Student");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search_Student ob=new Search_Student();
				ob.setVisible(true);
			}
		});
		btnSearchBook.setFont(new Font("Georgia", Font.PLAIN, 20));
		btnSearchBook.setBounds(24, 150, 170, 35);
		contentPane.add(btnSearchBook);
		
		JButton btnFineCheck = new JButton("Fine Check");
		btnFineCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Finecheck ob=new Finecheck();
				ob.setVisible(true);
			}
		});
		btnFineCheck.setFont(new Font("Georgia", Font.PLAIN, 20));
		btnFineCheck.setBounds(259, 150, 170, 34);
		contentPane.add(btnFineCheck);
		
		JLabel lblThisApplicationIs = new JLabel("This Application is Made by Prateek Dey");
		lblThisApplicationIs.setEnabled(false);
		lblThisApplicationIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblThisApplicationIs.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblThisApplicationIs.setBounds(10, 213, 466, 24);
		contentPane.add(lblThisApplicationIs);
	}
}
