import java.util.concurrent.locks.*;

public class Client implements Runnable {
	public static final int NBREQUETES = 5;
	private Serveur s;
	private static int i = 0;
	private int id;
	private ReponseRequete r;
	public final Lock l = new ReentrantLock();
	public final Condition c = l.newCondition();

	public Client(Serveur srv) {
		s = srv;
		id = i;
		i++;
		r = null;
	}

	public void run() {
		System.out.println("Démarrage Client " + id);
		int n = 0;
		try {
			while (n < NBREQUETES) {
				request();
				waitServer();
				work();
				n++;
			}
		}
		catch (InterruptedException e) {
			System.out.println("Client " + id + " interrompu");
		}
		System.out.println("Fin Client " + id);
	}

	public void requeteServie(ReponseRequete rep) {
		c.notifyAll();
		l.lock();
		r = rep;
		int n = rep.getId();
		System.out.println(" < Réponse " + n + "reçue");
		l.unlock();
	}

	private void request() throws InterruptedException {
		int n = (id%3 != 0) ? 1 : 2;
		Requete r = new Requete(this, n);
		s.soumettre(r);
	}

	private void waitServer() throws InterruptedException {
		l.lock();
		while(r == null)
			c.await();
		l.unlock();
	}

	private void work() throws InterruptedException {
		Thread.sleep((int)(Math.random()*1000));
		r = null;
	}
}