import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class Search extends JFrame {

	private JPanel contentPane;
	private JRadioButton radiobname;
	private JRadioButton radiobauthor;
	private JRadioButton radiobcategory;
	private JRadioButton radioisbn;
	private JLayeredPane changing_panel;
	private JPanel bnamelayer;
	private JPanel bauthorlayer;
	private JPanel bcategorylayer;
	private JPanel isbnlayer;
	private JLabel title1;
	private JLabel sub1;
	private JButton namesearchbtn;
	private JButton displayall1;
	private JButton displayall2;
	private JLabel title2;
	private JLabel sub2;
	private JTextField authorsearchtxt;
	private JButton authorsearchbtn;
	private JButton displayall4;
	private JLabel title4;
	private JLabel sub4;
	private JTextField isbnsearchtxt;
	private JButton isbnsearchbtn;
	private JLabel title3;
	private JLabel sub3;
	private JComboBox categoryselector;
	private JButton displayall3;
	private JButton categorysearchbtn;
	private JTable table;
	private JTextField namesearchtxt;
	private JTextField submitisbn;
	private JTextField submitusn;
	private JButton issue;
	private JLabel footer1;
	private JLabel footer2;
	private static String usn;
	private static String name;
	private JToggleButton back;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search(name,usn);
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
	public void searchName(String Name) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
			Statement st = con.createStatement();
			String query = "select * from lib_books where bname = '"+Name+"'";
			
			ResultSet rs =st.executeQuery(query);
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			tab.setRowCount(0);
			
			while(rs.next()) {
				Object o[]= {rs.getString("BNAME"),rs.getString("BAUTHOR"),rs.getString("BCATEGORY"),rs.getInt("ISBN")};
				tab.addRow(o);
			}
			con.close();
			
		}catch(Exception e1){
			JOptionPane.showMessageDialog(this, e1);
		}
	}
	public void searchAuthor(String Author) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
			Statement st = con.createStatement();
			String query = "select * from lib_books where bauthor = '"+Author+"'";
			
			ResultSet rs =st.executeQuery(query);
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			tab.setRowCount(0);
			
			while(rs.next()) {
				Object o[]= {rs.getString("BNAME"),rs.getString("BAUTHOR"),rs.getString("BCATEGORY"),rs.getInt("ISBN")};
				tab.addRow(o);
			}
			con.close();
		}catch(Exception e1){
			JOptionPane.showMessageDialog(this, e1);
		}
	}
	public void searchCategory(String Category) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
			Statement st = con.createStatement();
			String query = "select * from lib_books where bcategory = '"+Category+"'";
			
			ResultSet rs =st.executeQuery(query);
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			tab.setRowCount(0);
			
			while(rs.next()) {
				Object o[]= {rs.getString("BNAME"),rs.getString("BAUTHOR"),rs.getString("BCATEGORY"),rs.getInt("ISBN")};
				tab.addRow(o);
			}
			con.close();
		}catch(Exception e1){
			JOptionPane.showMessageDialog(this, e1);
		}
	}
	public void searchISBN(String ISBN) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
			Statement st = con.createStatement();
			String query = "select * from lib_books where isbn = '"+ISBN+"'";
			
			ResultSet rs =st.executeQuery(query);
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			tab.setRowCount(0);
			
			while(rs.next()) {
				Object o[]= {rs.getString("BNAME"),rs.getString("BAUTHOR"),rs.getString("BCATEGORY"),rs.getInt("ISBN")};
				tab.addRow(o);
			}
			con.close();
		}catch(Exception e1){
			JOptionPane.showMessageDialog(this, e1);
		}
	}
	public void displayAll() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
			Statement st = con.createStatement();
			String query = "select * from lib_books";
			
			ResultSet rs =st.executeQuery(query);
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			tab.setRowCount(0);
			
			while(rs.next()) {
				Object o[]= {rs.getString("BNAME"),rs.getString("BAUTHOR"),rs.getString("BCATEGORY"),rs.getInt("ISBN")};
				tab.addRow(o);
			}
			con.close();
		}catch(Exception e1){
			JOptionPane.showMessageDialog(this, e1);
		}
	}
	
	public void issue(String ISBN, String usn) {
		try {
			//date generation
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			Calendar current = Calendar.getInstance();
			String issue_date = sdf.format(current.getTime()).toString();
			current.add(Calendar.DAY_OF_MONTH, 10);
			String return_date = sdf.format(current.getTime()).toString();
			
			//Connection established
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
			Statement st = con.createStatement();
			
			//checking for the number of the book for the user
			String tempquery = "select * from book_crud where usn = '"+usn+"'";
			ResultSet rs = st.executeQuery(tempquery);
			int count = 0;
			while(rs.next()) {
				count++;
			}
			if(count == 3) {
				//display message more than two books
				JOptionPane.showMessageDialog(issue, "You Have Already Issued 3 book\n To Issue new Books return Existing book");
				submitisbn.setText("ISBN code");
			}
			else {
				//if allowed then issue new book
			String query = "insert into book_crud value('"+usn+"','"+ISBN+"','"+issue_date+"','"+return_date+"')";
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(issue, "Book Issued");
			submitisbn.setText("ISBN code");
			submitisbn.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					submitisbn.setText("");
					submitisbn.removeFocusListener(this);
				}
			});
			}
			con.close();
			
		}catch(Exception e1){
			JOptionPane.showMessageDialog(this, e1);
		}
	}
	public void switchpanels(JPanel panel) {
		changing_panel.removeAll();
		changing_panel.add(panel);
		changing_panel.repaint();
		changing_panel.revalidate();
		}
	
	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	public Search(String name,String usn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel Main_frame = new JPanel();
		contentPane.add(Main_frame, BorderLayout.CENTER);
		Main_frame.setLayout(null);
		
		JLabel main_title = new JLabel("Search Window");
		main_title.setFont(new Font("Georgia", Font.PLAIN, 25));
		main_title.setBounds(10, 10, 172, 29);
		Main_frame.add(main_title);
		
		radiobname = new JRadioButton("Search By Book Name");
		radiobname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radiobname.isSelected()) {
					radiobauthor.setSelected(false);
					radiobcategory.setSelected(false);
					radioisbn.setSelected(false);
					switchpanels(bnamelayer);
				}
			}
		});
		radiobname.setFont(new Font("Georgia", Font.PLAIN, 15));
		radiobname.setBounds(10, 50, 175, 27);
		Main_frame.add(radiobname);
		
		radiobauthor = new JRadioButton("Search By Book Author");
		radiobauthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radiobauthor.isSelected()) {
					radiobname.setSelected(false);
					radiobcategory.setSelected(false);
					radioisbn.setSelected(false);
					switchpanels(bauthorlayer);
					}
			}
		});
		radiobauthor.setFont(new Font("Georgia", Font.PLAIN, 15));
		radiobauthor.setBounds(225, 50, 183, 27);
		Main_frame.add(radiobauthor);
		
		radiobcategory = new JRadioButton("Search By Book Category");
		radiobcategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radiobcategory.isSelected()) {
					radiobauthor.setSelected(false);
					radiobname.setSelected(false);
					radioisbn.setSelected(false);
					switchpanels(bcategorylayer);
				}
			}
		});
		radiobcategory.setFont(new Font("Georgia", Font.PLAIN, 15));
		radiobcategory.setBounds(455, 50, 195, 27);
		Main_frame.add(radiobcategory);
		
		radioisbn = new JRadioButton("Search By Book ISBN");
		radioisbn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioisbn.isSelected()) {
					radiobauthor.setSelected(false);
					radiobcategory.setSelected(false);
					radiobname.setSelected(false);
					switchpanels(isbnlayer);
				}
			}
		});
		radioisbn.setFont(new Font("Georgia", Font.PLAIN, 15));
		radioisbn.setBounds(691, 50, 175, 27);
		Main_frame.add(radioisbn);
		
		changing_panel = new JLayeredPane();
		changing_panel.setBounds(10, 95, 856, 75);
		Main_frame.add(changing_panel);
		changing_panel.setLayout(new CardLayout(0, 0));
		
		bnamelayer = new JPanel();
		changing_panel.add(bnamelayer, "name_998299636469300");
		bnamelayer.setLayout(null);
		
		title1 = new JLabel("Search Book");
		title1.setFont(new Font("Georgia", Font.PLAIN, 18));
		title1.setBounds(10, 10, 128, 21);
		bnamelayer.add(title1);
		
		sub1 = new JLabel("(Enter the correct name of the book which you want to search else click Display All)");
		sub1.setEnabled(false);
		sub1.setFont(new Font("Georgia", Font.PLAIN, 15));
		sub1.setBounds(148, 10, 621, 21);
		bnamelayer.add(sub1);
		
		namesearchtxt = new JTextField();
		namesearchtxt.setFont(new Font("Georgia", Font.PLAIN, 15));
		namesearchtxt.setBounds(10, 41, 537, 24);
		bnamelayer.add(namesearchtxt);
		namesearchtxt.setColumns(10);
		
		namesearchbtn = new JButton("Search");
		namesearchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchName(namesearchtxt.getText().toUpperCase());
			}
		});
		namesearchbtn.setFont(new Font("Georgia", Font.PLAIN, 15));
		namesearchbtn.setBounds(557, 41, 136, 24);
		bnamelayer.add(namesearchbtn);
		
		displayall1 = new JButton("Display All");
		displayall1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAll();
			}
		});
		displayall1.setFont(new Font("Georgia", Font.PLAIN, 15));
		displayall1.setBounds(710, 41, 136, 24);
		bnamelayer.add(displayall1);
		
		bauthorlayer = new JPanel();
		changing_panel.add(bauthorlayer, "name_998304433911500");
		bauthorlayer.setLayout(null);
		
		title2 = new JLabel("Search Book");
		title2.setFont(new Font("Georgia", Font.PLAIN, 18));
		title2.setBounds(10, 10, 128, 21);
		bauthorlayer.add(title2);
		
		sub2 = new JLabel("(Enter the correct Author of the book which you want to search else click Display All)");
		sub2.setFont(new Font("Georgia", Font.PLAIN, 15));
		sub2.setEnabled(false);
		sub2.setBounds(148, 10, 621, 21);
		bauthorlayer.add(sub2);
		
		authorsearchtxt = new JTextField();
		authorsearchtxt.setFont(new Font("Georgia", Font.PLAIN, 15));
		authorsearchtxt.setColumns(10);
		authorsearchtxt.setBounds(10, 41, 537, 24);
		bauthorlayer.add(authorsearchtxt);
		
		authorsearchbtn = new JButton("Search");
		authorsearchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAuthor(authorsearchtxt.getText().toUpperCase());
			}
		});
		authorsearchbtn.setFont(new Font("Georgia", Font.PLAIN, 15));
		authorsearchbtn.setBounds(557, 41, 136, 24);
		bauthorlayer.add(authorsearchbtn);
		
		displayall2 = new JButton("Display All");
		displayall2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAll();
			}
		});
		displayall2.setFont(new Font("Georgia", Font.PLAIN, 15));
		displayall2.setBounds(710, 41, 136, 24);
		bauthorlayer.add(displayall2);
		
		bcategorylayer = new JPanel();
		changing_panel.add(bcategorylayer, "name_998309184044700");
		bcategorylayer.setLayout(null);
		
		title3 = new JLabel("Search Book");
		title3.setFont(new Font("Georgia", Font.PLAIN, 18));
		title3.setBounds(10, 10, 128, 21);
		bcategorylayer.add(title3);
		
		sub3 = new JLabel("Select from the DropDown Menu Box your genre of book you wanna Issue");
		sub3.setFont(new Font("Georgia", Font.PLAIN, 15));
		sub3.setEnabled(false);
		sub3.setBounds(125, 10, 498, 18);
		bcategorylayer.add(sub3);
		
		categoryselector = new JComboBox();
		categoryselector.setModel(new DefaultComboBoxModel(new String[] {"Action and Adventure", "Classics", "Graphic Novel", "Mystery", "Fantasy", "Historical Fiction", "Horror", "Literary Fiction", "Romance", "Science Fiction", "Short Stories", "Thriller", "Women Fiction", "Biography", "Cookbooks", "Essay", "History", "Memoir", "Poetry", "Self-Help", "true Crime"}));
		categoryselector.setMaximumRowCount(10);
		categoryselector.setFont(new Font("Georgia", Font.PLAIN, 18));
		categoryselector.setBounds(20, 41, 373, 24);
		bcategorylayer.add(categoryselector);
		
		categorysearchbtn = new JButton("Display Category");
		categorysearchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = categoryselector.getSelectedItem().toString().toUpperCase();
				searchCategory(temp);
			}
		});
		categorysearchbtn.setFont(new Font("Georgia", Font.PLAIN, 15));
		categorysearchbtn.setBounds(435, 41, 187, 27);
		bcategorylayer.add(categorysearchbtn);
		
		displayall3 = new JButton("Display All");
		displayall3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAll();
			}
		});
		displayall3.setFont(new Font("Georgia", Font.PLAIN, 15));
		displayall3.setBounds(646, 39, 128, 27);
		bcategorylayer.add(displayall3);
		
		isbnlayer = new JPanel();
		changing_panel.add(isbnlayer, "name_998313162795200");
		isbnlayer.setLayout(null);
		
		title4 = new JLabel("Search Book");
		title4.setFont(new Font("Georgia", Font.PLAIN, 18));
		title4.setBounds(10, 10, 128, 21);
		isbnlayer.add(title4);
		
		sub4 = new JLabel("(Enter the ISBN of the book which you want to search else click Display All)");
		sub4.setFont(new Font("Georgia", Font.PLAIN, 15));
		sub4.setEnabled(false);
		sub4.setBounds(148, 10, 621, 21);
		isbnlayer.add(sub4);
		
		isbnsearchtxt = new JTextField();
		isbnsearchtxt.setFont(new Font("Georgia", Font.PLAIN, 15));
		isbnsearchtxt.setColumns(10);
		isbnsearchtxt.setBounds(10, 41, 537, 24);
		isbnlayer.add(isbnsearchtxt);
		
		isbnsearchbtn = new JButton("Search");
		isbnsearchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchISBN(isbnsearchtxt.getText().toUpperCase());
			}
		});
		isbnsearchbtn.setFont(new Font("Georgia", Font.PLAIN, 15));
		isbnsearchbtn.setBounds(557, 41, 136, 24);
		isbnlayer.add(isbnsearchbtn);
		
		displayall4 = new JButton("Display All");
		displayall4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAll();
			}
		});
		displayall4.setFont(new Font("Georgia", Font.PLAIN, 15));
		displayall4.setBounds(710, 41, 136, 24);
		isbnlayer.add(displayall4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 182, 856, 261);
		Main_frame.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Georgia", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NAME", "AUTHOR", "CATEGORY", "ISBN"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		submitisbn = new JTextField();
		submitisbn.setBounds(346, 453, 140, 29);
		Main_frame.add(submitisbn);
		submitisbn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				submitisbn.setText("");
				submitisbn.removeFocusListener(this);
			}
		});
		submitisbn.setText("ISBN");
		submitisbn.setFont(new Font("Georgia", Font.PLAIN, 20));
		submitisbn.setHorizontalAlignment(SwingConstants.CENTER);
		submitisbn.setColumns(10);
		JTextField submitusn = new JTextField(usn);
		submitusn.setFont(new Font("Arial", Font.PLAIN, 20));
		submitusn.setHorizontalAlignment(SwingConstants.CENTER);
		submitusn.setEditable(false);
		submitusn.setColumns(10);
		submitusn.setBounds(510, 453, 210, 29);
		Main_frame.add(submitusn);
		
		issue = new JButton("Issue");
		issue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				issue(submitisbn.getText(),submitusn.getText());
			}
		});
		issue.setFont(new Font("Georgia", Font.PLAIN, 15));
		issue.setBounds(730, 457, 136, 24);
		Main_frame.add(issue);
		
		footer1 = new JLabel("Enter The correct ISBN of the book and");
		footer1.setFont(new Font("Georgia", Font.PLAIN, 15));
		footer1.setEnabled(false);
		footer1.setBounds(10, 453, 308, 21);
		Main_frame.add(footer1);
		
		footer2 = new JLabel("see that Correct USN is Shown");
		footer2.setFont(new Font("Georgia", Font.PLAIN, 15));
		footer2.setEnabled(false);
		footer2.setBounds(11, 473, 308, 21);
		Main_frame.add(footer2);
		
		back = new JToggleButton("BACK");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDisplay ob = new UserDisplay(name,usn);
				Search.this.dispose();
				ob.setVisible(true);
			}
		});
		back.setFont(new Font("Georgia", Font.PLAIN, 15));
		back.setBounds(730, 10, 140, 20);	
		Main_frame.add(back);
	}
}