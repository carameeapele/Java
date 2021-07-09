import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JToolBar;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Exercitiul1 extends JFrame
{
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldNume;
	private JTextField textFieldVarsta;
	
	private JButton btnFirst;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnLast;
	private JTextField txtAfis;
	
	private JButton btnAdaugare;
	private JButton btnEditare;
	private JButton btnStergere;
	private JButton btnCautare;
	private JButton btnSalvare;
	private JButton btnRenuntare;
	
	static String url = "jdbc:mysql://localhost:3306/test";
	private int N = 0;
	private int index;
	private int stare;
	private boolean add = false;
	
	public static void main(String[] args) throws SQLException
	{
		
		/* LAUNCH THE APPLICATION */
		
		EventQueue.invokeLater(new Runnable()
		{	
			public void run()
			{
				try
				{
					Exercitiul1 frame = new Exercitiul1();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setAfisText(int i)
	{
		txtAfis.setText(" " + String.valueOf(i + 1) + " / " + String.valueOf(N));
	}
	
	public void verifyIndex(int i)
	{
		if(i == 0)
		{
			btnFirst.setEnabled(false);
			btnPrevious.setEnabled(false);
		}
		else
		{
			btnFirst.setEnabled(true);
			btnPrevious.setEnabled(true);
		}
		
		if(i == N-1)
		{
			btnLast.setEnabled(false);
			btnNext.setEnabled(false);
		}
		else
		{
			btnLast.setEnabled(true);
			btnNext.setEnabled(true);
		}
	}
	
	public void verifyStare(int s)
	{
		if(s == 1)
		{
			btnSalvare.setEnabled(false);
			btnRenuntare.setEnabled(false);
			
			btnFirst.setEnabled(true);
			btnPrevious.setEnabled(true);
			btnLast.setEnabled(true);
			btnNext.setEnabled(true);
			btnCautare.setEnabled(true);
			btnAdaugare.setEnabled(true);
			btnEditare.setEnabled(true);
			btnStergere.setEnabled(true);
		}
		
		if(s == 2)
		{
			btnSalvare.setEnabled(true);
			btnRenuntare.setEnabled(true);
			
			btnFirst.setEnabled(false);
			btnPrevious.setEnabled(false);
			btnLast.setEnabled(false);
			btnNext.setEnabled(false);
			btnCautare.setEnabled(false);
			btnAdaugare.setEnabled(false);
			btnEditare.setEnabled(false);
			btnStergere.setEnabled(false);
		}
	}
	
	public Exercitiul1() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		/*   DATA BASE CONNECTION   */
		
		String url = "jdbc:mysql://localhost:3306/test";		
		
		//Class.forName ("com.mysql.cj.jdbc.Driver").newInstance ();
		final Connection con = DriverManager.getConnection (url, "carameea", "cafeaculapte");
		//Statement sql = con.createStatement();
		Statement sql = (Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		final ResultSet rs = sql.executeQuery("select * from persoane");
		
		while(rs.next())
		{
			N++;
		}
		
		rs.first();
		index = 0;
		stare = 1;
		
		/* -------------------------------------------------------------------------------------------------- */
		/*   FRAME   */
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		/* -------------------------------------------------------------------------------------------------- */
		/*   TOOLBAR   */
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		/*   NAVIGATION   */
		
		btnFirst = new JButton();
		btnFirst.setIcon(new ImageIcon(Exercitiul1.class.getResource("/Imagini/MoveFirst.png")));
		
		btnPrevious = new JButton();
		btnPrevious.setIcon(new ImageIcon(Exercitiul1.class.getResource("/Imagini/MovePrevious.png")));
		
		txtAfis = new JTextField();
		txtAfis.setFont(new Font("Arial", Font.BOLD, 14));
		setAfisText(0);
		
		btnNext = new JButton();
		btnNext.setIcon(new ImageIcon(Exercitiul1.class.getResource("/Imagini/MoveNext.png")));
		
		btnLast = new JButton();
		btnLast.setIcon(new ImageIcon(Exercitiul1.class.getResource("/Imagini/MoveLast.png")));
				
		toolBar.add(btnFirst);
		toolBar.add(btnPrevious);
		toolBar.add(txtAfis);
		toolBar.add(btnNext);
		toolBar.add(btnLast);
		
		/*   COMMANDS   */
		
		btnAdaugare = new JButton();
		btnAdaugare.setIcon(new ImageIcon(Exercitiul1.class.getResource("/Imagini/Add.png")));
		
		btnEditare = new JButton();
		btnEditare.setIcon(new ImageIcon(Exercitiul1.class.getResource("/Imagini/Edit.png")));
		
		btnStergere = new JButton();
		btnStergere.setIcon(new ImageIcon(Exercitiul1.class.getResource("/Imagini/Delete.png")));
		
		btnCautare = new JButton();
		btnCautare.setIcon(new ImageIcon(Exercitiul1.class.getResource("/Imagini/find.JPG")));
		
		btnSalvare = new JButton();
		btnSalvare.setIcon(new ImageIcon(Exercitiul1.class.getResource("/Imagini/save.JPG")));
		
		btnRenuntare = new JButton();
		btnRenuntare.setIcon(new ImageIcon(Exercitiul1.class.getResource("/Imagini/undo.JPG")));
		
		toolBar.add(btnAdaugare);
		toolBar.add(btnEditare);
		toolBar.add(btnStergere);
		toolBar.add(btnCautare);
		toolBar.add(btnSalvare);
		toolBar.add(btnRenuntare);
		
		verifyStare(stare);
		verifyIndex(index);
		
		/* -------------------------------------------------------------------------------------------------- */
		/*   MAIN PANEL   */
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		/* -------------------------------------------------------------------------------------------------- */
		/*   LEFT PANEL   */
		
		JPanel leftPanel = new JPanel();
		mainPanel.add(leftPanel);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
				
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Arial", Font.BOLD, 14));
		lblId.setAlignmentX(Component.RIGHT_ALIGNMENT);
		leftPanel.add(lblId);
		
		Component verticalStrut_1 = Box.createVerticalStrut(15);
		leftPanel.add(verticalStrut_1);
				
		JLabel lblNume = new JLabel("Nume");
		lblNume.setFont(new Font("Arial", Font.BOLD, 14));
		lblNume.setAlignmentX(Component.RIGHT_ALIGNMENT);
		leftPanel.add(lblNume);
		
		leftPanel.add(Box.createVerticalStrut(15));
				
		JLabel lblVarsta = new JLabel("Varsta");
		lblVarsta.setFont(new Font("Arial", Font.BOLD, 14));
		lblVarsta.setAlignmentX(Component.RIGHT_ALIGNMENT);
		leftPanel.add(lblVarsta);
		
		/* -------------------------------------------------------------------------------------------------- */
		/*   RIGHT PANEL   */
				
		JPanel rightPanel = new JPanel();
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		textFieldId = new JTextField();
		textFieldId.setFont(new Font("Arial", Font.PLAIN, 12));
		rightPanel.add(textFieldId);
		textFieldId.setColumns(15);
		
		rightPanel.add(Box.createVerticalStrut(15));
		
		textFieldNume = new JTextField();
		textFieldNume.setFont(new Font("Arial", Font.PLAIN, 12));
		rightPanel.add(textFieldNume);
		textFieldNume.setColumns(15);
		
		rightPanel.add(Box.createVerticalStrut(15));
		
		textFieldVarsta = new JTextField();
		textFieldVarsta.setFont(new Font("Arial", Font.PLAIN, 12));
		rightPanel.add(textFieldVarsta);
		//textFieldVarsta.setColumns(15);

		textFieldId.setText(rs.getInt("id") + "");
		textFieldNume.setText(rs.getString("nume") + "");
		textFieldVarsta.setText(rs.getInt("varsta") + "");
		
		textFieldId.setEditable(false);
		textFieldNume.setEditable(false);
		textFieldVarsta.setEditable(false);
		
		//setPersoana(index);
		
		/* -------------------------------------------------------------------------------------------------- */
		/*   ACTIONS   */
		
		// FIRST 
		btnFirst.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					rs.first(); 
					index = 0;
					textFieldId.setText(String.valueOf(rs.getInt("id"))); 
					textFieldNume.setText(rs.getString("nume")); 
					textFieldVarsta.setText(String.valueOf(rs.getInt("varsta"))); 
					setAfisText(index);
					verifyIndex(index);
				}
				catch(SQLException sqle) 
				{
					sqle.printStackTrace();
				}
			}
		});
		
		// PREVIOUS
		btnPrevious.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					rs.previous();
					index--;
					textFieldId.setText(String.valueOf(rs.getInt("id")));
					textFieldNume.setText(rs.getString("nume"));
					textFieldVarsta.setText(String.valueOf(rs.getInt("varsta")));
					setAfisText(index);
				}
				catch(SQLException sqle)
				{
					sqle.printStackTrace();
				}
				catch(IndexOutOfBoundsException iob)
				{
					index = 0;
					setAfisText(index);
				}
				
				verifyIndex(index);
			}
		});
		
		// NEXT
		btnNext.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					rs.next();
					index++;
					textFieldId.setText(String.valueOf(rs.getInt("id")));
					textFieldNume.setText(rs.getString("nume"));
					textFieldVarsta.setText(String.valueOf(rs.getInt("varsta")));
					setAfisText(index);
				}
				catch(SQLException sqle)
				{
					sqle.printStackTrace();
				}
				catch(IndexOutOfBoundsException iob)
				{
					index = N-1;
					setAfisText(index);
				}
				
				verifyIndex(index);
			}
		});
		
		// LAST
		btnLast.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					rs.last();
					index = N-1;
					textFieldId.setText(String.valueOf(rs.getInt("id")));
					textFieldNume.setText(rs.getString("nume"));
					textFieldVarsta.setText(String.valueOf(rs.getInt("varsta")));
					setAfisText(index);
					verifyIndex(index);
				}
				catch(SQLException sqle)
				{
					sqle.printStackTrace();
				}
			}
		});
		
		// ADD
		btnAdaugare.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				stare = 2;
				add = true;
				textFieldId.setText("");
				textFieldNume.setText("");
				textFieldVarsta.setText("");
				txtAfis.setText("");
				
				textFieldId.setEditable(true);
				textFieldNume.setEditable(true);
				textFieldVarsta.setEditable(true);
				
				verifyStare(stare);
			}
		});
		
		// EDIT
		btnEditare.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				stare = 2;
				add = false;
				verifyStare(stare);
				
				textFieldId.setEditable(true);
				textFieldNume.setEditable(true);
				textFieldVarsta.setEditable(true);
			}
		});
		
		// SAVE
		btnSalvare.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int id = Integer.parseInt(textFieldId.getText());
				String nume = textFieldNume.getText();
				int varsta = Integer.parseInt(textFieldVarsta.getText());
				
				if(add)
				{					
					try
					{
						rs.moveToInsertRow();
						rs.updateInt("id", id);
						rs.updateString("nume", nume);
						rs.updateInt("varsta", varsta);
						rs.insertRow();
						
						N++;
						index++;
					}
					catch(SQLException sqle)
					{
						sqle.printStackTrace();
					}
				}
				else
				{
					try
					{
						rs.absolute(index+1);
						rs.updateInt("id", id);
						rs.updateString("nume", nume);
						rs.updateInt("varsta", varsta);
						rs.updateRow();
					}
					catch(SQLException sqle)
					{
						sqle.printStackTrace();
					}
				}
				
				stare = 1;
				verifyStare(stare);
				setAfisText(index);
				
				try
				{
					rs.absolute(index+1);
					textFieldId.setText(String.valueOf(rs.getInt("id")));
					textFieldNume.setText(rs.getString("nume"));
					textFieldVarsta.setText(String.valueOf(rs.getInt("varsta")));
				}
				catch(SQLException sqle)
				{
					sqle.printStackTrace();
				}
				
				textFieldId.setEditable(false);
				textFieldNume.setEditable(false);
				textFieldVarsta.setEditable(false);
				verifyIndex(index);
			}
		});
		
		// UNDO
		btnRenuntare.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				stare = 1;
				verifyStare(stare);
				setAfisText(index);
				verifyIndex(index);
				
				textFieldId.setEditable(false);
				textFieldNume.setEditable(false);
				textFieldVarsta.setEditable(false);
			}
		});
		
		// DELETE
		btnStergere.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{				
				int ok = JOptionPane.showConfirmDialog(null, "Sunteti sigur ca doriti sa stergeti persoana curenta?", "Confirmare Stergere", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(ok == 0)
				{
					try
					{
						rs.absolute(index+1);
						rs.deleteRow();
					}
					catch(SQLException sqle)
					{
						sqle.printStackTrace();
					}
				
					N--;
					index--;
					setAfisText(index);
					
					try
					{
						rs.absolute(index+1);
						textFieldId.setText(String.valueOf(rs.getInt("id")));
						textFieldNume.setText(rs.getString("nume"));
						textFieldVarsta.setText(String.valueOf(rs.getInt("varsta")));
					}
					catch(SQLException sqle)
					{
						sqle.printStackTrace();
					}
					
					verifyIndex(index);
				}
			}
		});
		
		// SEARCH
		btnCautare.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				String nume = JOptionPane.showInputDialog("Numele");
				if(nume != null)
				{
					Boolean found = false;
				
					try
					{
						rs.first();
						index = 0;
						
						do
						{
							String numeSQL = rs.getString("nume");
							
							if(numeSQL.equalsIgnoreCase(nume))
							{
								found = true;
								
								rs.absolute(index+1);
								textFieldId.setText(String.valueOf(rs.getInt("id")));
								textFieldNume.setText(rs.getString("nume"));
								textFieldVarsta.setText(String.valueOf(rs.getInt("varsta")));
								
								setAfisText(index);
								verifyIndex(index);
								
								return;
							}
							
							index++;
						} while(rs.next());
					}
					catch(SQLException sqle)
					{
						sqle.printStackTrace();
					}
				
					if(!found)
						JOptionPane.showMessageDialog(null, "Persoana cu numele " + nume + " nu exista");
				}	
			}
		});
	}

}
