public class PoolHangars {
	private int nHangars;
	private Hangar[] hangars;

	public PoolHangars(int t) {
		nHangars = t;
		hangars = new Hangar[t];
		for(int i = 0; i < nHangars; i++)
			hangars[i] = new Hangar(i);
	}

	public int getEmptyHangarPos() {
		for(int i = 0; i < nHangars; i++) {
			if(hangars[i].isEmpty())
				return hangars[i].getPos();
		}
		return -1;
	}

	public Hangar getHangar(int n) {
	/*
		if(n < 0 || n >= nHangars)
			throw Exception
	*/
		return hangars[n];
	}
}