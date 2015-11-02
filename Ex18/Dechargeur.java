public class Dechargeur implements Runnable {
	private AleaObjet objet;
	private AleaStock stock;
	private Chariot chariot;
	private Lock chariot;

	public Dechargeur(AleaStock s, Chariot c) {
		objet = NULL;
		stock = s;
		chariot = c;
	}

	/**
	 * Prend un objet dans le chariot
	 * @return true si la saisie s'est bien passée, false sinon
	 */
	public boolean pick() {
		if(objet != NULL)
			return false;

		objet = chariot.remove();

		return true;
	}

	/**
	 * Place l'objet dans le stock
	 * @return le code d'erreur de chariot.add()
	 */
	public boolean put() {
		AleaObject o = objet;
		if(stock.addElem(o))
		{
			objet = NULL;

			return true;
		}

		return false;
	}

	public void run()
	{
		while(true)
		{
			await(chariot.empty);
			while(pick())
			{
				if(!put())
				{
					System.out.println("Le stock est plein !")
					break;
				}
			}
		}
	}
}
