import java.io.File;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class fileCrawler {
	static HashMap<String, Vector<String>> allFiles;
	static private Vector<String>vector;
	static Lock subLock=new ReentrantLock(),mainlock=new ReentrantLock();
	
	fileCrawler(){
		// TODO Auto-generated method stub
		File file=new File("E:\\lab3"); 
		allFiles=new HashMap<String, Vector<String>>();
		threader thread1=new threader();
		thread1.file=file;
		thread1.start();
	}
	
	static void search(File file){
		if (file.isDirectory()) {
			//do you have permission to read this directory?
			if (file.canRead()) {
				for (final File temp : file.listFiles()) {
					if (temp.isDirectory()) {
						if(threader.count<threader.max){
							threader thread1=new threader();
							thread1.file=new File(temp.getAbsolutePath());
							thread1.start();
						}
						else{
							search(new File(temp.getAbsolutePath()));
						}
		    		}
		    		else{
		    			subLock.lock();
		    			if(allFiles.containsKey(temp.getName())){
		    				allFiles.get(temp.getName()).add(temp.getAbsolutePath());
		    			}
		    			else{
		    				vector =new Vector<String>();
		    				vector.add(temp.getAbsolutePath());
		    				allFiles.put(temp.getName(),vector );
		    			}
		    			subLock.unlock();
		    		}
		    	}
		    } 
		    else {
		    	System.out.println(file.getAbsoluteFile() + "Permission Denied");
		    }
		}
	}
}
