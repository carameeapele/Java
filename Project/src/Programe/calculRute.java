package Programe;

import java.util.*;

class Drum
{
    public Oras destinaite;
    public double distanta;
    public boolean pretios, periculos;
    
    public Drum(Oras destinatie, double distanta, boolean pretios, boolean periculos)
    {
    	this.destinaite = destinatie;
    	this.distanta = distanta;
    	this.pretios = pretios;
    	this.periculos = periculos;
    }
    
    public void setDestinatie(Oras destinatie) { this.destinaite = destinatie; }
    public void setDistanta(double distanta) { this.distanta = distanta; }
    public void setPretios(boolean pretios) { this.pretios = pretios; }
    public void setPericulos(boolean periculos) { this.periculos = periculos; }
    
    public Oras getDestinatie() { return this.destinaite; }
    public double getDistanta() { return this.distanta; }
    public boolean getPretios() { return this.pretios; }
    public boolean getPericulos() { return this.periculos; }
    
}

class Oras implements Comparable<Oras>
{
    public String nume;
    public Drum[] drumuri;
    public double distantaMinima = Double.POSITIVE_INFINITY;
    public boolean pretios, periculos;
    
    public Oras previous;
    public Oras(String nume) { this.nume = nume; }
    
    public void setNume(String nume) { this.nume = nume; }
    
    public String getNume() { return this.nume; }
        
    public String toString() { return this.nume; }
    
    public int compareTo(Oras oras) { return Double.compare(distantaMinima, oras.distantaMinima); }
}

class ListaOrase
{
	Set<Oras> orase;
	
	public ListaOrase()
	{
		this.orase = new HashSet<Oras>();
	}
	
	public void setListaOrase(Set<Oras> orase) { this.orase = orase;}
	public Set<Oras> getListaOrase() { return this.orase; }
	public void addOras(Oras oras) { orase.add(oras); }
	
	public Oras cautaOras(String numeOras)
	{
        for(Iterator<Oras> i = orase.iterator(); i.hasNext();)
        {
        	Oras oras = i.next();
        	if(oras.getNume().equals(numeOras))
        		return oras;
        }
        
        return null;
	}
}

class Dijkstra
{
	public static List<Oras> celMaiScurtDrum(Oras destinatie)
	{
	    List<Oras> drum = new ArrayList<Oras>();
	    for (Oras oras = destinatie; oras != null; oras = oras.previous)
	        drum.add(oras);
	
	    Collections.reverse(drum);
	    return drum;
	}

	public static void calculeazaTraseu(Oras pornire, String categorie)
    {
        pornire.distantaMinima = 0.;
        PriorityQueue<Oras> traseu = new PriorityQueue<Oras>();
        traseu.add(pornire);

        while (!traseu.isEmpty())
        {
            Oras oras = traseu.poll();
            
            switch(categorie)
            {
            	case "periculos":
            		for (Drum drum : oras.drumuri)
		            {
		            	if(drum.periculos)
		            	{
		            		Oras o = drum.destinaite;
			                double distanta = drum.distanta;
			                double distanceThroughU = oras.distantaMinima + distanta;
			                
			                if (distanceThroughU < o.distantaMinima)
			                {
			                    traseu.remove(o);
			
			                    o.distantaMinima = distanceThroughU ;
			                    o.previous = oras;
			                    traseu.add(o);
			                } 
		            	}
		                           
		            }
            		break;
            	case "pretios":
            		for (Drum drum : oras.drumuri)
		            {
		            	if(drum.pretios)
		            	{
		            		Oras o = drum.destinaite;
			                double distanta = drum.distanta;
			                double distanceThroughU = oras.distantaMinima + distanta;
			                
			                if (distanceThroughU < o.distantaMinima)
			                {
			                    traseu.remove(o);
			
			                    o.distantaMinima = distanceThroughU ;
			                    o.previous = oras;
			                    traseu.add(o);
			                } 
		            	}
		                           
		            }
            		break;
            	default:
            		for (Drum drum : oras.drumuri)
		            {
	            		Oras o = drum.destinaite;
		                double distanta = drum.distanta;
		                double distanceThroughU = oras.distantaMinima + distanta;
		                
		                if (distanceThroughU < o.distantaMinima)
		                {
		                    traseu.remove(o);
		
		                    o.distantaMinima = distanceThroughU ;
		                    o.previous = oras;
		                    traseu.add(o);
		                }          
		            }
            		break;
            }             
        }
    }
}
 
