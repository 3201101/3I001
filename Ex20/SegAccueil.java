public class SegAccueil {
	private final Lock l = new Lock();
	private Condition isAvaiable = l.newCondition();

	public void reserver() {
		l.lock();
	}

	public void liberer() {
		l.unlock();
	}
}