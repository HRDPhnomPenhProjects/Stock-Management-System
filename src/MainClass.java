 
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

 

public class MainClass {

	
	public static final String DISPLAY = "*";
	public static final String WRITE = "W";
	public static final String READ = "R";
	public static final String UPDATE = "U";
	public static final String DELETE = "D";
	public static final String FIRST = "F";
	public static final String PREVIOUS = "P";
	public static final String NEXT = "N";
	public static final String LAST = "L";
	public static final String SEARCH = "S";
	public static final String GO_TO = "G";
	public static final String SET_ROW = "SE";
	public static final String BACK_UP = "B";
	public static final String RESTORE = "RE";
	public static final String HELP = "H";
	public static final String EXIT = "E";
	
	List<Data> result;
	
	
	
	 public static Scanner input;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		double old = new Date().getTime();
	
	  	MainClass mainClass = new MainClass();
	  	input = new Scanner(System.in);
	  	
		OperationClass.totalRecord = 100001;
	 	OperationClass.numberOfFile= 0 ;
	 	 
	 	
	  	if(OperationClass.totalRecord%OperationClass.MAXIMUM_RECORD_PER_FILE!=0){
	 		OperationClass.numberOfFile = (int) OperationClass.totalRecord / OperationClass.MAXIMUM_RECORD_PER_FILE;
	 		OperationClass.numberOfFile++;
	 	}else{	
	 		OperationClass.numberOfFile = (int) OperationClass.totalRecord / OperationClass.MAXIMUM_RECORD_PER_FILE;	 		
	 	}
	  	
	  	
	 	mainClass.initializeData(); 
	 	mainClass.readData();
	 
	  	 
	   
	  	
	  	
	 		System.out.println();
			System.out.println("Speed " +(new Date().getTime() - old)/1000 +" seconds");
			String choice = "";
			
			
			System.out.print("Option: ");
			
			 choice =  input.nextLine();
			 
		 	 
			switch (choice.toUpperCase()) {
			case DISPLAY:
					
				break;

			case WRITE:
				mainClass.addData(); 
				break;
			case READ:
				System.out.print("Product ID: ");
				mainClass.readData(input.nextLine());
				break;

			case UPDATE:
				
				
				break;

			case DELETE:
				
				break;

			case FIRST:
				
				break;

			case PREVIOUS:
				
				break;

			case NEXT:
				
				break;

			case LAST:
				
				break;

			case SEARCH:
				
				break;
			
			case GO_TO:
				
				break;

			case SET_ROW:
				
				break;
			case BACK_UP:
				
				break;
			case RESTORE:
				
				break;
			case HELP:
				
				break;
			case EXIT:
				System.exit(0);
				break;

				
			default:
				break;
			}
			
			
	}
	
