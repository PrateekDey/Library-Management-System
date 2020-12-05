import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class UserDisplay extends JFrame {

	private JPanel contentPane;
	private static String name;
	private static String usn;
	private JLayeredPane changing_panel;
	private JPanel info_panel;
	private JPanel return_panel;
	private JPanel Reissue_panel;
	private JLabel info3;
	private JTextField isbnreturn;
	private JTextField finereturn;
	private JLabel info7;
	private JTextField isbnreissue;
	private JTextField finereissue;
	private JButton finalreissue;
	private JLabel info8;
	private JLabel info9;
	private JLabel info10;
	private JButton finalreturn;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDisplay frame = new UserDisplay(name, usn);
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
	public void refresh(String usn) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
			Statement st = con.createStatement();
			String query = "select isbn, issue_date, return_date from user_crud where usn = '"+usn+"'";
			
			ResultSet rs =st.executeQuery(query);
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			tab.setRowCount(0);
			
			while(rs.next()) {
				String isbn = rs.getString("ISBN");
				String issue_date = rs.getString("ISSUE_DATE");
				String return_date = rs.getString("Return_Date");
				String fine = Calculatefine(issue_date);
				Object o[]= {isbn,issue_date,return_date,fine};
				tab.addRow(o);
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
	
	public String Calculatefine(String issue_date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Calendar current=Calendar.getInstance();
		
		Date date1 = sdf.parse(issue_date);
		String today = sdf.format(current.getTime());
		Date date2 = sdf.parse(today);
		
		long diff = date2.getTime() - date1.getTime();
		int fine =(int) (diff/(24*60*60*1000));
		
		if(fine > 10) return Integer.toString(fine);
		
		return "0";
		
	}
	
	public UserDisplay(String name,String usn) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel main_panel = new JPanel();
		contentPane.add(main_panel, BorderLayout.CENTER);
		main_panel.setLayout(null);
		
		JLabel main_title = new JLabel("User Profile ");
		main_title.setFont(new Font("Georgia", Font.PLAIN, 25));
		main_title.setHorizontalAlignment(SwingConstants.CENTER);
		main_title.setBounds(10, 10, 147, 29);
		main_panel.add(main_title);
		
		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ob=new Login();
				if(JOptionPane.showConfirmDialog(null, "Are you sure you wanna Logout")==JOptionPane.YES_OPTION) {
					UserDisplay.this.dispose();
					ob.setVisible(true);
				}
			}
		});
		logout.setFont(new Font("Georgia", Font.PLAIN, 18));
		logout.setBounds(662, 12, 104, 29);
		main_panel.add(logout);
		
		JLabel sub1 = new JLabel("Welcome");
		sub1.setHorizontalAlignment(SwingConstants.CENTER);
		sub1.setFont(new Font("Georgia", Font.PLAIN, 20));
		sub1.setBounds(30, 56, 88, 24);
		main_panel.add(sub1);
		
		JLabel namelabel = new JLabel(name);
		namelabel.setBackground(Color.WHITE);
		namelabel.setFont(new Font("Georgia", Font.PLAIN, 20));
		namelabel.setBounds(162, 56, 141, 24);
		main_panel.add(namelabel);
		
		JLabel usnlabel = new JLabel(usn);
		usnlabel.setBackground(Color.WHITE);
		usnlabel.setFont(new Font("Arial", Font.PLAIN, 20));
		usnlabel.setBounds(395, 56, 157, 24);
		main_panel.add(usnlabel);
		
		JButton Searchbtn = new JButton("Search A Book");
		Searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search ob=new Search(name,usn);
				UserDisplay.this.dispose();
				ob.setVisible(true);
			}
		});
		Searchbtn.setFont(new Font("Georgia", Font.PLAIN, 20));
		Searchbtn.setBounds(160, 102, 200, 40);
		main_panel.add(Searchbtn);
		
		JButton issuebtn = new JButton("Issue A book");
		issuebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "For your convinience,\n We have merged the search window\n with the issue option");
				Search ob = new Search(name,usn);
				UserDisplay.this.dispose();
				ob.setVisible(true);
			}
		});
		issuebtn.setFont(new Font("Georgia", Font.PLAIN, 20));
		issuebtn.setBounds(395, 102, 200, 40);
		main_panel.add(issuebtn);
		
		JButton reissuebtn = new JButton("Re-Issue A book");
		reissuebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isbnreturn.setText("ISBN code");
				finereturn.setText("Fine Amount");
				switchpanels(Reissue_panel);
			}
		});
		reissuebtn.setFont(new Font("Georgia", Font.PLAIN, 20));
		reissuebtn.setBounds(395, 156, 200, 40);
		main_panel.add(reissuebtn);
		
		JButton returnbtn = new JButton("Return A Book");
		returnbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchpanels(return_panel);
			}
		});
		returnbtn.setFont(new Font("Georgia", Font.PLAIN, 20));
		returnbtn.setBounds(160, 156, 200, 40);
		main_panel.add(returnbtn);
		
		JLabel Sub2 = new JLabel("Book Issued");
		Sub2.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		Sub2.setBounds(10, 204, 108, 18);
		main_panel.add(Sub2);
		
		changing_panel = new JLayeredPane();
		changing_panel.setBounds(10, 306, 756, 87);
		main_panel.add(changing_panel);
		changing_panel.setLayout(new CardLayout(0, 0));
		
		info_panel = new JPanel();
		changing_panel.add(info_panel, "name_1017921277050500");
		info_panel.setLayout(null);
		
		JLabel info1 = new JLabel("Welcome to the libaray Management System");
		info1.setHorizontalAlignment(SwingConstants.CENTER);
		info1.setFont(new Font("Georgia", Font.PLAIN, 25));
		info1.setBounds(116, 20, 532, 29);
		info_panel.add(info1);
		
		JLabel info2 = new JLabel("Made By Prateek Dey");
		info2.setHorizontalAlignment(SwingConstants.CENTER);
		info2.setFont(new Font("Georgia", Font.PLAIN, 15));
		info2.setBounds(278, 48, 172, 18);
		info_panel.add(info2);
		
		return_panel = new JPanel();
		changing_panel.add(return_panel, "name_1017929957904600");
		return_panel.setLayout(null);
		
		info3 = new JLabel("Enter the ISBN Code of the book which you Want to Return and the Amount Paid :");
		info3.setEnabled(false);
		info3.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		info3.setBounds(10, 10, 740, 20);
		return_panel.add(info3);
		
		isbnreturn = new JTextField();
		isbnreturn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				isbnreturn.setText("");
				isbnreturn.removeFocusListener(this);
			}
		});
		isbnreturn.setHorizontalAlignment(SwingConstants.CENTER);
		isbnreturn.setText("ISBN code");
		isbnreturn.setFont(new Font("Arial", Font.PLAIN, 15));
		isbnreturn.setBounds(20, 40, 150, 35);
		return_panel.add(isbnreturn);
		isbnreturn.setColumns(10);
		
		finereturn = new JTextField();
		finereturn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				finereturn.setText("");
				finereturn.removeFocusListener(this);
			}
		});
		finereturn.setText("Fine Amount");
		finereturn.setHorizontalAlignment(SwingConstants.CENTER);
		finereturn.setFont(new Font("Arial", Font.PLAIN, 15));
		finereturn.setColumns(10);
		finereturn.setBounds(200, 40, 150, 35);
		return_panel.add(finereturn);
		
		finalreturn = new JButton("Return");
		finalreturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tempisbn = isbnreturn.getText();
				@SuppressWarnings("unused")
				String tempfine = finereturn.getText();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
					Statement st = con.createStatement();
					String query = "Delete from user_crud where usn ='"+usn+"' and isbn ='"+tempisbn+"'";
					if(JOptionPane.showConfirmDialog(finalreturn, "Are you Sure you wanna return") == JOptionPane.YES_OPTION) {
					st.executeUpdate(query);
					
					Random rand = new Random();
					JOptionPane.showMessageDialog(null, "Reciept number : "+rand.nextInt(100000)+"\n Go to the Librarian and pay Rs."+tempfine+" the existing bill\n Using this reciept number");
					refresh(usn);
					isbnreturn.setText("ISBN code");
					finereturn.setText("Fine Amount");
					}
							
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				isbnreturn.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						isbnreturn.setText("");
						isbnreturn.removeFocusListener(this);
					}
				});
				finereturn.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						finereturn.setText("");
						finereturn.removeFocusListener(this);
					}
				});
			}
		});
		finalreturn.setFont(new Font("Georgia", Font.PLAIN, 20));
		finalreturn.setBounds(383, 40, 150, 35);
		return_panel.add(finalreturn);
		
		JLabel info4 = new JLabel("There will be a dialogue box generated");
		info4.setEnabled(false);
		info4.setFont(new Font("Tahoma", Font.BOLD, 8));
		info4.setBounds(555, 37, 156, 10);
		return_panel.add(info4);
		
		JLabel info5 = new JLabel("in which the receipt number will be");
		info5.setEnabled(false);
		info5.setFont(new Font("Tahoma", Font.BOLD, 8));
		info5.setBounds(555, 52, 140, 10);
		return_panel.add(info5);
		
		JLabel info6 = new JLabel("there go to the libararian Submit that one.");
		info6.setEnabled(false);
		info6.setFont(new Font("Tahoma", Font.BOLD, 8));
		info6.setBounds(555, 67, 168, 10);
		return_panel.add(info6);
		
		Reissue_panel = new JPanel();
		changing_panel.add(Reissue_panel, "name_1017937618919700");
		Reissue_panel.setLayout(null);
		
		info7 = new JLabel("Enter the ISBN Code of the book which you Want to Re-Issue and the Amount Paid :");
		info7.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		info7.setEnabled(false);
		info7.setBounds(10, 10, 740, 20);
		Reissue_panel.add(info7);
		
		isbnreissue = new JTextField();
		isbnreissue.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				isbnreissue.setText("");
				isbnreissue.removeFocusListener(this);
			}
		});
		isbnreissue.setText("ISBN code");
		isbnreissue.setHorizontalAlignment(SwingConstants.CENTER);
		isbnreissue.setFont(new Font("Arial", Font.PLAIN, 15));
		isbnreissue.setColumns(10);
		isbnreissue.setBounds(20, 40, 150, 35);
		Reissue_panel.add(isbnreissue);
		
		finereissue = new JTextField();
		finereissue.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				finereissue.setText("");
				finereissue.removeFocusListener(this);
			}
		});
		finereissue.setText("Fine Amount");
		finereissue.setHorizontalAlignment(SwingConstants.CENTER);
		finereissue.setFont(new Font("Arial", Font.PLAIN, 15));
		finereissue.setColumns(10);
		finereissue.setBounds(200, 40, 150, 35);
		Reissue_panel.add(finereissue);
		
		finalreissue = new JButton("Re-Issue");
		finalreissue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tempisbn = isbnreissue.getText();
				@SuppressWarnings("unused")
				String tempfine = finereissue.getText();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
					Statement st = con.createStatement();
					String query = "Delete from user_crud where usn ='"+usn+"' and isbn ='"+tempisbn+"'";
					if(JOptionPane.showConfirmDialog(finalreturn, "Are you Sure you wanna Re-issue") == JOptionPane.YES_OPTION) {
					st.executeUpdate(query);
					Random rand = new Random();
					JOptionPane.showMessageDialog(null, "Reciept number : "+rand.nextInt(100000)+"\n Go to the Librarian and pay Rs."+tempfine+" the existing bill\n Using this reciept number");
					//date generation
					SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
					Calendar current = Calendar.getInstance();
					String issue_date = sdf.format(current.getTime()).toString();
					current.add(Calendar.DAY_OF_MONTH, 10);
					String return_date = sdf.format(current.getTime()).toString();
					
					
					String tempquery = "insert into book_crud value('"+usn+"','"+tempisbn+"','"+issue_date+"','"+return_date+"')";
					st.executeUpdate(tempquery);
					JOptionPane.showMessageDialog(null, "Book Issued");
					refresh(usn);
					isbnreissue.setText("ISBN code");
					finereissue.setText("Fine Amount");
					}
							
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				isbnreissue.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						isbnreissue.setText("");
						isbnreissue.removeFocusListener(this);
					}
				});
				finereissue.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						finereissue.setText("");
						finereissue.removeFocusListener(this);
					}
				});
			}
		});
		finalreissue.setFont(new Font("Georgia", Font.PLAIN, 20));
		finalreissue.setBounds(383, 40, 150, 35);
		Reissue_panel.add(finalreissue);
		
		info8 = new JLabel("There will be a dialogue box generated");
		info8.setFont(new Font("Tahoma", Font.BOLD, 8));
		info8.setEnabled(false);
		info8.setBounds(555, 37, 156, 10);
		Reissue_panel.add(info8);
		
		info9 = new JLabel("in which the receipt number will be");
		info9.setFont(new Font("Tahoma", Font.BOLD, 8));
		info9.setEnabled(false);
		info9.setBounds(555, 52, 140, 10);
		Reissue_panel.add(info9);
		
		info10 = new JLabel("there go to the libararian Submit that one.");
		info10.setFont(new Font("Tahoma", Font.BOLD, 8));
		info10.setEnabled(false);
		info10.setBounds(555, 67, 168, 10);
		Reissue_panel.add(info10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 225, 756, 70);
		main_panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ISBN", "Issue Date", "Return Date", "Fine / Perday"
			}
		) {
			private static final long serialVersionUID = 1L;
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
		table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setViewportView(table);
		refresh(usn);
		
	}
}
