package Tema4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

enum Stare{achizitionat, expus, vandut};
enum modScriere{color, albnegru};
enum Format{A4, A3};
enum SistemOperare{windows, linux};

class Echipament implements Serializable
{
	protected String denumire;
	protected long nrInv;
	protected float pret;
	protected String zonaMag;
	Stare stare;
	
	public Echipament(String denumire, long nrInv, float pret, String zonaMag, Stare stare)
	{
		this.denumire = denumire;
		this.nrInv = nrInv;
		this.pret = pret;
		this.zonaMag = zonaMag;
		this.stare = stare;
	}
	
	public String toString()
	{
		return this.denumire + " " + this.nrInv + " " + this.pret + " " + this.zonaMag + " " + this.stare;
	}
}

class Imprimanta extends Echipament implements Serializable
{
	private int ppm;
	private String rezolutie;
	private int pCar;
	public modScriere scriere;
	
	public Imprimanta(String denumire, long nrInv, float pret, String zonaMag, Stare stare, int ppm, String rezolutie, int pCar, modScriere scriere)
	{
		super(denumire, nrInv, pret, zonaMag, stare);
		
		this.ppm = ppm;
		this.rezolutie = rezolutie;
		this.pCar = pCar;
		this.scriere = scriere;
	}
	
	public String toString()
	{
		return super.toString() + " " + this.ppm + " " + this.rezolutie + " " + this.pCar + " " + this.scriere;
	}
}

class Copiator extends Echipament implements Serializable
{
	private int ppm;
	private int pTon;
	Format format;
	
	public Copiator(String denumire, long nrInv, float pret, String zonaMag, Stare stare, int ppm, int pTon, Format format)
	{
		super(denumire, nrInv, pret, zonaMag, stare);
		
		this.ppm = ppm;
		this.pTon = pTon;
		this.format = format;
	}
	
	public String toString()
	{
		return super.toString() + " " + this.ppm + " " + this.pTon + " " + this.format;
	}
}

class SistemCalcul extends Echipament implements Serializable
{
	private String tipMon;
	private double vitProc;
	private double cHdd;
	SistemOperare sistem;
	
	public SistemCalcul(String denumire, long nrInv, float pret, String zonaMag, Stare stare, String tipMon, double vitProc, double cHdd, SistemOperare sistem)
	{
		super(denumire, nrInv, pret, zonaMag, stare);
		
		this.tipMon = tipMon;
		this.vitProc = vitProc;
		this.cHdd = cHdd;
		this.sistem = sistem;
	}
	
	public String toString()
	{
		return super.toString() + " " + this.tipMon + " " + this.vitProc + " " + this.cHdd + " " + this.sistem;
	}
}

