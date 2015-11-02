public class Producteur implements Runnable {
	private EnsembleDonnees d;

	public Producteur(EnsembleDonnees data) {
		d = data;
	}

	public void run() {
		while(true) {
			d.add(Math.random(0,255));
		}
	}
}