package tema;

import java.util.Scanner;

public class Exercitiul3
{

	public static void main(String[] args)
	{
		int ok = 0;
		
		System.out.println("Dati n: ");
		
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		
		for(int i = 2; i < n; i++)
		{
			if(n % i == 0)
			{
				ok = 1;
				System.out.println(" "+i);
			}
		}
		
		if(ok == 0)
		{
			System.out.println("Numarul nu are divizori");
		}
		
		scanner.close();
	}

}