public class calculRute
{	
	public static ListaOrase initializareOrase()
	{
		Oras Arad = new Oras("Arad");
        Oras Caransebes = new Oras("Caransebes");
        Oras Cluj = new Oras("Cluj Napoca");
        Oras Lugoj = new Oras("Lugoj");
        Oras Marghita = new Oras("Marghita");
        Oras Oradea = new Oras("Oradea");
        Oras Orsova = new Oras("Orsova");
        Oras Resita = new Oras("Resita");
        Oras Salonta = new Oras("Salonta");
        Oras Timisoara = new Oras("Timisoara");
        Oras Zalau = new Oras("Zalau");
        
        Arad.drumuri = new Drum[] {
        		new Drum(Salonta, 79, true, true),
        		new Drum(Timisoara, 54, true, true)
        };
        
        Caransebes.drumuri = new Drum[] {
        		new Drum(Lugoj, 44, true, true),
        		new Drum(Orsova, 92, false, true),
        		new Drum(Resita, 44, true, true)
        };
        
        Cluj.drumuri = new Drum[] {
        	new Drum(Lugoj, 267, true, true),
        	new Drum(Zalau, 84, true, true),
        	new Drum(Oradea, 151, false, true)
        };
        
        Lugoj.drumuri = new Drum[] {
        	new Drum(Caransebes, 44, true, true),
        	new Drum(Cluj, 267, true, true),
        	new Drum(Resita, 56, false, false),
        	new Drum(Timisoara, 67, true, true)
        };
        
        Marghita.drumuri = new Drum[] {
        	new Drum(Oradea, 63, true, true),
        	new Drum(Zalau, 84, false, false)
        };
        
        Oradea.drumuri = new Drum[] {
        	new Drum(Cluj, 151, false, true),
        	new Drum(Marghita, 63, true, true),
        	new Drum(Salonta, 39, true, true),
        	new Drum(Zalau, 115, true, true)
        };
        
        Orsova.drumuri = new Drum[] {
        	new Drum(Caransebes, 92, false, true),
        	new Drum(Resita, 134, true, true)
        };
        
        Resita.drumuri = new Drum[] {
        	new Drum(Caransebes, 44, true, true),
        	new Drum(Lugoj, 56, false, false),
        	new Drum(Orsova, 134, true, true),
        	new Drum(Timisoara, 97, true, false)
        };
        
        Salonta.drumuri = new Drum[] {
        		new Drum(Arad, 79, true, true),
        		new Drum(Oradea, 39, true, true)
        };
        
        Timisoara.drumuri = new Drum[] {
        		new Drum(Arad, 54, true, true),
        		new Drum(Lugoj, 67, true, true),
        		new Drum(Resita, 97, true, false)
        };
        
        Zalau.drumuri = new Drum[] {
        		new Drum(Cluj, 84, true, true),
        		new Drum(Marghita, 84, false, false),
        		new Drum(Oradea, 115, true, true)
        };
        
        ListaOrase listaOrase = new ListaOrase();
        listaOrase.addOras(Arad);
        listaOrase.addOras(Caransebes);
        listaOrase.addOras(Cluj);
        listaOrase.addOras(Lugoj);
        listaOrase.addOras(Marghita);
        listaOrase.addOras(Oradea);
        listaOrase.addOras(Orsova);
        listaOrase.addOras(Resita);
        listaOrase.addOras(Salonta);
        listaOrase.addOras(Timisoara);
        listaOrase.addOras(Zalau);
        
        return listaOrase;
	}
	
	public static void main(String[] args)
	{   
		ListaOrase listaOrase = initializareOrase();
		
        Oras plecare = listaOrase.cautaOras("Timisoara");
        Oras destinatie = listaOrase.cautaOras("Cluj Napoca");
        
        if(plecare != null && destinatie != null)
        {
        	Dijkstra.calculeazaTraseu(plecare, "periculos");
        	if(destinatie.distantaMinima == Double.POSITIVE_INFINITY)
        	{
        		System.out.println("Nu se gaseste drum");
        	}
        	else
        	{
        		System.out.println("Distance from " + plecare + " " + "to " + destinatie + ": " + destinatie.distantaMinima);
	            List<Oras> traseu = Dijkstra.celMaiScurtDrum(destinatie);
	            String t = "";
	            for(int i = 0; i < traseu.size(); i++)
	            {
	            	t = t + traseu.get(i).nume + ",";
	            }
	            
	            System.out.println("Path: " + traseu);
	            System.out.println(t);
        	}
            
        }	  
        else
        {
        	System.out.println("Datele nu au fost introduse corect");
        }
	}

}













