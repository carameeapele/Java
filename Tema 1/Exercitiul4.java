package tema;

import java.util.Random;

public class Exercitiul4
{

	public static void main(String[] args)
	{
		int a, b, r;
		Random random = new Random();
		
		a = random.nextInt(30);
		b = random.nextInt(30);
		
		System.out.println("a = "+a);
		System.out.println("b = "+b);
		
		while(b != 0)
		{
			r = a % b;
			a = b;
			b = r;
		}
		
		System.out.println("CMMDC: "+a);
	}

}
