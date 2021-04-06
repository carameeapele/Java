package tema;

import java.util.Scanner;

public class Dreptunghi 
{
	public static void main(String[] args) 
	{
		System.out.println("Lungime: ");
		
		Scanner scanner = new Scanner(System.in);
		int Lungime = scanner.nextInt();
		
		System.out.println("Latime: ");

		int Latime = scanner.nextInt();
		
		int Perimetru = 2*(Lungime + Latime);
		int Arie = Lungime * Latime;
		
		System.out.println("Perimetrul: "+Perimetru);
		System.out.println("Aria: "+Arie);
		
		scanner.close();
	}
}
