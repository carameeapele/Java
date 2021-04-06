package Tema5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Carte
{
	protected String titlu;
	protected String autor;
	protected int anAparitie;
	
	public Carte(String titlu, String autor, int an)
	{
		this.titlu = titlu;
		this.autor = autor;
		this.anAparitie = an;
	}
	
	public String getTitlu() { return this.titlu; }
	public String getAutor() { return this.autor; }
	public int getAn() { return this.anAparitie; }
	
	public String toString()
	{
		return this.titlu + ", " + this.autor + ", " + this.anAparitie;
	}
}

class ComparaTitlu implements Comparator
{
	@Override
	public int compare(Object o1, Object o2)
	{
		return (((Carte)o1).getTitlu().compareTo(((Carte)o2).getTitlu()));
	}
}

public class Exercitiul2
{
	public static void main(String[] args) throws IOException
	{
		Map<Integer,Carte> carti = new HashMap<Integer,Carte> ();
		
		File fileIn = new File("in2.txt");
		BufferedReader buffer = new BufferedReader(new FileReader(fileIn));
		
		String linie;
		while((linie = buffer.readLine()) != null)
		{
			String s[] = linie.split("; ");
			
			carti.put(Integer.parseInt(s[0]), new Carte(s[1], s[2], Integer.parseInt(s[3])));
		}
		
		System.out.println("Colectia Map:");
		System.out.println(carti.toString());
		
		List<Integer> keyList = new ArrayList<Integer>(carti.keySet());
		List<Carte> valueList = new ArrayList<Carte>(carti.values());
		
		System.out.println("\nList Neordonat:");
		System.out.println(valueList.toString());
		
		System.out.println("\nList Ordonat:");
		Collections.sort(valueList, new ComparaTitlu());
		System.out.println(valueList.toString());
		
		buffer.close();
	}
}
