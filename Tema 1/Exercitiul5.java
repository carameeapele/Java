package tema;

import java.util.Random;

public class Exercitiul5
{
	public static void main(String[] args) 
	{
		int x, ok = 0;
		Random random = new Random();
		
		x = random.nextInt(20);
		
		System.out.println("x = "+x);
		
		//numerele din sirul lui Fibonacci mai mici de 20
		int[] array = {0, 1, 1, 2, 3, 5, 8, 13};
		
		for(int i = 0; i < 8; i++)
		{
			if(array[i] == x)
			{
				ok = 1;
				break;
			}
		}
		
		if(ok == 1)
		{
			System.out.println("Apartine sirului Fibonacci");
		}
		else
		{
			System.out.println("Nu apartine sirului Fibonacci");
		}
	}
}
