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
	 * @param n valeur à ajourer
	 */
	public void add(int n) {
		l.writeLock().lock();
		try {
			data.add(n);
		}
		finally {
			l.writeLock().unlock();
		}
	}

	/**
	 * Parcoure et affiche les données
	 * @param  n identifiant de la valeur à lire
	 * @return valeur lue
	 */
	public int read(int n) throws InexistantException {
		l.readLock().lock();
		try {
			int s = data.size();
			if(s != 0)
				return data.get(n%s);
			else
				throw new InexistantException(n);
		}
		finally {
			l.readLock().unlock();
		}
	}

	/**
	 * Supprime une valeur des données
	 * @param n Valeur à supprimer
	 */
	public void delete(int n) throws InexistantException {
		l.writeLock().lock();
		try {
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