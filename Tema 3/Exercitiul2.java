package Tema3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Exercitiul2
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);
		BufferedReader buffer = new BufferedReader(new FileReader("ex2.txt"));
		
		StringBuilder str = new StringBuilder(buffer.readLine());
		System.out.println(str);

		System.out.println("Dati pozitia de inserare pana in " + str.length() + " :");
		int pozitie = scanner.nextInt();
		
		String str2 = buffer.readLine();
		StringBuilder afterInsertion = str.insert(pozitie, str2);
		System.out.println(afterInsertion);
		
		System.out.println("Dati pozitia de stergere pana in " + str.length() + " :");
		pozitie = scanner.nextInt();
		System.out.println("Numarul de caractere: ");
		int nr = scanner.nextInt();
		
		StringBuilder strDelete = str.delete(pozitie, pozitie+nr);
		System.out.println(strDelete);
		
		buffer.close();
		scanner.close();
		fluxIn.close();
	}

}
