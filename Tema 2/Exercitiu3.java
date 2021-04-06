package tema2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Produs
{
	private String denumire;
	private double pret;
	private double cantitate;
	
	public Produs(String denumire, double pret, double cantitate)
	{
		this.denumire = denumire;
		this.pret = pret;
		this.cantitate = cantitate;
	}
	
	public Produs(Produs produs)
	{
		this(produs.denumire, produs.pret, produs.cantitate);
	}
	
	public String toString()
	{
		return this.denumire + " " + this.pret + " " + this.cantitate;
	}
	
	public String getDenumire()
	{
		return denumire;
	}
	
	public double getPret()
	{
		return pret;
	}
	
	public double getCantitate()
	{
		return cantitate;
	}
}

public class Exercitiu3
{
	@SuppressWarnings("resource")
	public static void main(String[] arg) throws IOException
	{
		Produs[] produs = new Produs[5];

		File fileIn = new File("fisier.txt");
		File fileOut = new File("destinatie.txt");
		
		Scanner scanner = new Scanner(System.in);
		BufferedReader buffer = new BufferedReader(new FileReader(fileIn));
		FileWriter fileWriter = new FileWriter(fileOut);
		
		String linie;
		int i = 0;
		while ((linie = buffer.readLine()) != null) 
		{
			String[] s = linie.split(";");
			
			produs[i] = new Produs(s[0], Double.parseDouble(s[1]), Double.parseDouble(s[2]));
			System.out.println(produs[i].toString());
			i++;
		}
		
		Produs minim = produs[0];
		Produs maxim = produs[0];
		
		for(i = 1; i < 5; i++)
		{
			if(produs[i].getPret() < minim.getPret())
				minim = produs[i];
			
			if(produs[i].getPret() > maxim.getPret())
				maxim = produs[i];
		}
		
		fileWriter.write("Minim: " + minim.toString());
		fileWriter.write("\nMaxim: " + maxim.toString());
		
		System.out.println("Dati valoarea: ");
		int valoare = scanner.nextInt();
		
		for(i = 0; i < 5; i++)
		{
			if(produs[i].getCantitate() < valoare)
				System.out.println(produs[i].toString());
		}
				
		scanner.close();
		buffer.close();
		fileWriter.close();
	}
}


