public class AleaStock{
    public final int taille;
    private AleaObject[] stock;
    private int nbElem;
    private Lock[] stockLock;
    private Lock nbElemLock;

    public AleaStock(int t) {
		taille = t;
		stock = new AleaObject[t];
    }

    public boolean addElem(AleaObject o) {
    	
    	nbElemLock.lock();

		if(nbElem >= taille)
		{
			nbElemLock.unlock();
		    return false;
		}

		n = nbElem;
		nbElem++;
		nbElemLock.unlock();

		stockLock[n].lock();
		stock[n] = o;
		stockLock[n].unlock();

		return true;
    }

    public boolean isEmpty() {
	    nbElemLock.lock();
		n = nbElem;
		nbElemLock.unlock();

		return (n > 0);
    }
	
	public AleaObject takeElem(int n) {

    	nbElemLock.lock();

		if(nbElem <= 0)
		{
			nbElemLock.unlock();
		    return NULL;
		}

		nbElem--;
		n = nbElem;
		nbElemLock.unlock();

		stockLock[n].lock();
		AleaObject o = stock[n];
		stock[n] = NULL;
		stockLock[n].unlock();
		
		return o;
	}
}
