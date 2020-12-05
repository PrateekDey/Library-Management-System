import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Search_Student extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search_Student frame = new Search_Student();
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
	public Search_Student() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel Header = new JLabel("Search Student Window");
		Header.setFont(new Font("Georgia", Font.PLAIN, 20));
		Header.setBounds(10, 10, 241, 24);
		panel.add(Header);
		
		JLabel lblBelowAllThe = new JLabel("Below All the Students are displayed But you can still search a Particular Student");
		lblBelowAllThe.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblBelowAllThe.setBounds(10, 30, 744, 24);
		panel.add(lblBelowAllThe);
		
		JTextField usnfield = new JTextField();
		usnfield.setFont(new Font("Georgia", Font.PLAIN, 20));
		usnfield.setBounds(10, 65, 450, 30);
		panel.add(usnfield);
		usnfield.setColumns(10);
		
		JButton searchspecific = new JButton("Search Student");
		searchspecific.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usnspecific = usnfield.getText().toUpperCase();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
					Statement st = con.createStatement();
					String query = "select usn,isbn,issue_date,return_date from book_crud where usn = '"+usnspecific+"'";
					
					ResultSet rs =st.executeQuery(query);
					DefaultTableModel tab = (DefaultTableModel)table.getModel();
					tab.setRowCount(0);
					
					while(rs.next()) {
						Object o[]= {rs.getString("usn"),rs.getString("isbn"),rs.getString("Issue_date"),rs.getString("return_date")};
						tab.addRow(o);
					}
					con.close();
				}catch(Exception e1){
					JOptionPane.showMessageDialog(searchspecific, e1);
				}
			}
		});
		searchspecific.setFont(new Font("Georgia", Font.PLAIN, 20));
		searchspecific.setBounds(480, 65, 220, 30);
		panel.add(searchspecific);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 756, 330);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"USN", "ISBN", "Issued Date", "Return Date"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search_Student.this.dispose();
			}
		});
		btnNewButton_1.setBounds(681, 10, 85, 21);
		panel.add(btnNewButton_1);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
			Statement st = con.createStatement();
			String query = "select usn,isbn,issue_date,return_date from book_crud";
			
			ResultSet rs =st.executeQuery(query);
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			tab.setRowCount(0);
			
			while(rs.next()) {
				Object o[]= {rs.getString("usn"),rs.getString("isbn"),rs.getString("issue_date"),rs.getString("return_date")};
				tab.addRow(o);
			}
			con.close();
		}catch(Exception e1){
			JOptionPane.showMessageDialog(this, e1);
		}
	}
}
