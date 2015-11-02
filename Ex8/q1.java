import graphique.*;
import java.awt.*;
import java.lang.*;

public class q1
{
    static class TraceThread extends Thread // Classe interne statique héritant de Thread
    {
	Fenetre f;
	Point p1;
	Point p2;
	
	public TraceThread(Fenetre f, Point p1, Point p2)
	{
	    this.f = f;
	    this.p1 = p1;
	    this.p2 = p2;
	}
	
	public void run()
	{
	    f.tracerLignePointAPoint(p1, p2, "white");
	}
    }

    static class TraceLigne implements Runnable // Classe interne statique implémentant Runnable
    {
	Fenetre f;
	Point p1;
	Point p2;
	
	public TraceLigne(Fenetre f, Point p1, Point p2)
	{
	    this.f = f;
	    this.p1 = p1;
	    this.p2 = p2;
	}
	
	public void run()
	{
	    f.tracerLignePointAPoint(p1, p2, "red");
	}
    }

    public static void main(String[] args)
    {
	Fenetre f = new Fenetre(1280, 720, "Q1");
	Point p1 = new Point(100, 100);
	Point p2 = new Point(1180, 620);
	Point p3 = new Point(1180, 100);
	Point p4 = new Point(640, 360);
	
	f.remplir("black");
	
	Thread t1 = new Thread() { public void run() { f.tracerLignePointAPoint(p1, p2, "yellow"); } }; // Classe anonyme par redéfinition de Thread.run()
	Thread t2 = new Thread(new Runnable() { public void run() { f.tracerLignePointAPoint(p1, p3, "green"); } }); // Classe anonyme par passage d'un Runnable redéfini
	Thread t3 = new TraceThread(f, p2, p3); // Classe (interne) héritée
	Thread t4 = new Thread(new TraceLigne(f, p4, p3));
	t1.start();
	t2.start();
	t3.start();
	t4.start();
	
    }
   
}
