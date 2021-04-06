package tema2;

class Punct
{
	private int x;
	private int y;
	
	public void init(int xx, int yy)
	{
		x = xx; 
		y = yy;
	}
	
	public void move(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	
	public int getX( )
	{ 
		return x; 
	}
	public int getY( )
	{
		return y;
	}
}

public class Exercitiu1
{
	public static void main(String[] args)
	{
		Punct p1 = new Punct( );
		Punct p2 = new Punct( );
		
		p1.init (10,20); p2.init (30,40);
		p1.move(5,5); p2.move(6,-2);
		
		System.out.println("(x1,y1) = ("+p1.getX()+","+p1.getY( )+ ")");
		System.out.println("(x2,y2) = ("+p2.getX()+","+ p2.getY( )+ ")");
	}

}
