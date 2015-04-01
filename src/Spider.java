import java.util.Vector;

public class Spider {

	static fileCrawler filer;
	Spider(){
		filer=new fileCrawler();
	}
	public static Vector<String> find(String fileName) {
		if(filer==null)
			filer=new fileCrawler();
		if (filer.allFiles.containsKey(fileName)){
			return filer.allFiles.get(fileName);
		}
		return null;
	}
	

}
