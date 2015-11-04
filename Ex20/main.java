public class main {
	public static void main(String[] args) {
		SegTournant segT = new SegTournant();
		Thread segTourn = new Thread(segT);
		SegAccueil segA = new SegAccueil();
		PoolHangars pool = new PoolHangars(3);
		Thread l1 = new Thread(new Loco(segT, segA, pool));
		Thread l2 = new Thread(new Loco(segT, segA, pool));
		Thread l3 = new Thread(new Loco(segT, segA, pool));
		Thread l4 = new Thread(new Loco(segT, segA, pool));
		segTourn.start();
		l1.start();
		l2.start();
		l3.start();
		l4.start();
	}
}