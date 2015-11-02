public class Groupe implements Runnable{
    public final int id;
    public final int nb;
    public final  Salle salle;
    private static int cpt = 0;

    public Groupe(int n, Salle s){
	id = cpt;
	nb = n;
	salle = s;
	cpt++;
    }

    public void run() {
	if(salle.reserver(id, nb))
	    System.out.println("Salle résevé pour le grp " + id);
    }
}
