import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class EnsembleDonnees {
	private ArrayList<Integer> data;
	public final ReentrantReadWriteLock l = new ReentrantReadWriteLock(true);

	public EnsembleDonnees() {
		data = new ArrayList();
	}

	/**
	 * Ajoute une valeur aux données
	 * @param id identifiant du producteur
	 * @param n valeur à ajourer
	 */
	public void add(int id, int n) {
		l.writeLock().lock();
		try {
			System.out.println("Producteur " + id + " ajoute " + n);
			int s = data.size();
			data.add(n);
			System.out.println("    Ajout [" + s + "] = " + n);
		}
		finally {
			l.writeLock().unlock();
		}
	}

	/**
	 * Parcoure et affiche les données
	 * @param  id identifiant du lecteur
	 */
	public void read(int id) throws InexistantException {
		l.readLock().lock();
		try {
			System.out.println("Lecteur " + id + " lit le tableau");
			for(int i = 0; i < data.size(); i++) {
				System.out.println("    Case [" + i + "] = " + data.get(i));
			}
		}
		finally {
			l.readLock().unlock();
		}
	}

	/**
	 * Supprime une valeur des données
	 * @param id identifiant de l'effaceur
	 * @param n Valeur à supprimer
	 */
	public void delete(int id, int n) throws InexistantException {
		l.writeLock().lock();
		try {
			System.out.println("Effaceur " + id + " efface " + n);
			if(!data.remove(new Integer(n))) {
				throw new InexistantException(n);
			}
		}
		finally {
			l.writeLock().unlock();
		}
	}
}

class InexistantException extends Exception {
	public InexistantException (int n){
		super("Valeur " + n + " non touvé");
	}
}