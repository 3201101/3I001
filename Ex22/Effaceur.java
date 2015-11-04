public class Effaceur implements Runnable {
	private EnsembleDonnees d;
	private static int i = 1;
	private int id;

	public Effaceur(EnsembleDonnees data) {
		d = data;
		id = i;
		i++;
	}

	public void run() {
		while(true) {
			try {
				int n = (int)(Math.random()*1000);
				System.out.println("Effaceur " + id + " cherche " + d.read(n));
				d.delete(n);
				Thread.sleep(500);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}