public class Tema
{
	static void Scrie(Object object, String fisier)
	{
		try
		{
			FileOutputStream f = new FileOutputStream(fisier);
			ObjectOutputStream oos = new ObjectOutputStream(f);
			oos.writeObject(object);
			oos.close();
			f.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	static Object Citeste(String fisier)
	{
		FileInputStream f;
		try
		{
			f = new FileInputStream(fisier);
			ObjectInputStream ois = new ObjectInputStream(f);
			Object object = ois.readObject();
			ois.close();
			f.close();
			
			return object;
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException
	{
		File fileIn = new File("echipamente.txt");
		
		Scanner fileScanner = new Scanner(fileIn);
		Scanner scanner = new Scanner(System.in);
		BufferedReader buffer = new BufferedReader(new FileReader(fileIn));
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));
		
		int opt, i = 0, gasit, N = 0;
		long nr;
		String linie, stare = "gol";
		
		while(fileScanner.hasNextLine())
		{
			fileScanner.nextLine();
			N++;
		}
		
		Echipament[] echipamente = new Echipament[N];
		
		while((linie = buffer.readLine()) != null)
		{
			String[] s = linie.split(";");
			
			switch(s[5])
			{
			case "imprimanta":
				echipamente[i] = new Imprimanta(s[0], Long.parseLong(s[1]), Float.parseFloat(s[2]), s[3], Stare.valueOf(s[4]), Integer.parseInt(s[6]), s[7], Integer.parseInt(s[8]), modScriere.valueOf(s[9].toLowerCase()));
				break;
			case "copiator":
				echipamente[i] = new Copiator(s[0], Long.parseLong(s[1]), Float.parseFloat(s[2]), s[3], Stare.valueOf(s[4]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), Format.valueOf(s[8]));
				break;
			case "sistem de calcul":
				echipamente[i] = new SistemCalcul(s[0], Long.parseLong(s[1]), Float.parseFloat(s[2]), s[3], Stare.valueOf(s[4]), s[6], Double.parseDouble(s[7]), Double.parseDouble(s[8]), SistemOperare.valueOf(s[9]));
				break;
			}
			
			i++;
		}
		
		do
		{
			System.out.println("0. Iesire\n" + "1. Afisare imprimante\n" + "2. Afisare copiatoare\n" + "3. Afisare sisteme de calcul\n4. Modificare stare echipament\n5. Modificare setare scriere imprimanta\n6. Modificare format copiere copiator\n7. Instalare sistem de operare pe un sistem de calcul\n8. Afisare echipamente vandute\n9. Serializarea/deserializarea obiectelor din fisierul echip.bin");
			System.out.println("\n\nDati optiune: ");
			
			opt = scanner.nextInt();
			
			switch(opt)
			{
			case 0:
				//iesire
				break;
			case 1:
				//afisare imprimante
				for(i = 0; i < N; i++)
				{
					if(echipamente[i] instanceof Imprimanta)
							System.out.println(echipamente[i].toString());
				}
				
				//System.in.read();
				break;
			case 2:
				//afisare copiatoare
				for(i = 0; i < N; i++)
				{
					if(echipamente[i] instanceof Copiator)
						System.out.println(echipamente[i].toString());
				}
				
				//System.in.read();
				break;
			case 3:
				//afisare sisteme de calcul
				for(i = 0; i < N; i++)
				{
					if(echipamente[i] instanceof SistemCalcul)
						System.out.println(echipamente[i].toString());
				}
				
				//System.in.read();
				break;
			case 4:
				//modificare stare echipament
				System.out.println("Dati numar inventar pentru echipamentul de modificat");
				nr = scanner.nextLong();
				gasit = 0;
				
				for(i = 0; i < N; i++)
				{
					if(echipamente[i].nrInv == nr)
					{
						gasit = 1;
						System.out.println("achizitionat - a / expus - e / vandut - v");
						stare = scanner.next();
						
						switch(stare)
						{
						case "a":
							echipamente[i].stare = Stare.achizitionat;
							break;
						case "e":
							echipamente[i].stare = Stare.expus;
							break;
						case "v":
							echipamente[i].stare = Stare.vandut;
							break;
						}
						
						break;
					}
				}
				
				if(gasit == 0)
					System.out.println("Imprimanta cu numarul " + nr + " nu exista");
				
				break;
			case 5:
				System.out.println("Dati numar inventar pentru imprimanta dorita: ");
				nr = scanner.nextLong();
				gasit = 0;
				
				for(i = 0; i < N; i++)
				{
					if((echipamente[i].nrInv == nr) && (echipamente[i] instanceof Imprimanta))
					{
						gasit = 1;
						System.out.println("colo - c / alb negru - a");
						stare = scanner.next();
						Imprimanta imprimanta = (Imprimanta) echipamente[i];
						
						switch(stare)
						{
						case "c":
							imprimanta.scriere = modScriere.color;
							break;
						case "a":
							imprimanta.scriere = modScriere.albnegru;
							break;
						}
						break;
					}
				}
				
				if(gasit == 0)
					System.out.println("Imprimanta cu numarul " + nr + " nu exista");
				
				break;
			case 6:
				System.out.println("Dati numar inventar pentru copiatorul dorit: ");
				nr = scanner.nextLong();
				gasit = 0;
				
				for(i = 0; i < N; i++)
				{
					if((echipamente[i].nrInv == nr) && (echipamente[i] instanceof Copiator))
					{
						gasit = 1;
						System.out.println("A4 - 4 / A3 - 3");
						stare = scanner.next();
						Copiator copiator = (Copiator) echipamente[i];
						
						switch(stare)
						{
						case "4":
							copiator.format = Format.A4;
							break;
						case "3":
							copiator.format = Format.A3;
							break;							
						}
					break;
					}
						
				}
				if(gasit == 0)
					System.out.println("Copiatorul cu numarul " + nr + " nu exista");
				
				break;
			case 7:
				System.out.println("Dati numar inventar pentru sistemul de calcul dorit: ");
				nr = scanner.nextLong();
				gasit = 0;
				
				for(i = 0; i < N; i++)
				{
					if((echipamente[i].nrInv == nr) && (echipamente[i] instanceof SistemCalcul))
					{
						gasit = 1;
						System.out.println("windows - w / linux - l");
						stare = scanner.next();
						SistemCalcul sistemCalc = (SistemCalcul) echipamente[i];
						
						switch(stare)
						{
						case "w":
							sistemCalc.sistem = SistemOperare.windows;
							break;
						case "3":
							sistemCalc.sistem = SistemOperare.linux;
							break;							
						}
					break;
					}
						
				}
				if(gasit == 0)
					System.out.println("Sistwmule de calcul cu numarul " + nr + " nu exista");
				
				break;
			case 8:
				for(i = 0; i < N; i++)
				{
					if(echipamente[i].stare == Stare.vandut)
					{
						System.out.println(echipamente[i].toString());
					}
				}
				
				//System.in.read();
				break;
			case 9:
				Scrie(echipamente, "echip.bin");
				Echipament []e;
				e = (Echipament[])Citeste("echip.bin");
				for(Echipament echip:e)
					System.out.println(echip);
				
				break;
			default:
				System.out.println("\nOptiune gresita");
				System.in.read();
				break;
			}
			
		} while(opt != 0);
		
		scanner.close();
		fileScanner.close();
		buffer.close();
		fluxIn.close();
	}

}
