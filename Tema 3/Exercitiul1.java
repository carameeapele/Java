package Tema3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class Vers
{
	private String vers;
	private double nrGenerat;
	
	public Vers(String vers)
	{
		this.vers = vers;
	}
	
	public Vers(Vers vers)
	{
		this(vers.vers);
	}
	
	public int numarCuvinte()
	{
		//am numarat cuvintele despartite prin ' ca doua cuinte: don't, I'll etc
		String[] cuvant = vers.split("\\W+");
				
		return cuvant.length;
	}
	
	public int numarVocale()
	{
		int nrVocale = 0;
		String vocale = "aeiou";
		vers = vers.toLowerCase();
		
		for(int i = 0; i < vers.length(); i++)
			if(vocale.indexOf(vers.charAt(i)) >= 0)
				nrVocale++;
		
		return nrVocale;
	}
	
	public int grupareLitere(String grupare)
	{
		String[] cuvant = vers.split("\\W+");
		int n = cuvant.length -1;
		int x;
		
		if(cuvant[n].length() >= grupare.length())
			x = cuvant[n].length() - grupare.length();
		else
			x = grupare.length() - cuvant[n].length();
		
		if(cuvant[n].equals(grupare))
			return 1;
		if(grupare.equals(cuvant[n].substring(x)))
			return 1;
		else
			return 0;	
	}
	
	public int numarGenerat()
	{
		nrGenerat = Math.random();
		
		if(nrGenerat < 0.1)
			return 1;
		else
			return 0;
	}
	
	public double getNrGenerat()
	{
		return nrGenerat;
	}
}

public class Exercitiul1
{	
	public static void main(String[] args) throws IOException
	{
		File fileIn = new File("cantec_in.txt");
		File fileOut = new File("cantec_out.txt");
		
		if(fileOut.createNewFile())
		{
			System.out.println("\nS-a creat fisierul " + fileOut.getName());
		}
		else
		{
			System.out.println("\nFisierul " + fileOut.getName() +" exista deja");
		}
		
		Scanner fileScanner = new Scanner(fileIn);
		BufferedReader buffer = new BufferedReader(new FileReader(fileIn));
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fileWriter = new FileWriter(fileOut);
		
		int nrVersuri = 0;
		while(fileScanner.hasNextLine())
		{
			fileScanner.nextLine();
			nrVersuri++;
		}
		
		Vers[] vers = new Vers[nrVersuri];
		
		System.out.println("Dati gruparea de litere: ");
		String grupare = fluxIn.readLine();
		
		String linie;
		int i = 0;
		while((linie = buffer.readLine()) != null)
		{
			vers[i] = new Vers(linie);
			int nrCuvinte = vers[i].numarCuvinte();
			int nrVocale = vers[i].numarVocale();
			
			if(vers[i].numarGenerat() == 1)
				fileWriter.write(linie.toUpperCase());
			else
				fileWriter.write(linie);
			
			fileWriter.write(" " + nrCuvinte + " " + nrVocale /*+ " " + vers[i].getNrGenerat()*/);
			
			if(vers[i].grupareLitere(grupare) == 1)
				fileWriter.write(" *");
			
			fileWriter.write("\n");
			i++;
		}
		
		fluxIn.close();
		fileScanner.close();
		buffer.close();
		fileWriter.close();
	}

}
