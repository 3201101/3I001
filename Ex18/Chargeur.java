public class Chargeur implements Runnable {
	private AleaObjet objet;
	private AleaStock stock;
	private Chariot chariot;
	private Lock chariot;

	public Chargeur(AleaStock s, Chariot c) {
		objet = NULL;
		stock = s;
		chariot = c;
	}

	/**
	 * Prend un objet dans le stock
	 * @return true si la saisie s'est bien passée, false sinon
	 */
	public boolean pick() {
		if(objet != NULL)
			return false;

		objet = stock.takeElem();

		return true;
	}

	/**
	 * Place l'objet dans le chariot
	 * @return le code d'erreur de chariot.add()
	 */
	public boolean put() {
		AleaObject o = objet;
		objet = NULL;

		return chariot.add(o);
	}
}
