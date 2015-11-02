public class Effaceur implements Runnable {
	private EnsembleDonnees d;

	public Effaceur(EnsembleDonnees data) {
		d = data;
	}

	public void run() {
		while(true) {
			try {
				d.delete(0);
			}
			catch {
				
			}
		}
	}
}