package Exercitiul1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Angajat
{
	String nume;
	String post;
	LocalDate dataAngajarii;
	float salar;
	
	public Angajat(String nume, String post, LocalDate dataAngajarii, float salar)
	{
		this.nume = nume;
		this.post = post;
		this.dataAngajarii = dataAngajarii;
		this.salar = salar;
	}
	
	public void setNume(String nume) { this.nume = nume; }
	public void setPost(String post) { this.post = post; }
	public void setDataAngajarii(LocalDate dataAngajarii) { this.dataAngajarii = dataAngajarii; }
	public void setSalar(float salar) { this.salar = salar; }
	
	public String getNume() { return this.nume; }
	public String getPost() { return this.post; }
	public LocalDate getDataAngajarii() { return this.dataAngajarii; }
	public float getSalar() { return this.salar; }
	
	public String toString() { return this.nume + ", " + this.post + ", " + this.dataAngajarii + ", " + this.salar; }
}

public class Exercitiul1
{
	public static void Menu()
	{
		System.out.println("0. Iesire");
		System.out.println("1. Afiseaza angajatii");
		System.out.println("2. Afiseza angajatii care au salariul peste 2500 ron");
		System.out.println("3. Lista cu angajatii din luna aprilie, a anului curent, care au functie de conducere");
		System.out.println("4. Angajatii care nu au functie de conducere");
		System.out.println("5. Numele angajatilor scrise cu majuscule");
		System.out.println("6. Afiseaza salariile mai mici de 3000 de RON");
	}
	
	public static void main(String[] args) throws IOException
	{
		File file = new File("in.txt");
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		Scanner scanner = new Scanner(System.in);
		
		List<Angajat> angajati = new ArrayList<Angajat>();
		String linie;
		int opt;
		
		while((linie = buffer.readLine()) != null)
		{
			String[] s = linie.split(",");
			Angajat angajat = new Angajat(s[0], s[1], LocalDate.parse(s[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")), Float.parseFloat(s[3]));
			angajati.add(angajat);
			
		}
		
		do
		{
			Menu();
			System.out.println("\nDati optiunea:");
			opt = scanner.nextInt();
			
			switch(opt)
			{
				case 0:
					// Iesire
					break;
				case 1:
					// Afiseaza angajatii
					angajati.forEach(System.out::println);
					
					System.in.read();
					break;
				case 2:
					// Afiseza angajatii care au salariul peste 2500 ron
					
					angajati
					.stream()
					.filter((angajat) -> angajat.getSalar() > 2500)
					.forEach(System.out::println);
					
					System.in.read();
					break;
				case 3:
					// Lista cu angajatii din luna aprilie, a anului curent, care au functie de conducere
					
					angajati
					.stream()
					.filter((angajat) -> angajat.getDataAngajarii().getMonthValue() == 4 
								&& angajat.getDataAngajarii().getYear() == LocalDateTime.now().getYear()
								&& angajat.getPost().contains("director")
								|| angajat.getPost().contains("sef"))
					.collect(Collectors.toList())
					.forEach(System.out::println);
					
					System.in.read();
					break;
				case 4:
					// Angajatii care nu au functie de conducere
					
					angajati
					.stream()
					.filter((angajat) -> !angajat.getPost().contains("director")
								&& !angajat.getPost().contains("sef"))
					.sorted((Angajat a, Angajat b) -> { 
						if (a.getSalar() > b.getSalar())
							return -1;
						else
							if( a.getSalar() < b.getSalar())
								return 1;
							else
								return 0;
					})
					.forEach(System.out::println);
					
					System.in.read();
					break;
				case 5:
					// Numele angajatilor scrise cu majuscule
					
					angajati
					.stream()
					.map(Angajat::getNume)
					.map(String::toUpperCase)
					.collect(Collectors.toList())
					.forEach(System.out::println);
					
					System.in.read();
					break;
				case 6:
					// Afiseaza salariile mai mici de 3000 de RON
					
					angajati
					.stream()
					.filter((angajat) -> angajat.getSalar() < 3000)
					.map(Angajat::getSalar)
					.forEach(System.out::println);
					
					System.in.read();
					break;
			}
			
		} while(opt != 0);
		
		
		buffer.close();
		scanner.close();
	}
}
