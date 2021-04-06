package tema2;

class Parabola
{
	private double a, b, c;
	private double x, y;
		
	public Parabola(double aa, double bb, double cc)
	{
		a = aa;
		b = bb;
		c = cc;
	}
		
	public Parabola(Parabola parabola)
	{
		this.a = parabola.a;
		this.b = parabola.b;
		this.c = parabola.c;
	}
		
	public static void display(Parabola parabola)
	{
		System.out.println("f(x) = " + parabola.a + "x^2 + " + parabola.b + " x + " + parabola.c);
	}
		
	public static void coordonateVarf(Parabola parabola)
	{
		parabola.x = -parabola.b / (2 * parabola.a);
		parabola.y = (-(parabola.b * parabola.b) + 4 * parabola.a * parabola.c) / (4 * parabola.a);
		
		System.out.println("coordonate varf: " + parabola.x + " , " + parabola.y);
	}
		
	public static void coordonateMijloc(Parabola parabola1, Parabola parabola2)
	{
		Parabola.coordonateVarf(parabola1);
		Parabola.coordonateVarf(parabola2);
			
		double x = (parabola1.x + parabola2.x) / 2;
		double y = (parabola1.y + parabola2.y) / 2;
		
		System.out.println("coordonate mijloc: " + x + " , " + y);
	}
}

public class Exercitiu2
{
	public static void main(String[] arg)
	{
		Parabola parabola1 = new Parabola(3,9,7);
		Parabola.display(parabola1);
			
		Parabola parabola2 = new Parabola(2,4,6);
		Parabola.display(parabola2);
			
		Parabola.coordonateVarf(parabola1);
		Parabola.coordonateVarf(parabola2);
			
		Parabola.coordonateMijloc(parabola1, parabola2);
	}
}
