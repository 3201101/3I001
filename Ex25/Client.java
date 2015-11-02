public class Client implements Runnable {
	private Serveur s;
	private static int i = 0;
	private int id;

	public Client(Serveur srv) {
		s = srv;
		id = i;
		i++;
	}

	public void run() {
		while (true) {
			request();
			waitServer();
			work();
		}
	}

	public void requeteServie(ReponseRequete r) {

	}

	private void request() {
		int n = (id%3) ? 1 : 2;
		Requete r = new Requete(this, n);
		s.soumettre(r);
	}

	private void waitServer() {

	}

	private void work() {
		sleep(Random);
	}
}