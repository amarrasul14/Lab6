import java.io.File;
import java.util.HashMap;
import java.util.Timer;
import java.util.Vector;

class threader extends Thread {
	static int count=0,max=4;
	private Thread t;
	HashMap<String,Vector<String>> files=fileCrawler.allFiles;
	File file; 
	
	threader(){
		count++;
	}
	
	public void run() {
		fileCrawler.mainlock.lock();
		fileCrawler.search(file);
		fileCrawler.mainlock.unlock();
		new Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	fileCrawler.mainlock.lock();
		            	Spider.filer=new fileCrawler();
		            	fileCrawler.mainlock.unlock();
		            }
		        }, 
		        5000 
		);
		count--;
	}
	
	public void start (){
		if (t == null){
			t = new Thread (this);
			t.start ();
		}
	}

	
}