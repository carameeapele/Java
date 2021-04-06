package Tema6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/*Sa se scrie un program care citeste numele si CNP-ul unei persoane.
Mecanismul de tratare a exceptiilor va fi utilizat pentru introducerea unui
CNP corect. Daca CNP-ul a fost introdus corect va fi utilizat pentru a
determina data nasterii si a calcula varsta.

La problema 3, creati o clasa proprie pentru tratarea exceptiei. Pentru
simplitate, vom considera ca CNP-ul este valid in urmatoarele situatii:
- Are 13 caractere
- Toate sunt cifre
- Prima cifra care codifica sex-ul este valida daca este cuprinsa intre 1 si 6
- Cifrele care codifica luna sunt valide daca sunt cuprinse intre 1 si 12
- Cifrele care codifica ziua sunt valide daca sunt cuprinse intre 1 si 31

*/

class LungimeGresita extends Exception
{
	public String toString()
	{
		return "CNP-ul trebuie sa contina 13 cifre";
	}
}

class CaracterGresit extends Exception
{
	private int pozitie;
	
	public CaracterGresit(int i)
	{
		this.pozitie = i;
	}
	
	public String toString()
	{
		return "Caracterul de pe pozitia " + this.pozitie + " nu este cifra";
	}
}

class SexGresit extends Exception
{
	public String toStrinng()
	{
		return "Prima cifra a CNP-ului trebuie sa fie cuprinsa intre 1 si 6";
	}
}

class LunaGresita extends Exception
{
	public String toString()
	{
		return "Cifrele pentru luna trebuie sa fie intre 1 si 12";
	}
}

class ZiGresita extends Exception
{
	public String toString()
	{
		return "Cifrele pentru zi trebuie sa fie intre 1 si 31";
	}
}

class Persoana
{
	private String nume;
	private String CNP;
	private int varsta;
	private Calendar dataNasterii;
	
	public Persoana()
	{
		this.nume = " ";
		this.CNP = " ";
		this.varsta = 0;
		this.dataNasterii = Calendar.getInstance();
	}
	
	public void setNume(String nume)
	{
		this.nume = nume;
	}
	
	public void setCNP(String CNP)
	{
		this.CNP = CNP;
	}
	
	public void setDataNasterii()
	{
		int an = Integer.parseInt(CNP.substring(1, 3));
		int luna = Integer.parseInt(CNP.substring(3, 5));
		int zi = Integer.parseInt(CNP.substring(5, 7));
		
		if(CNP.charAt(0) <= '2')
			an = 1900 + an;
		else
			an = 2000 + an;
			
		dataNasterii.set(an, luna, zi);
	}
	
	public void setVarsta()
	{
		Calendar current = Calendar.getInstance();
		current.set(current.get(Calendar.YEAR), (current.get(Calendar.MONTH)+1), current.get(Calendar.DAY_OF_MONTH));
		
		long c = current.getTimeInMillis();
		long d = dataNasterii.getTimeInMillis();
		
		long zile = TimeUnit.MILLISECONDS.toDays(c-d);
		long ani = zile / 365;
		
		this.varsta = (int) ani;
		//this.varsta = current.get(Calendar.YEAR) - dataNasterii.get(Calendar.YEAR);
	}
	
	public String getNume()
	{
		return this.nume;
	}
	
	public String getCNP()
	{
		return this.CNP;
	}
	
	public Calendar getDataNasterii()
	{
		return this.dataNasterii;
	}
	
	public int getVarsta()
	{
		return this.varsta;
	}
	
	public String toString()
	{
		return this.nume + " " + this.CNP + " " + this.dataNasterii.get(Calendar.DAY_OF_MONTH) + "." + this.dataNasterii.get(Calendar.MONTH) + "." + this.dataNasterii.get(Calendar.YEAR) + " " + this.varsta;
	}
}

public class Exercitiul3
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));
		
		//Alin 1981001108964
		Persoana persoana = new Persoana();
		boolean correct = true;
		String CNP = " ";
		
		System.out.println("Dati numele persoanei: ");
		persoana.setNume(fluxIn.readLine());
		
		do
		{
			System.out.println("Dati CNP-ul persoanei: ");
			
			try
			{
				CNP = fluxIn.readLine();
				correct = true;
				
				//verificare lungime
				if(CNP.length() != 13)
					throw new LungimeGresita();
				
				//verificare caractere
				for(int i = 0; i < CNP.length(); i++)
				{
					if(CNP.charAt(i) < '0' || CNP.charAt(i) > '9')
						throw new CaracterGresit(i);
				}
				
				//verificare prima cifra
				if(CNP.charAt(0) < '1' || CNP.charAt(0) > '6')
					throw new SexGresit();
				
				//verificare luna
				String luna = CNP.substring(3, 5);
				if(luna.compareTo("01") < 0 || luna.compareTo("12") > 0)
					throw new LunaGresita();
				
				//verificare zi
				String zi = CNP.substring(5, 7);
				if(zi.compareTo("01") < 0 || zi.compareTo("31") > 0)
					throw new ZiGresita();

			}
			catch(LungimeGresita lungime)
			{
				correct = false;
				System.out.println("Eroare LUNGIME: " + lungime.toString());
			}
			catch(CaracterGresit caracter)
			{
				correct = false;
				System.out.println("Eroare CARACTER: " + caracter.toString());
			}
			catch(SexGresit sex)
			{
				correct = false;
				System.out.println("Eroare SEX: " + sex.toString());
			}
			catch(LunaGresita luna)
			{
				correct = false;
				System.out.println("Eroare LUNA: " + luna.toString());
			}
			catch(ZiGresita zi)
			{
				correct = false;
				System.out.println("Eroare ZI: " + zi.toString());
			}
			
		}while(!correct);
		
		persoana.setCNP(CNP);
		persoana.setDataNasterii();
		persoana.setVarsta();
		
		System.out.println(persoana.toString());
		
		fluxIn.close();
	}

}

