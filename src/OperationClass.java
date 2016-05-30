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
	
	
	public static final int MAXIMUM_RECORD_PER_FILE = 20000;
	
	public static int currentPage = 1;
	public static int totalPage = 0;
	public static long totalRecord = 10000;
	public static int numberOfRowPerPage = 5;
	public static int numberOfFile = 0;
	
	
	
	
	private String filePath; 
	private int taskMode; 
	private List<Data> datas;
	
	
	OperationClass(String filePath){
		this.filePath = filePath; 
	 
	}
	
	private OperationClass(){}
	
	public static OperationClass getInstance(){
		return new OperationClass();
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
		
		updateRecordHistory(totalRecord,totalRecord);
		
	}
	 
	 
	 void writeAllData(List<Data> lst) throws IOException{
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath)); 
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
			
			objectOutputStream.writeObject(lst);
			objectOutputStream.close();			
	 }
	 
	 
	 
	 void writeAllData(long numberOfRecords) throws IOException{
			List<Data> lst = new ArrayList<>();
			File file = new File(filePath);
			file.createNewFile();
			
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file)); 	 
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
			
			for(int i=1;i<=numberOfRecords;i++){
				lst.add(new Data((current_id++) +"","Name", (float)2.5 ,100,Today.today));
			}
			
			objectOutputStream.writeObject(lst);
			objectOutputStream.close();
			
		}
	 
	 
	 public void createFiles() throws IOException{
			for(long i=1;i<=OperationClass.numberOfFile;i++){
		 		 new File(FileNameClass.STOCK_RECORD_PATH +"_"+i).createNewFile(); 
		 	}
		
	 }
	 
	 
	 
	 void addData(List<Data> result ) throws IOException, ClassNotFoundException{
		 
			File file = new File(filePath);
			file.createNewFile();
			
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file)); 	 
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
			
		 
			
			objectOutputStream.writeObject(result);
			objectOutputStream.close();
			
			updateRecordHistory(getRecordHistory().getTotalRecord()+1,getRecordHistory().getCurrentRecord()+1);
			
		}
	 
	
	 List<Data> readAllData() throws IOException, ClassNotFoundException{		
		BufferedInputStream bufferedOutputStream = new BufferedInputStream(new FileInputStream(filePath));
		ObjectInputStream objectInputStream = new ObjectInputStream(bufferedOutputStream);
		datas = (List<Data>) objectInputStream.readObject();
		objectInputStream.close();
	 
		return datas;
	}
	 
	 List<Data> readAllData(String filePath) throws IOException, ClassNotFoundException{		
			BufferedInputStream bufferedOutputStream = new BufferedInputStream(new FileInputStream(filePath));
			ObjectInputStream objectInputStream = new ObjectInputStream(bufferedOutputStream);
			datas = (List<Data>) objectInputStream.readObject();
			objectInputStream.close();
		 
			return datas;
		}

	   List<Data> readData(String id) throws ClassNotFoundException, IOException{
			
		    long recordId = InputConverter.toLong(id); 
			long readFile = 1;
			List<Data> result;
			
			if(recordId % OperationClass.MAXIMUM_RECORD_PER_FILE !=0){
				readFile = recordId / OperationClass.MAXIMUM_RECORD_PER_FILE;
				readFile++;
			}else{
				readFile = recordId / OperationClass.MAXIMUM_RECORD_PER_FILE;	
			}
			
	 
			 result = null;
			 filePath = FileNameClass.STOCK_RECORD_PATH + "_" + readFile;
		 	 result = readAllData();
		 	 
		  	   
		   	return result;
		
	   }
	   
	   
	   List<Data> searchByName(String name) throws ClassNotFoundException, IOException{
		   List<Data> result = new Vector<>();
		   File file = new File(FileNameClass.STOCK_RECORD_DIRECTORY_PATH);
		   
		   for(int i=1;i<file.listFiles().length+1;i++){
			   List<Data> tmp = readAllData(FileNameClass.STOCK_RECORD_PATH +"_"+i);
			     for(int j=0;j<tmp.size();j++){
			      if(tmp.get(j).getName().toLowerCase().contains(name.toLowerCase())){
			     	 	result.add(tmp.get(j));
			    	 }
			     }
		   }
		   
	 	   return result;
	   }
	   
	  /* Data readData(int id) throws ClassNotFoundException, IOException{
			
			int recordId = Integer.parseInt(id); 
				int readFile = 1;
				List<Data> result;
				
				if(recordId % OperationClass.MAXIMUM_RECORD_PER_FILE !=0){
					readFile = recordId / OperationClass.MAXIMUM_RECORD_PER_FILE;
					readFile++;
				}else{
					readFile = recordId / OperationClass.MAXIMUM_RECORD_PER_FILE;	
				}
				
		 
				 result = null;
			 	 result =  new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + readFile).readAllData();
			 	 
			  	  for(int i=0;i<result.size();i++){
			  		 System.out.println(result.get(i).getId());
			 		 	
			  	 	  if(result.get(i).getId().equals(id)){
			  			 return result.get(i);
			 		 }
			 	 } 
			  	   
			   	return null;
			  	  
			}*/
	   
	   
	   Data searchData(String id) throws ClassNotFoundException, IOException{
		   List<Data> result = readData(id);
		   
		   for(int i=0; i<result.size() ;i++){
			   if(result.get(i).getId().equals(id)){
				   return result.get(i);
			   }
		   }
		   return null;
	   }
	   
	 
 
	 		
	 		
	 		boolean updateName(String id,String name) throws ClassNotFoundException, IOException{
	 			
	 			List<Data> lst = readData(id);
	 			
	 			for(int i=0;i<lst.size();i++){
	 				if(lst.get(i).getId().equals(id)){
	 					lst.get(i).setName(name);
	 					writeAllData(lst);
	 					return true;
	 				}
	 				
	 				
	 			}
	 			
	 			return false;
	 		}
	 		
	 
	 		boolean updatePrice(String id,String price) throws ClassNotFoundException, IOException{
	 			
	 			List<Data> lst = readData(id);
	 			
	 			for(int i=0;i<lst.size();i++){
	 				if(lst.get(i).getId().equals(id)){
	 					lst.get(i).setUnitPrice(Float.parseFloat(price));
	 					writeAllData(lst);
	 					return true;
	 				}
	 				
	 				
	 			}
	 			
	 			return false;
	 		}
	 	
	 		boolean updateQuantity(String id,String quantity) throws ClassNotFoundException, IOException{
	 			
	 			List<Data> lst = readData(id);
	 			
	 			for(int i=0;i<lst.size();i++){
	 				if(lst.get(i).getId().equals(id)){
	 					lst.get(i).setStockQuantity(Integer.parseInt(quantity)); 
	 					writeAllData(lst);
	 					return true;
	 				}
	 				
	 			}
	 			
	 			return false;
	 		}
	 		
	 		
	 
	 		boolean delete(String id) throws ClassNotFoundException, IOException{
	 			totalRecord--;
	 			updateRecordHistory(getRecordHistory().getTotalRecord(), getRecordHistory().getCurrentRecord()-1);
	 			List<Data> lst = readData(id);
	 			for(int i=0;i<lst.size();i++){
	 				
	 				if(lst.get(i).getId().equals(id)){
	 					lst.remove(i);
	 					writeAllData(lst);
	 					return true;
	 				}
	 				
	 				
	 			}
		 		
	 			return false; 
	 		
	 		}
	 		
	 		void updateRecordHistory(long totalRecord,long currentRecord) throws IOException{
	 			File file = new File(FileNameClass.RECORD_HISTORY_PATH);
	 			file.createNewFile();
	 			 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
	 			 ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
	 			 objectOutputStream.writeObject(new RecordHistory(totalRecord,currentRecord));
	 			 objectOutputStream.close();
	 		}
	 		
	 		RecordHistory getRecordHistory() throws IOException, ClassNotFoundException{
	 		 	 BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(FileNameClass.RECORD_HISTORY_PATH));
	 			 ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
	 			 RecordHistory recordHistory = (RecordHistory) objectInputStream.readObject();
	 			 objectInputStream.close();
	 			 return recordHistory; 
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
