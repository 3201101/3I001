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
		while(true) {
			try {
				Thread.sleep(500);
				d.read(id);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}