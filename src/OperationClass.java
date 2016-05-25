 import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class OperationClass extends Thread {
	
	public static final int CREATE_FILE_MODE = 0;
	public static final int WRITE_MODE = 1;	
	public static final int READ_MODE = 2;
	public static int current_id = 1;
	
	
	public static final int MAXIMUM_RECORD_PER_FILE = 50000;
	
	public static int currentPage = 1;
	public static int totalPage = 100;
	public static int totalRecord = 10000;
	public static int numberOfRecordPerPage = 10;
	public static int numberOfFile;
	
	private String filePath; 
	private int taskMode; 
	private List<Data> datas;
	
	
	OperationClass(String filePath){
		this.filePath = filePath; 
	 
	}
	
	 void writeAllData() throws IOException{
		List<Data> lst = new Vector<Data>();
		 
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath)); 
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
	
		for(int i=1;i<=MAXIMUM_RECORD_PER_FILE;i++){
			lst.add(new Data((current_id++)+"","Name", (float)2.5  ,10,Today.today));		
		}
		
		
		objectOutputStream.writeObject(lst);
		objectOutputStream.close();
		
	}
	 
	 private void writeAllData(String filePath) throws IOException{
			List<Data> lst = new Vector<Data>();
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));	 
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		
			for(int i=1;i<=MAXIMUM_RECORD_PER_FILE;i++){
				lst.add(new Data((current_id++) +"","Name",(float) 2.5 ,100,Today.today));
			}
			
			
			objectOutputStream.writeObject(lst);
			objectOutputStream.close();
		}
	 
	 public void createFiles() throws IOException{
			for(int i=1;i<=OperationClass.numberOfFile;i++){
		 		 new File(FileNameClass.STOCK_RECORD_PATH +"_"+i).createNewFile(); 
		 		//	writeAllData(filePath + "_" + i);
		
		 	}
		
	 }
	 
	 void writeAllData(int numberOfRecords) throws IOException{
			List<Data> lst = new ArrayList<>();
			File file = new File(filePath);
			file.createNewFile();
			
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file)); 	 
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
			
			for(int i=1;i<=numberOfRecords;i++){
				lst.add(new Data((current_id++) +"","ProName", (float)2.5 ,100,Today.today));
			}
			
			objectOutputStream.writeObject(lst);
			objectOutputStream.close();
			
		}
	
	 List<Data> readAllData() throws IOException, ClassNotFoundException{		
		BufferedInputStream bufferedOutputStream = new BufferedInputStream(new FileInputStream(filePath));
		ObjectInputStream objectInputStream = new ObjectInputStream(bufferedOutputStream);
		datas = (List<Data>) objectInputStream.readObject();
		objectInputStream.close();
	 
		return datas;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		switch (taskMode) {
		case WRITE_MODE:
			
			try {
				
				writeAllData();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case READ_MODE:
			try {
				readAllData();
				 
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
					
			break;
		case CREATE_FILE_MODE:
				
			try {
				createFiles();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			break;
		
		default:
			break;
		}
		
	}
	
	void startWrite(){
		taskMode = WRITE_MODE;
		run();
	}
	
	
	List<Data> startRead() throws InterruptedException{
		taskMode = READ_MODE;
		run();
		return datas;
		
	}
	
	void startCreateFiles(){
		taskMode = CREATE_FILE_MODE;
		run();
	}
	
}
