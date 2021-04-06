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

class Exceptie extends Exception
{
	private int x;
	private int y;
	
	public Exceptie(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return x + " este mai mare sau egal cu " + y;
	}
}

class Pair
{	
	public static boolean Verificare(int x, int y) throws Exceptie
	{
		if(x < y)
			return true;
		else
			throw new Exceptie(x, y);
	}
	
	public static void writeFile(int x, int y) throws IOException
	{
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Dati numele fisierului: ");
		String file = fluxIn.readLine();
		FileWriter fileWriter = new FileWriter(file);
		
		fileWriter.write( "(" + x + " , " + y + ")" );
		fileWriter.close();
	}
}

public class Exemplu
{
	public static void main(String[] args) throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		
		int x, y;
		boolean done = false;
		
		do
		{
			System.out.println("Dati pereche de numere: primul < al doilea");
			x = scanner.nextInt();
			y = scanner.nextInt();
				
			try
			{
				done = Pair.Verificare(x, y);
			}
			catch (Exceptie e)
			{
				System.out.println(e.toString());
			}
			
		}while(!done);
		
		Pair.writeFile(x, y);
		System.out.println("Complete!");
		
		scanner.close();
	}

}

//---------------------------------------------------------------------

/*
class CNPgresit extends Exception
{
    String CNP;
    
    public CNPgresit(String CNP)
    {
    	this.CNP = CNP;
    }
    public String toString(String CNP)
    {
    	return "CNP: " + CNP + " invalid!\n";
    }
}

class GenGresit extends Exception
{
    char s;
    
    public GenGresit(char s)
    {
    	this.s = s;
    }
    
    public String toString()
    {
    	return "prima cifra din CNP e" + s + ", nu 1/2 sau 5/6!";
    }
}

class ZiGresita extends Exception
{
    String day;
    
    public ZiGresita(String day)
    {
    	this.day=day;
    }
    
    public String toString()
    {
    	return "Ziua introdusa, " + day + ", nu e in intervalul [1,31]";
    }
}

class LunaGresita extends Exception{
    String month;
    public LunaGresita(String month) {
	this.month=month;
    }
    public String toString() {
  	return "Luna introdusa, " + month + ", nu e in intervalul [1,12]";
      }
}


public class Ex3
{
    public static void main(String[] args) throws CNPgresit, GenGresit, ZiGresita, LunaGresita, ParseException
    {
	Scanner in=new Scanner(System.in);
	String nume, CNP = " ";
	boolean ok = false;
	System.out.println("Numele: ");
	nume=in.nextLine();
	
	do {
	    try {
		System.out.println("CNP-ul lui "+nume+": ");
		CNP=in.nextLine();
		if(CNP.length()!=13)
		    throw new CNPgresit(CNP);
		for(int i=0;i<CNP.length();i++)
		{
		    if(CNP.charAt(i)<'0'||CNP.charAt(i)>'9')
			throw new CNPgresit(CNP);
		}
		if(CNP.charAt(0)<'1'||CNP.charAt(0)>'6')
		    throw new GenGresit(CNP.charAt(0));
		
		if(CNP.substring(3, 5).compareTo("01")<0||CNP.substring(3, 5).compareTo("12")>0)
		    throw new LunaGresita(CNP.substring(3,5));
		
		if(CNP.substring(5, 7).compareTo("01")<0||CNP.substring(5, 7).compareTo("31")>0)
		    throw new ZiGresita(CNP.substring(5,7));
		ok=true;
		}
	    catch(CNPgresit e1){
		System.out.println("Exceptie format: "+ e1.toString());
		//ok=true;
	    }
	    catch(GenGresit e2){
		System.out.println("Exceptie gen: "+ e2.toString());
		//ok=true;
	    }
	    catch(LunaGresita e3){
		System.out.println("Exceptie luna: "+ e3.toString());
		//ok=true;
	    }
	    catch(ZiGresita e4){
		System.out.println("Exceptie zi: "+ e4.toString());
		//ok=true;
	    }
	}while(ok!=true);
	
	System.out.println("CNP-ul introdus este corect!\n\n");
	int zi, luna, an=0;
	
	if(CNP.charAt(0)=='1' || CNP.charAt(0)=='2')
	    an=an+1900+Integer.parseInt(CNP.substring(1,3));
	else if(CNP.charAt(0)=='5' || CNP.charAt(0)=='6')
	    an=an+2000+Integer.parseInt(CNP.substring(1,3));
	luna=Integer.parseInt(CNP.substring(3,5));
	zi=Integer.parseInt(CNP.substring(5,7));
	//Calendar today=Calendar.getInstance();
	//SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
	LocalDate bday=LocalDate.of(an, Month.of(luna),zi);
	//bday.setTime(sdf.parse(CNP.substring(1, 7)));
	Period age=Period.between(bday, LocalDate.now());
	System.out.println(nume+" cu CNP-ul: "+CNP+"\ns-a nascut in data de "+bday+"\nsi are varsta de "+age.getYears()+" ani si "+age.getMonths()+" luni\n");
	in.close();
    }

}

*/