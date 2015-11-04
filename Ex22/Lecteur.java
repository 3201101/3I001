public class Lecteur implements Runnable {
	private EnsembleDonnees d;
	private static int i = 1;
	private int id;

	public Lecteur(EnsembleDonnees data) {
		d = data;
		id = i;
		i++;
	}

	public void run() {
		int n = 0;
		while(true) {
			try {
				System.out.println("Lecteur " + id + " lit " + d.read(n));
				n++;
				Thread.sleep(500);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}