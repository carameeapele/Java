import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tema7 extends JFrame
{
	private JPanel contentPane;
	private JButton btnScadere;
	private JTextField txtResultat;
	private JTextField txtOperand1;
	private JTextField txtOperand2;

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
					Tema7 frame = new Tema7();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Tema7()
	{
		//JFrame myFrame = new JFrame("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 450, 300);
		//myFrame.setSize(300, 300);
		getContentPane().setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOperand1 = new JLabel("Operand 1");
		lblOperand1.setBounds(116, 51, 64, 14);
		contentPane.add(lblOperand1);
		
		txtOperand1 = new JTextField();
		txtOperand1.setBounds(190, 48, 129, 20);
		contentPane.add(txtOperand1);
		txtOperand1.setColumns(10);
		
		JLabel lblOperand2 = new JLabel("Operand2");
		lblOperand2.setBounds(116, 82, 64, 14);
		contentPane.add(lblOperand2);
		
		txtOperand2 = new JTextField();
		txtOperand2.setBounds(190, 79, 129, 20);
		contentPane.add(txtOperand2);
		txtOperand2.setColumns(10);
		
		JButton btnAdunare = new JButton("Adunare");
		btnAdunare.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int Operand1 = Integer.parseInt(txtOperand1.getText());
					int Operand2 = Integer.parseInt(txtOperand2.getText());
					
					int rezultat = Operand1 + Operand2;
					txtResultat.setText("" + rezultat);
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Nu se pot introduce litere");
				}
			}
		});
		
		btnAdunare.setBounds(116, 110, 89, 23);
		contentPane.add(btnAdunare);
		
		btnScadere = new JButton("Scadere");
		btnScadere.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int Operand1 = Integer.parseInt(txtOperand1.getText());
					int Operand2 = Integer.parseInt(txtOperand2.getText());
					
					int rezultat = Operand1 - Operand2;
					txtResultat.setText("" + rezultat);
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Nu se pot introduce litere");
				}
			}
		});
		btnScadere.setBounds(233, 110, 89, 23);
		contentPane.add(btnScadere);
		
		JButton btnInmultire = new JButton("Inmultire");
		btnInmultire.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int Operand1 = Integer.parseInt(txtOperand1.getText());
					int Operand2 = Integer.parseInt(txtOperand2.getText());
					
					int rezultat = Operand1 * Operand2;
					txtResultat.setText("" + rezultat);
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Nu se pot introduce litere");
				}
			}
		});
		btnInmultire.setBounds(116, 144, 89, 23);
		contentPane.add(btnInmultire);
		
		JButton btnImpartie = new JButton("Impartire");
		btnImpartie.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int Operand1 = Integer.parseInt(txtOperand1.getText());
					int Operand2 = Integer.parseInt(txtOperand2.getText());
					
					int rezultat = Operand1 / Operand2;
					txtResultat.setText("" + rezultat);
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Nu se pot introduce litere");
				}
				catch(ArithmeticException ae)
				{
					JOptionPane.showMessageDialog(null, "Impartitorul nu poate sa fie 0");
				}
			}
		});
		btnImpartie.setBounds(233, 144, 89, 23);
		contentPane.add(btnImpartie);
		
		txtResultat = new JTextField();
		txtResultat.setBounds(116, 178, 206, 20);
		contentPane.add(txtResultat);
		txtResultat.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				txtOperand1.setText(null);
				txtOperand2.setText(null);
				txtResultat.setText(null);
			}
		});
		btnClear.setBounds(116, 209, 206, 23);
		contentPane.add(btnClear);
	}
}
