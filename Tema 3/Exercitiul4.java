package Tema3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Melodie implements Comparable<Melodie>
{
	private String numeMelodie, numeArtist;
	private int anLansare;
	private double numarVizualizariYoutube;
	
	public Melodie(String numeMelodie, String numeArtist, int an, double vizualizari)
	{
		this.numeMelodie = numeMelodie;
		this.numeArtist = numeArtist;
		this.anLansare = an;
		this.numarVizualizariYoutube = vizualizari;
	}
	
	public String getMelodie()
	{
		return numeMelodie;
	}
	
	public String getArtist()
	{
		return numeArtist;
	}
	
	public int getAn()
	{
		return anLansare;
	}
	
	public double getVizualizari()
	{
		return numarVizualizariYoutube;
	}
	
	public String toString()
	{
		return this.numeMelodie + " - " + this.numeArtist + " - " + this.anLansare + " - " + this.numarVizualizariYoutube + " vizualizari";
	}
	
	public int compareTo(Melodie melodie)
	{
		if(this.numarVizualizariYoutube != melodie.getVizualizari())
		{
			return (int) (this.numarVizualizariYoutube - melodie.getVizualizari());
		}
		
		return this.numeMelodie.compareTo(melodie.getMelodie());
	}
}

public class Exercitiul4
{
	public static void main(String[] args) throws IOException 
	{
		File fileIn = new File("in.txt");
		
		Scanner fileScanner = new Scanner(fileIn);
		BufferedReader buffer = new BufferedReader(new FileReader(fileIn));
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));
	
		int nrMelodii = 0;
		while(fileScanner.hasNextLine())
		{
			fileScanner.nextLine();
			nrMelodii++;
		}
		
		Melodie[] arrayMelodii = new Melodie[nrMelodii];
		
		String linie;
		int i = 0;
		while ((linie = buffer.readLine()) != null) 
		{
			String[] s = linie.split("-");
			arrayMelodii[i] = new Melodie(s[0], s[1], Integer.parseInt(s[2]), Double.parseDouble(s[3]));
			
			System.out.println(arrayMelodii[i].toString());
			i++;
		}
		
		System.out.println("\nMelodiile sortate: \n");
		Arrays.sort(arrayMelodii, Comparator.reverseOrder());

		for(i = 0; i < nrMelodii; i++)
		{
			System.out.println(arrayMelodii[i].toString());
		}
		
		System.out.println("\nDati numele artistului");
		String artist = fluxIn.readLine();

		for(i = 0; i < nrMelodii; i++)
		{
			if(arrayMelodii[i].getArtist().compareTo(artist) == 0)
				System.out.println(arrayMelodii[i].getMelodie().toString());
		}
		
		fileScanner.close();
		buffer.close();
		fluxIn.close();
	}
}
