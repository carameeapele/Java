import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Exercitiul2 extends JFrame
{

	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtIn;
	private JList listFormatii;
	private JButton btnStergere;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					Exercitiul2 window = new Exercitiul2();
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
	public Exercitiul2()
	{
		super("JList");
		frame = new JFrame("Formatii");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(450, 200, 450, 300);
		frame.getContentPane().setLayout(new BorderLayout());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		txtIn = new JTextField();
		txtIn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				listModel.addElement(txtIn.getText());
				txtIn.setText(null);
			}
		});
		frame.getContentPane().add(BorderLayout.NORTH, txtIn);
		
		final JList<String> listFormatii = new JList<String>(listModel);
		listModel.addElement(txtIn.getText());
		frame.getContentPane().add(BorderLayout.CENTER, listFormatii);
		
		JScrollPane jspScroll = new JScrollPane(listFormatii);
		jspScroll.setBounds(10, 406, 664, 131);
		frame.getContentPane().add(jspScroll);
		
		btnStergere = new JButton("Stergere");
		btnStergere.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int[] n = listFormatii.getSelectedIndices();
				
				for(int i = n.length-1; i >= 0; i--)
				{
					listModel.removeElementAt(n[i]);
				}
			}
		});
		frame.getContentPane().add(BorderLayout.SOUTH, btnStergere);
	}
}