/*	void copyDatas() throws IOException, InterruptedException, ClassNotFoundException{
	  	new OperationClass(FileNameClass.STOCK_RECORD_PATH).createFiles() ;
	 	 	
	   	File f = new File("datas/stockRecord");
	  	System.out.println(f.listFiles().length);
	 	
	 	 
	 	for(int i =1 ;i<f.listFiles().length;i++){
	 		List<Data> lst =  new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + i).readAllData();
	 		 
	 	}  
  
	}*/
	
	void initializeData() throws IOException{
	  	new OperationClass(FileNameClass.STOCK_RECORD_PATH).createFiles() ;
	 	 	
	   	File f = new File(FileNameClass.STOCK_RECORD_DIRECTORY_PATH);
	  	System.out.println(f.listFiles().length);
	 	
	  	
	 	if(OperationClass.totalRecord%OperationClass.MAXIMUM_RECORD_PER_FILE!=0){
	 		for(int i =1 ;i<f.listFiles().length;i++){
		 		 new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + i).startWrite();
		 		 
		 	}
	 		 new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + f.listFiles().length).writeAllData(OperationClass.totalRecord%OperationClass.MAXIMUM_RECORD_PER_FILE);
			 	
	 	}else{	
	 		 
		 	for(int i =1 ;i<=f.listFiles().length;i++){
		 		 new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + i).startWrite();
		 		 
		 	}  
	 		
	 	}
	 	
	 	
	  
  
	}
	
	
	void readData(String id) throws ClassNotFoundException, IOException{
		
		int recordId = Integer.parseInt(id); 
		int readFile = 1;
		
		
		if(recordId % OperationClass.MAXIMUM_RECORD_PER_FILE !=0){
			readFile = recordId / OperationClass.MAXIMUM_RECORD_PER_FILE;
			readFile++;
		}else{
			readFile = recordId / OperationClass.MAXIMUM_RECORD_PER_FILE;	
		}
		
 
		 result = null;
	 	 result =  new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + readFile).readAllData();
	 	
	  
	 	 
	  	  for(int i=0;i<result.size();i++){
	  		 
	  	 	  if(result.get(i).getId().equals(id)){
	 			 System.out.println();
	 		 	 System.out.println("Product ID    : " + result.get(i).getId());
	 			 System.out.println("Product Name  : " + result.get(i).getName());
	 			 System.out.println("Unit Price    : " + result.get(i).getUnitPrice());
	 			 System.out.println("Quantity      : " + result.get(i).getStockQuantity());
	 			 System.out.println("Imported Date : " + result.get(i).getImportedDate());	
	 			 
	 		 }
	 	 } 
	  	   
	   
	  	//  System.out.println("Product ID Not Founded");
	  	  
	}
	
	void readData() throws ClassNotFoundException, IOException{
		 result =  new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_1").readAllData();	
		 
	  	 System.out.println("Size of data: "+result.size()); 
	  	
		 System.out.println("		 _________________________________________________________________");	
		 System.out.print("		");
		 
		 System.out.print("||	ID	|");
		 System.out.print("|	Title	|");
		 System.out.print("|	Author	|");
		 System.out.print("|	Date	 ||");
		 System.out.println();
		 System.out.println("		||______________||______________||______________||_______________||");
			
		 
		
	  	 
	 	for(int i = 0 ; i<100 ; i++){
	 		System.out.println("\t\t|| "+result.get(i).getId() + "\t\t|| " + result.get(i).getName() + "\t\t|| " + result.get(i).getUnitPrice() + "\t\t|| " + result.get(i).getImportedDate() +"\t ||" ); 
	 		 System.out.println("		||______________||______________||______________||_______________||");
	 			 	
		}
		 System.out.println("\t\t\t Page: " + OperationClass.currentPage +"/"+ OperationClass.totalPage +"\t\t\tTotal Record: "+ OperationClass.totalRecord +"\t");
		 System.out.println("		||_______________________________________________________________||");
	 	
		 
	 	
	 	 System.out.println(" _____________________________________________________________________________________");
	 	 System.out.println("| *)Display | W)rite | R)ead | U)pdate | D)elete | F)irst | P)revious | N)ext | L)ast |");
		 System.out.println("|           S)earch | G)o to | Se)t row | B)ack up | Re)store | H)elp | E)xit         |");
		 System.out.println("|_____________________________________________________________________________________|");

	}
	
	
	void addData() throws ClassNotFoundException, IOException{
		
	 	File f = new File(FileNameClass.STOCK_RECORD_DIRECTORY_PATH);
  
	 	List<Data> result =(List<Data>)new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" +f.listFiles().length).readAllData();
	 	if(result.size() >= OperationClass.MAXIMUM_RECORD_PER_FILE){
	 		
	 	}else{
	 		String id = Long.parseLong(result.get(result.size()-1).getId())+1 + "" ;
	 		String name = input.nextLine();
	 		float unitPrice = Float.parseFloat(input.nextLine());
	 		int quantity = Integer.parseInt(input.nextLine());
	 		 
	 		
	 		result.add(new Data(id, "Name", (float)2.5, 100, Today.today));
	 		
	 	}	
	 	 
	 	
	}
	
	
	
}
