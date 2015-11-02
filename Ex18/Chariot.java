public class Chariot implements Runnable
{
	private int maxWeight;
	private int weight;
	private Lock weightLock;
	private int maxItems;
	private int n;
	private Lock nLock;
	private ArrayList items;
	public final Condition full;
	public final Condition empty;

	public Chariot(int w = 100, int n = 10)
	{
		maxWeight = w;
		maxItems = n;
		weight = 0;
		n = 0;
		items = new ArrayList();
		weightLock = new ReetrantLock();
		nLock = new ReetrantLock();
		full = nLock.newCondition();
		empty = nLock.newCondition();
	}

	/**
	 * Ajoute un objet dans le chariot
	 * @param  o Objet à ajouter
	 * @return   true si l'ajout s'est bien passé, false sinon
	 */
	public boolean add(AleaObject o)
	{
		weightLock.lock();
		nLock.lock();

		if(o.weight + weight > maxWeight || n >= maxItems)
		{
			weightLock.unlock();
			nLock.unlock();
			return false;
		}

		weight+= o.weight;
		n++;

		weightLock.unlock();
		nLock.unlock();

		items.add(o);

		return true;
	}

	public AleaObject remove()
	{
		nLock.lock();

		if(n <= 0)
		{
			nLock.unlock();
			return NULL;
		}

		weightLock.lock();

		n--;
		AleaObject o = items.get(n);
		weight-= o.weight;
		weightLock.unlock();
		items.remove(n);
		nLock.unlock();

		return o;
	}
}
