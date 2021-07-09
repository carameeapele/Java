package Exemple;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Exemplul2
{
	public static void rec(Node n)
	{
		NodeList aux;
		NamedNodeMap map;
		
		System.out.println(n.getNodeName());
		
		if(n.hasAttributes())
		{
			map = n.getAttributes();
			
			for(int i = 0; i < map.getLength(); i++)
				System.out.println("\t" + map.item(i).getNodeName() + " = \"" + map.item(i) .getNodeValue() + "\"");
		}
		
		if(n.hasChildNodes())
		{
			aux = n.getChildNodes();
			
			for(int i = 0; i < aux.getLength(); i++)
				rec(aux.item(i));
		}
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException 
	{	
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
		
			Document document = builder.parse("optionale.xml");
			Element elt = document.getDocumentElement();
		
			rec(elt);
		
			Node stud = document.createElement("student");
			Node n = document.createElement("nume");
			Node t = document.createTextNode("Ana Lana");
		
			n.appendChild(t);
			stud.appendChild(n);
		
			n = document.createElement("an");
			t = document.createTextNode("3");
		
			n.appendChild(t);
			stud.appendChild(n);
		
			n = document.createElement("grupa");
			t = document.createTextNode("2");
		
			n.appendChild(t);
			stud.appendChild(n);
			document.getDocumentElement().appendChild(stud);
		
			System.out.println("Lista tuturor studentilor:");
			NodeList lista = document.getElementsByTagName("nume");
		
			for (int i = 0; i < lista.getLength(); i++)
				System.out.println(lista.item(i).getFirstChild().getNodeValue());
			
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		
	}
}
