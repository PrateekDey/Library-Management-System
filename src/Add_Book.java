import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Add_Book extends JFrame {

	private JPanel contentPane;
	private JTextField addname;
	private JTextField addauthor;
	private JComboBox<String> addcategory;
	private JTextField addisbn;
	private JButton btnNewButton_1;
	private static String name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Book frame = new Add_Book(name);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param name 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Add_Book(String name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel tempcategory = new JLabel("Add a Category");
		tempcategory.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tempcategory.setVisible(false);
				tempcategory.removeFocusListener(this);
			}
		});
		tempcategory.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		tempcategory.setHorizontalAlignment(SwingConstants.CENTER);
		tempcategory.setBounds(40, 160, 296, 25);
		panel.add(tempcategory);
		
		JLabel lblNewLabel = new JLabel("Add Book Section");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
		lblNewLabel.setBounds(40, 32, 298, 24);
		panel.add(lblNewLabel);
		
		addname = new JTextField();
		addname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				addname.setText("");
				addname.setForeground(Color.black);
			}
		});
		addname.setForeground(Color.LIGHT_GRAY);
		addname.setText("Name of the Book");
		addname.setHorizontalAlignment(SwingConstants.CENTER);
		addname.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		addname.setBounds(40, 77, 298, 24);
		panel.add(addname);
		addname.setColumns(10);
		
		addauthor = new JTextField();
		addauthor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				addauthor.setText("");
				addauthor.setForeground(Color.black);
			}
		});
		addauthor.setForeground(Color.LIGHT_GRAY);
		addauthor.setText("Name of the Author");
		addauthor.setHorizontalAlignment(SwingConstants.CENTER);
		addauthor.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		addauthor.setColumns(10);
		addauthor.setBounds(40, 116, 298, 24);
		panel.add(addauthor);
		
		addcategory = new JComboBox<String>();
		addcategory.setModel(new DefaultComboBoxModel(new String[] {"", "Action and Adventure", "Classics", "Graphic Novel", "Mystery", "Fantasy", "Historical Fiction", "Horror", "Literary Fiction", "Romance", "Science Fiction", "Short Stories", "Thriller", "Women Fiction", "Biography", "Cookbooks", "Essay", "History", "Memoir", "Poetry", "Self-Help", "true Crime"}));
		addcategory.setForeground(SystemColor.windowText);
		addcategory.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		addcategory.setBounds(40, 160, 298, 25);
		panel.add(addcategory);
		
		addisbn = new JTextField();
		addisbn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				addisbn.setText("");
				addisbn.setForeground(Color.black);
			}
		});
		addisbn.setForeground(Color.LIGHT_GRAY);
		addisbn.setText("ISBN Number");
		addisbn.setHorizontalAlignment(SwingConstants.CENTER);
		addisbn.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		addisbn.setColumns(10);
		addisbn.setBounds(40, 206, 298, 24);
		panel.add(addisbn);
		
		JButton btnNewButton = new JButton("Add Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = addname.getText().toUpperCase();
				String author = addauthor.getText().toUpperCase();
				String category = addcategory.getSelectedItem().toString().toUpperCase();
				String isbn = addisbn.getText().toUpperCase();
				
				if(name.isEmpty()||author.isEmpty()||category.isEmpty()||isbn.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter all the field");
				}
				else {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "Apple1234");
						Statement st = con.createStatement();
						String query = "insert into book_crud value('"+name+"','"+author+"','"+category+"','"+isbn+"')";
						st.executeUpdate(query);
						if(JOptionPane.showConfirmDialog(null, "Book Added\nDo you wanna Add another?") == JOptionPane.YES_OPTION) {
							addname.setText("Name of the Book");
							addauthor.setText("Author of the Book");
							addcategory.setSelectedIndex(0);
							tempcategory.setVisible(true);
							tempcategory.addFocusListener(new FocusAdapter() {
								@Override
								public void focusGained(FocusEvent e) {
									tempcategory.setVisible(false);
									tempcategory.removeFocusListener(this);
								}
							});
							addisbn.setText("ISBN of the Book");
						}
						else {
							Librarian_display ob=new Librarian_display(name);
							Add_Book.this.dispose();
							ob.setVisible(true);
						}
						con.close();
					}catch(Exception ae) {
						JOptionPane.showMessageDialog(null, ae);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		btnNewButton.setBounds(102, 247, 165, 29);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Librarian_display ob=new Librarian_display(name);
				Add_Book.this.dispose();
				ob.setVisible(true);
			}
		});
		btnNewButton_1.setForeground(SystemColor.windowText);
		btnNewButton_1.setBackground(SystemColor.control);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_1.setBounds(300, 0, 76, 21);
		panel.add(btnNewButton_1);
	}
}
