package Tema3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Exercitiul3
{
	public static void main(String[] args) throws IOException
	{
		File fileIn = new File("judete_in.txt");
		File fileOut = new File("judete_out.txt");
		
		BufferedReader buffer = new BufferedReader(new FileReader(fileIn));
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));

		Scanner fileScanner = new Scanner(fileIn);
		FileWriter fileWriter = new FileWriter(fileOut);

		//se creaza un fisier nou cu judetele ordonate pt o vizualizare mai usoara
		if(fileOut.createNewFile())
		{
			System.out.println("\nS-a creat fisierul " + fileOut.getName() + " cu judetele ordonate");
		}
		else
		{
			System.out.println("\nFisierul " + fileOut.getName() +" exista deja");
		}
		
		int n = 0;
		while(fileScanner.hasNextLine())
		{
			fileScanner.nextLine();
			n++;
		}
		
		String[] judete = new String[n];
		String linie; int i = 0;
		
		while((linie = buffer.readLine()) != null)
		{
			judete[i] = linie;
			i++;
		}
		
		Arrays.sort(judete, Comparator.naturalOrder());
		
		fileWriter.write(Arrays.toString(judete));
		
		System.out.println("Dati judetul cautat: ");
		String judetCautat = fluxIn.readLine();
		
		System.out.println("Gasit la pozitia: " + Arrays.binarySearch(judete, judetCautat));
		
		
		fluxIn.close();
		fileWriter.close();
		fileScanner.close();
		buffer.close();
	}

}
