public class Salon {
    private Client[] tabClient;
    public final int taille;
    private Lock[] lockTabClient;
    public final Coiffeur[] tabCoif;
    private int nbCoif;
    private int nbClient;
    private Lock coifLock;
    private Lock clientLock;

    public Salon(int t, Coiffeur[] tab) {
		taille = t;
		tabClient = new Client[t];
		tabCoif = tab;
		nbCoif = 0;
		nbClient = 0;
    }
    
    public boolean addClient(Client c) {
		int n = nbClient % taille;		// Emplacement du dernier client dans la salle
		int i = n;						// Place actuellement sélectionnée

		while(!lockTabClient[x].trylock())
		    i = (i + 1) % taille;		// On teste la lecture de la disponibilité des places

		if(tabClient[n] == NULL) {		// On trouve une place libre
		    tabClient[n] = c;			// On installe le client
		    nbClient++;					// On incrémente le compteur de clients
		    lockTabClient[i].unlock();

		    return true;
		}

		lockTabClient[i].unlock();

		return false;					// Ce cas signifie qu'aucune place n'est libre dans le salon actuellement
    }

    /*public void removeClient(int i) {
    	int n = i % taille;				// Emplacement du client dans la salle
		lockTabClient[n].lock();
		tabClient[n] = NULL;
		lockTabClient[n].unlock();
    }*/

    public Client getNextClient() {
    	int n = nbClient % taille;		// Emplacement du client dans la salle
		lockTabClient[n].lock();
		Client c = tabClient[n];

		if(c == NULL) {
			lockTabClient[n].unlock();
			return NULL;				//
		}

		tabClient[nbCoif % taille] = NULL;
		nbCoif++;						// Le client est retiré de la salle d'attente et envoyé au coiffeur
	
		return c;
    }
}
