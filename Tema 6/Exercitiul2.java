/*Sa se scrie un program care citeste doua numere de la tastatura si afiseaza
rezultatul impartirii celor doua numere. Programul va utiliza mecanismul de
tratare a exceptiilor pentru a cere reintroducerea impartitorului atunci cand
se realizeaza o impartire la 0, si pentru a cere reintroducerea corecta a
numerelor daca in loc de numere s-au introdus cuvinte.

La problema 2 utilizati mecanismul de tratare a exceptiilor din Java si
clasele predefinite pentru tratarea exceptiilor existente in API-ul de Java.*/

package Tema6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Clasa
{
	private int x;
	private int y;
	
	public Clasa()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public void setX() throws IOException
	{
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));
		boolean intValidate = true;
		
		do
		{	
			System.out.println("x = ");
			String s = fluxIn.readLine();
			
			try
			{
				this.x = Integer.parseInt(s);
				intValidate = true;
			}
			catch (NumberFormatException e)
			{
				intValidate = false;
				System.out.println("Valoarea introdusa nu este int");
			}
			
		}while(!intValidate);
		
	}
	
	public void setY() throws IOException
	{
		BufferedReader fluxIn = new BufferedReader(new InputStreamReader(System.in));
		boolean intValidate = true;
		
		do
		{	
			System.out.println("y = ");
			String s = fluxIn.readLine();
			
			try
			{
				this.y = Integer.parseInt(s);
				intValidate = true;
			}
			catch (NumberFormatException e)
			{
				intValidate = false;
				System.out.println("Valoarea introdusa nu este int");
			}
			
		}while(!intValidate);
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}
	
	public String toString()
	{
		return x + " " + y;
	}
}

public class Exercitiul2
{
	public static void main(String[] args) throws IOException
	{
		boolean done = true;
		Clasa clasa = new Clasa();
		float c = 0;
		
		clasa.setX();
		clasa.setY();
		
		do
		{
			try
			{
				c = clasa.getX() / clasa.getY();
				done = true;
			}
			catch (ArithmeticException e)
			{
				done = false;
				System.out.println("Impartitorul nu poate as fie 0");
				clasa.setY();
			}
			
		}while(!done);

		System.out.println(clasa.getX() + " / " + clasa.getY() + " = " + c);
	}
}
