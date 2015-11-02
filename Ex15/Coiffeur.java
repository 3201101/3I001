public class Coiffeur implements Runnable {
    public final Salon s;
    private Client c = NULL;

    public Coiffeur(Salon salon){
		s = salon;
    }

    public run() {
		while(true) {
		    c = s.getNextClient();
		    c.coiffer();
		}
    }
}

