public class Client implements Runnable {
    Coiffeur c;
    boolean done = false;

    public run() {
		Salon s = c.getSalon();
		if(nbChaise <= s.l.getSize())
	    	return
	    else
			s.nouveauClient(c);
		if(s.l.getSize() == 1)
	    	notifyAll;
		while(done)
		    wait;
	}

    public boolean coiffer() {
    	sleep(2000);
		done = true;
		return true;
    }
