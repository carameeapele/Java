import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.*;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
//import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;

import java.util.*;
import javax.swing.Timer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Exercitiul1 extends JFrame
{

	private JPanel contentPane;
	private JLabel lblTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
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
	
	public static void rec(DefaultMutableTreeNode root, Node n)
	{
		DefaultMutableTreeNode node = null;
				
		if(n.hasChildNodes())
		{
			
			NodeList aux = n.getChildNodes();
			for(int i = 0; i < aux.getLength(); i++)
			{
				
				if(aux.item(i).getNodeType() == Node.ELEMENT_NODE)
				{
					node = new DefaultMutableTreeNode(aux.item(i).getNodeName());
					root.add(node);
				}
				
				if(aux.item(i).getNodeType() == Node.TEXT_NODE && aux.item(i).getNodeValue().trim().length() > 0)
				{
					node = new DefaultMutableTreeNode(aux.item(i).getNodeValue().trim());	
					root.add(node);
				}

				rec(node, aux.item(i));
			}
		}
	}
	
	/* CLOCK */
	
	public void startClock()
	{
		Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Date date = new Date();
            	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        		String time = timeFormat.format(date);
        		
        		lblTime.setText(time);
            }
        });
		
		timer.setInitialDelay(0);
		timer.start();
	}
	
	/**
	 * Create the frame.
	 */
	public Exercitiul1()
	{
		setTitle("Procesare XML");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnOpen = new JButton("Open XML");
		btnOpen.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String current = Path.of("").toAbsolutePath().toString();
				JFileChooser fileChooser=new JFileChooser(current);

				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".xml", "xml"));
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				int file = fileChooser.showOpenDialog(null);
				
				if (file == JFileChooser.APPROVE_OPTION)
				{
					File f = fileChooser.getSelectedFile();
					String filePath = f.getPath();
					
					/* CONNECT TO THE XML FILE */
					try
					{
						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						DocumentBuilder builder = factory.newDocumentBuilder();
						
						Document document = builder.parse(filePath);
						Element elt = document.getDocumentElement();
						
						//create the root node
						DefaultMutableTreeNode root = new DefaultMutableTreeNode(elt.getNodeName());
						rec(root, elt);
						
						JTree tree = new JTree(root);
						tree.setBorder(new EmptyBorder(0, 5, 0, 0));
						contentPane.add(tree, BorderLayout.CENTER);
						
						JScrollPane jspScroll = new JScrollPane(tree);
						jspScroll.setBounds(10, 406, 664, 131);
						contentPane.add(jspScroll);
					}
					catch(Exception exception)
					{
						System.out.println(exception.toString());
					}
				}
			}
		});
		panel.add(btnOpen, BorderLayout.WEST);
		
		lblTime = new JLabel("Time");
		panel.add(lblTime, BorderLayout.EAST);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		panel.add(verticalStrut, BorderLayout.SOUTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		panel.add(verticalStrut_1, BorderLayout.NORTH);
		
		startClock();
		
	}

}
