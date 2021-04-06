package Tema6;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*Sa se scrie un program care citeste de la tastatura o pereche de numere in 
care primul numar trebuie sa fie mai mic decat al doilea. Daca aceasta conditie
nu este indeplinita, folosind mecanismul exceptiilor, se va semnala eroare si
se va trata aceasta eroare prin cererea unei alte perechi de numere. 
Perechea care indeplineste conditia va fi scrisa intrun fisier primit ca
parametru la apel.*/

class MyException extends Exception
{
	MyException(String s)
	{
		super(s);
	}
}

class Pereche
{
	private static int x;
	private static int y;
	
	public Pereche(int x, int y)
	{
		Pereche.x = x;
		Pereche.y = y;
	}
	
	public static boolean verificare() throws MyException
	{
		if(x < y)
			return true;
		else
			throw new MyException("Primul numar e mai mare decat al doilea");
	}
	
	public String toString()
	{
		return x + " " + y;
	}
}

public class Exercitiul1
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);

		System.out.println("Dati numele fisierului: ");
		String file = fluxIn.readLine();
		FileWriter fileWriter = new FileWriter(file);
		Pereche pereche = new Pereche(0, 0);
		
		boolean done = false;
		
		do
		{
			try
			{
				System.out.println("Dati pereche de numere: primul < al doilea");
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				pereche = new Pereche(x, y);
				
				done = pereche.verificare();
			}
			catch (MyException e)
			{
				System.out.println(e);
			}
			
		}while(!done);
		
		fileWriter.write(pereche.toString());
		System.out.println("Complete!");
		
		scanner.close();
		fileWriter.close();
		fluxIn.close();
	}

}
