public class EnsembleDonnees {
	private ArrayList<int> data;
	public final ReentrantReadWriteLock l = new ReentrantReadWriteLock(true);

	public EnsembleDonnees() {
		data = new ArrayList<int>();
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
	 */
	public void read() {
		l.readLock().lock();
		try {
			data.add(n);
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
			if(data.remove(n))
				// affichage
		}
		catch(InexistantException e) {
			
		}
		finally {
			l.writeLock().unlock();
		}
	}
}