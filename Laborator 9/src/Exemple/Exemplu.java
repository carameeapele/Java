package Exemple;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class Exemplu extends DefaultHandler
{
	private int sw;
	public Exemplu() { sw = 0; }
	
	// apelata automat in momentul in care incepe procesarea documenteleor XML
	public void startDocument() throws SAXException { System.out.println("Cursurile optionale sunt:"); }
	
	// apelata la aparitia unui tag
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		if (qName.equals("nume"))
			sw = 1;
		else if (qName.equals("studenti"))
		{
			String min = attributes.getValue("min");
			String max = attributes.getValue("max");
		
			if (min != null) System.out.println("\tMin = " + min);	
			
			if (max != null) System.out.println("\tMax = " + max);
		}
	}
	
	// apelata atunci cand este intalnit un tag de sfarsit. Daca tagul este de sine statator atunci se apeleaza automat dupa startElement
	public void endElement(String uri, String localName, String qName) throws SAXException { if (qName.equals("nume")) sw = 0; }
	
	// se executa la aparitia unui text aflat intre taguri; respectivul text se afla in ch incepand cu pozitia start si va contine length caractere
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		if (sw == 1)
		{
			System.out.print(" - ");
			
			for (int i = 0; i < length; i++)
				System.out.print(ch[start + i]);
			
			System.out.println("");
		}
	}
	
}

class Main
{
	public static void main(String[] args) 
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		DefaultHandler handler = new Exemplu();
		SAXParser parser;
		
		try
		{
			parser = factory.newSAXParser();
			parser.parse("optionale.xml", handler);
		} 
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		
	}
}
