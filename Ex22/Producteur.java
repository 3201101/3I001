public class Producteur implements Runnable {
	private EnsembleDonnees d;
	private static int i = 1;
	private int id;

	public Producteur(EnsembleDonnees data) {
		d = data;
		id = i;
		i++;
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
				int n = (int)(Math.random()*50);
				d.add(id, n);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}