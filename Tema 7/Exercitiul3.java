import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.DefaultListModel;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Exercitiul3
{

	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtFilm;
	private JTextField txtActori;
	private JScrollPane js;
	
	String[] coloane = { "Film", "Actori", "An lansare", "Genuri" };
	private DefaultTableModel tableModel = new DefaultTableModel(coloane, 0);
	private JTable tableFilme = new JTable(tableModel);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Exercitiul3 window = new Exercitiul3();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Exercitiul3()
	{		
		frame = new JFrame("Filme");
		frame.setBounds(450, 200, 541, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGenuri = new JLabel("Genuri");
		lblGenuri.setBackground(Color.LIGHT_GRAY);
		lblGenuri.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGenuri.setBounds(110, 155, 55, 14);
		contentPane.add(lblGenuri);
		
		JLabel lblFilm = new JLabel("Film:");
		lblFilm.setBounds(99, 36, 46, 14);
		contentPane.add(lblFilm);
		
		txtFilm = new JTextField();
		txtFilm.setBounds(155, 33, 250, 20);
		contentPane.add(txtFilm);
		txtFilm.setColumns(10);
		
		JLabel lblActori = new JLabel("Actori:");
		lblActori.setBounds(99, 75, 46, 14);
		contentPane.add(lblActori);
		
		txtActori = new JTextField();
		txtActori.setBounds(155, 72, 250, 20);
		contentPane.add(txtActori);
		txtActori.setColumns(10);
		
		JLabel lblAnLansare = new JLabel("An lansare:");
		lblAnLansare.setBounds(99, 117, 66, 14);
		contentPane.add(lblAnLansare);
		
		final JSpinner spinnerAnLansare = new JSpinner();
		spinnerAnLansare.setModel(new SpinnerNumberModel(2015, 2015, 2020, 1));
		spinnerAnLansare.setBounds(180, 114, 61, 20);
		contentPane.add(spinnerAnLansare);
		
		Box horizontalBoxGenuri = Box.createHorizontalBox();
		horizontalBoxGenuri.setToolTipText("");
		horizontalBoxGenuri.setBounds(109, 174, 307, 36);
		contentPane.add(horizontalBoxGenuri);
		
		final JCheckBox chckbxComedie = new JCheckBox("comedie");
		horizontalBoxGenuri.add(chckbxComedie);
		
		final JCheckBox chckbxDrama = new JCheckBox("drama");
		horizontalBoxGenuri.add(chckbxDrama);
		
		final JCheckBox chckbxIstoric = new JCheckBox("istoric");
		horizontalBoxGenuri.add(chckbxIstoric);
		
		final JCheckBox chckbxRomantic = new JCheckBox("romantic");
		horizontalBoxGenuri.add(chckbxRomantic);
		
		JCheckBox chckbxActiune = new JCheckBox("actiune");
		horizontalBoxGenuri.add(chckbxActiune);
		
		tableModel.addRow(new Object[] {coloane[0], coloane[1], coloane[2], coloane[3]});
		
		JButton btnAdaugare = new JButton("Adaugare");
		btnAdaugare.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String genuri = "";
				
				if(chckbxComedie.isSelected())
				{
					genuri = genuri + chckbxComedie.getText() + "  ";
					chckbxComedie.setSelected(false);
				}
				
				if(chckbxDrama.isSelected())
				{
					genuri = genuri + chckbxDrama.getText() + "  ";
					chckbxDrama.setSelected(false);
				}
				
				if(chckbxIstoric.isSelected())
				{
					genuri = genuri + chckbxIstoric.getText() + "  ";
					chckbxIstoric.setSelected(false);
				}
				
				if(chckbxRomantic.isSelected())
				{
					genuri = genuri + chckbxRomantic.getText() + "  ";
					chckbxRomantic.setSelected(false);
				}
				
				//data = new Object[] {txtFilm.getText(), txtActori.getText(), spinnerAnLansare.getValue(), genuri};
				tableModel.addRow(new Object[] {txtFilm.getText(), txtActori.getText(), spinnerAnLansare.getValue(), genuri});
				
				txtFilm.setText(null);
				txtActori.setText(null);
				spinnerAnLansare.setValue(2015);
			}
		});
		btnAdaugare.setBounds(110, 234, 149, 23);
		contentPane.add(btnAdaugare);
		
		JButton btnStergere = new JButton("Stergere");
		btnStergere.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int[] n = tableFilme.getSelectedRows();
				
				for(int i = n.length-1; i >= 0; i--)
				{
					tableModel.removeRow(n[i]);
				}
			}
		});
		btnStergere.setBounds(279, 234, 137, 23);
		contentPane.add(btnStergere);
		
		//js = new JScrollPane(tableFilme);
		//js.setBounds(10, 406, 664, 131);
		//contentPane.add(js);
		
		tableFilme.setBounds(0, 288, 525, 86);
		contentPane.add(tableFilme);
		
	}
}
