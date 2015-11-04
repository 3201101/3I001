import java.util.concurrent.locks.*;

public class Serveur implements Runnable {
	private Requete r;
	public final Lock l = new ReentrantLock();
	public final Condition cs = l.newCondition();
	public final Condition cc = l.newCondition();

	public Serveur() {
		r = null;
	}
	public void run() {
		try	{
			while (true) {
				attendreRequete();
				traiterRequete();
			}
		}
		catch (InterruptedException e) {
			System.out.println("Serveur interrompu");
		}
	}

	public void soumettre(Requete req) throws InterruptedException {
		cs.notifyAll();
		l.lock();
		while(r != null)
			cc.await();
		r = req;
		int n = r.getId();
		System.out.println(" > Requête " + n + "reçue");
		l.unlock();
	}

	private void attendreRequete() throws InterruptedException {
		l.lock();
		while(r == null)
			cs.await();
		l.unlock();
	}

	private void traiterRequete() {
		r.getClient().requeteServie(new ReponseRequete(r.getClient(), r.getId(), (int)(Math.random()*1000)));
		r = null;
		cc.notifyAll();
	}
}