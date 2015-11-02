import java.util.concurrent.locks.*;
import java.lang.Thread;

class Test {
	public static void main(String[] args) {
		A a = new A();
		B b1 = new B(a);
		B b2 = new B(a);
		B b3 = new B(a);
		b1.run();
		b2.run();
		b3.run();
	}
}

class A {
	private Lock l;
	public A() {
		l = new ReentrantLock();
	}
	public void l() {
		l.lock();
		try { java.lang.Thread.sleep(500);
		}
		catch(Exception e) {}
	}
	public void u() {
		l.unlock();
		try { java.lang.Thread.sleep(500);
		}
		catch(Exception e) {}
	}
}

class B implements Runnable {
	private A a;
	private static int i = 0;
	private int id;
	public B(A a) {
		this.a = a;
		id = i;
		i++;
	}

	public void run() {

		try { java.lang.Thread.sleep(500);
		}
		catch(Exception e) {}
		System.out.println("B" + id + "starts.");
		a.l();

		try { java.lang.Thread.sleep(500);
		}
		catch(Exception e) {}
		System.out.println("B" + id + "locks.");
		a.u();
		
		try { java.lang.Thread.sleep(500);
		}
		catch(Exception e) {}
		System.out.println("B" + id + "stops.");
	}
}