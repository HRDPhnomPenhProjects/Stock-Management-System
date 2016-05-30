 
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

 

public class MainClass  {

	
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
	
	public static final String ALL = "A";
	public static final String NAME = "N";
	public static final String PRICE = "P";
	public static final String QUANITY = "Q";
	
	public static final String YES = "Y";
	public static final String NO = "N";
	
	
	public static final int BACK_ARROW = 37;
	public static final int UP_ARROW = 38;
	public static final int NEXT_ARROW = 39;
	public static final int DOWN_ARROW = 40;
	public static final int ENTER = 10;
	public static final int BACK_SPACE = 8;
	public static final int SHIFT = 16;
	
	
	
	List<Data> result;
	 
	
	
	 public static Scanner input;
 
	
	public static void main(String[] args) {
		double old = new Date().getTime();
	
	  	MainClass mainClass = new MainClass();
	  
	  	// input = new Scanner(System.in);
	  	 
		try {
			OperationClass.totalRecord =  OperationClass.getInstance().getRecordHistory().getCurrentRecord();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("You don't have record yet,Please input manually");
		} catch (Exception e) {
			System.out.println("You don't have record yet,Please input manually");
		}
		
		if(OperationClass.totalRecord%OperationClass.numberOfRowPerPage==0){
			OperationClass.totalPage = (int)OperationClass.totalRecord / OperationClass.numberOfRowPerPage;	
		}else{
			OperationClass.totalPage = (int)OperationClass.totalRecord / OperationClass.numberOfRowPerPage ;
			OperationClass.totalPage++;
		}
		 		
	 	OperationClass.numberOfFile = 0 ;
	 	 
	 	
	  	if(OperationClass.totalRecord%OperationClass.MAXIMUM_RECORD_PER_FILE!=0){
	 		OperationClass.numberOfFile = (int) OperationClass.totalRecord / OperationClass.MAXIMUM_RECORD_PER_FILE;
	 		OperationClass.numberOfFile++;
	 	 
	 	}else{	
	 		OperationClass.numberOfFile = (int) OperationClass.totalRecord / OperationClass.MAXIMUM_RECORD_PER_FILE;	 		
	 	}
	  	
	  	
	  	try {
			System.out.println(OperationClass.getInstance().getRecordHistory().getCurrentRecord());
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("You don't have record yet,Please input manually");
		} catch (Exception e) {
			System.out.println("You don't have record yet,Please input manually");
		}
	  	
	  //	mainClass.initializeData(); 
	 	try {
			mainClass.readData();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("You don't have record yet,Please input manually");
		} catch (Exception e) {
			System.out.println("You don't have record yet,Please input manually");
		}
	   
	  	
	 		System.out.println();
			System.out.println("Speed " +(new Date().getTime() - old)/1000 +" seconds");
			String choice = "";
			 
			
			
			while (true) {
		 
			System.out.print("Option: ");
		 
			 
			 while(true){
				 MyConsoleListener.getCh();
					
				if(MyConsoleListener.keyboardCode == BACK_ARROW){
					choice = "P";
					
					break;
				}else if(MyConsoleListener.keyboardCode == UP_ARROW){
					choice = "L";
					break;
				}else if(MyConsoleListener.keyboardCode == NEXT_ARROW){
					choice = "N";
					break;
				}else if(MyConsoleListener.keyboardCode == DOWN_ARROW){
					choice = "F";
					break;
				}else if(MyConsoleListener.keyboardCode == ENTER){
					choice = MyConsoleListener.option;
					break;
				} 
				
			 
				
			} 
			 
			 
		 
	 
			 
			 MyConsoleListener.option = "";
			 
		 	/*while (input.hasNextLine()) {
		 	System.out.println(input.nextLine());
			}	 */
			  
			  
			 input = new Scanner(System.in);
 			switch (choice.toUpperCase().trim()) {
			case DISPLAY:
				try {
					mainClass.readData();
				}catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
				break;

			case WRITE:
				try {
					mainClass.addData();
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
			 	 
				break;
			case READ:
				 
				System.out.println("---READ---");
				System.out.print("Product ID: ");
				
				try {
					 
					mainClass.readData(input.nextLine());
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
			 	break;

			case UPDATE:
				 
				System.out.println("---UPDATE---");
				System.out.print("Product ID: ");
				try {
					mainClass.update(input.nextLine());
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
				
				break;

			case DELETE:
				 
				System.out.println("---DELETE---");
				System.out.print("Product ID: ");
				try {
					mainClass.delete(input.nextLine());
				}catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
			 	break;
			 	
			case FIRST:
			 
				try {
					mainClass.goToFirstPage();
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
				break;

			case PREVIOUS:
			 
				try {
					mainClass.goToPreviousPage();
				}catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
				break;

			case NEXT:
				 
				try {
					mainClass.goToNextPage();
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
				
				break;

			case LAST:
			 
				try {
					mainClass.goToLastPage();
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
				
				break;

			case SEARCH:
				 
				System.out.println("---SEARCH---");
				System.out.print("Input Name: ");
				try {
					mainClass.searchByName(input.nextLine());
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
				break;
			
			case GO_TO:
				 
				System.out.println("---GO TO---");
				System.out.print("Input Page No#: ");
				try {
					mainClass.goTo(input.nextLine());
				}catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
					
				break;

			case SET_ROW:
			 
				System.out.println("---SET ROWS---");
				System.out.print("Input rows num: ");
				try {
					mainClass.setRow(input.nextLine());
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("You don't have record yet,Please input manually");
				} catch (Exception e) {
					System.out.println("You don't have record yet,Please input manually");
				}
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
	 		 new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + f.listFiles().length).writeAllData(OperationClass.totalRecord % OperationClass.MAXIMUM_RECORD_PER_FILE);
			 	
	 	}else{	
	 		 
		 	for(int i =1 ;i<=f.listFiles().length;i++){
		 		 new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + i).startWrite();
		 		 
		 	}  
	 		
	 	}
	  
	 	
	  
  
	}
	
	
	boolean readData(String id) throws ClassNotFoundException, IOException{
		
		  Data result = OperationClass.getInstance().searchData(id);
	 
	  	 	  if(result != null){
	 			 System.out.println();
	 		 	 System.out.println("Product ID    : " + result.getId());
	 			 System.out.println("Product Name  : " + result.getName());
	 			 System.out.println("Unit Price    : " + result.getUnitPrice());
	 			 System.out.println("Quantity      : " + result.getStockQuantity());
	 			 System.out.println("Imported Date : " + result.getImportedDate());	
	 			 System.out.println("\n\n\n");
	 			 return true;
	 		 }
	 	   	   
	    System.out.println("Product ID Not Founded || Erorr 404 Contact PHNOM PENH GP 3 for more information");
	  	return false;
	  	
	}
	
	void readData() throws ClassNotFoundException, IOException{
		 result =  new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_1").readAllData();	
		 System.out.println("\n\n\n");
			
	  	
		 System.out.println("		 ________________________________________________________________________");	
		 System.out.print("		");
		 
		 System.out.print("||	ID	|");
		 System.out.print("|	Name	|");
		 System.out.print("|	Price	|");
		 System.out.print("|	Quantity	||");
		 System.out.println();
		 System.out.println("		||______________||______________||______________||______________________||");
			
		 
		
	  	 
	 	for(int i = 0 ; i<OperationClass.numberOfRowPerPage ; i++){
	 		 System.out.println("\t\t|| "+result.get(i).getId() + "\t\t|| " + result.get(i).getName() + "\t\t|| " + result.get(i).getUnitPrice() + "\t\t|| " + result.get(i).getStockQuantity() +"\t\t\t||" ); 
	 		 System.out.println("		||______________||______________||______________||______________________||");
	 			 	
		}
		 	 System.out.println("\t\t\t Page: " + OperationClass.currentPage +"/"+ OperationClass.totalPage +"\t\t\tTotal Record: "+ OperationClass.getInstance().getRecordHistory().getCurrentRecord() +"\t");
		 	 System.out.println("		||______________________________________________________________________||");
	 	
	 	 System.out.println(" _____________________________________________________________________________________");
	 	 System.out.println("| *)Display | W)rite | R)ead | U)pdate | D)elete | F)irst | P)revious | N)ext | L)ast |");
		 System.out.println("|           S)earch | G)o to | Se)t row | B)ack up | Re)store | H)elp | E)xit         |");
		 System.out.println("|_____________________________________________________________________________________|");

	}
	
	void readData(List<Data> result) throws ClassNotFoundException, IOException{
		System.out.println("\n\n\n");
	
		 System.out.println("		 ________________________________________________________________________");	
		 System.out.print("		");
		 
		 System.out.print("||	ID	|");
		 System.out.print("|	Name	|");
		 System.out.print("|	Price	|");
		 System.out.print("|	Quantity	||");
		 System.out.println();
		 System.out.println("		||______________||______________||______________||______________________||");
			
		 
		
	  	 
	 	for(int i = 0 ; i<result.size() ; i++){
	 		 System.out.println("\t\t|| "+result.get(i).getId() + "\t\t|| " + result.get(i).getName() + "\t\t|| " + result.get(i).getUnitPrice() + "\t\t|| " + result.get(i).getStockQuantity() +"\t\t\t||" ); 
	 		 System.out.println("		||______________||______________||______________||______________________||");
	 			 	
		}
		 	 System.out.println("\t\t\t Page: " + OperationClass.currentPage +"/"+ OperationClass.totalPage +"\t\t\tTotal Record: "+ OperationClass.totalRecord +"\t");
		 	 System.out.println("		||______________________________________________________________________||");
	 	
		 
	 	
	 	 System.out.println(" _____________________________________________________________________________________");
	 	 System.out.println("| *)Display | W)rite | R)ead | U)pdate | D)elete | F)irst | P)revious | N)ext | L)ast |");
		 System.out.println("|           S)earch | G)o to | Se)t row | B)ack up | Re)store | H)elp | E)xit         |");
		 System.out.println("|_____________________________________________________________________________________|");

	}
	
	void delete(String id) throws ClassNotFoundException, IOException{
		if(readData(id)) {
			System.out.println("Are you sure you want to delete this Product? [Y/N]");
			System.out.print("Option: ");
			String choice = input.nextLine();
			switch (choice.toUpperCase()) {
			case YES:
				
				if(OperationClass.getInstance().delete(id)){
					System.out.println("Delete Succesful...");
				}else{
					System.out.println("ID Not Found...");
				}
				
				break;
				
			case NO:
				System.out.println("Cancel Delete...");
				break;
				

			default:
				break;
			}
		}	
		
	}
	
	void update(String id) throws ClassNotFoundException, IOException{
	 
		if(readData(id)) {
			System.out.println("What do you want to update?");
			System.out.println("(A)ALL (N)Name (P)Price (Q)Quantity");
			System.out.println("Option: ");
			String choice = input.nextLine();
			switch (choice.toUpperCase()) {
			case ALL:
				
				break;

			case NAME:
					System.out.print("Input Name: ");
					if(OperationClass.getInstance().updateName(id,input.nextLine())){
						System.out.println("Update succesfully...");
					}else{
						System.out.println("Error while Updating...");			
					}
				break;
			case PRICE:
				System.out.print("Input Price: ");
				
				if(OperationClass.getInstance().updatePrice(id,input.nextLine())){
					System.out.println("Update succesfully...");
				}else{
					System.out.println("Error while Updating...");			
				}
				break;
			
			case QUANITY:
				System.out.print("Input Quantity: ");
				
				if(OperationClass.getInstance().updateQuantity(id,input.nextLine())){
					System.out.println("Update succesfully...");
				}else{
					System.out.println("Error while Updating...");			
				}
				
				
				break;

				
			default:
				break;
			}
		}
	}
	
	void addData() throws ClassNotFoundException, IOException{
		
	 	File f = new File(FileNameClass.STOCK_RECORD_DIRECTORY_PATH);
  
	 	List<Data> result =(List<Data>)new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" +f.listFiles().length).readAllData();
	 	
	 	if(result.size() == OperationClass.MAXIMUM_RECORD_PER_FILE){
	 		String id = OperationClass.getInstance().getRecordHistory().getCurrentRecord()+1 + "";
	 		System.out.print("Product ID   : " + id);
	 		System.out.println();	
	 		
	 		System.out.print("Product Name : ");
	 		String name = input.nextLine();
	 		 
	 		System.out.print("Unit Price   : ");
	 		float unitPrice = Float.parseFloat(input.nextLine());
	 		 
	 		System.out.print("Quantity     : ");
	 		int quantity = Integer.parseInt(input.nextLine());
	 		System.out.println();	
	 		 
	 		
	 		result.add(new Data(id, name, (float)unitPrice, (int)quantity, Today.today));
			new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + (f.listFiles().length+1)).addData(result);
	 		
	 	}else{
	 		String id = OperationClass.getInstance().getRecordHistory().getCurrentRecord()+1 + "";
		 	System.out.print("Product ID   : " + id);
	 		System.out.println();	
	 		
	 		System.out.print("Product Name : ");
	 		String name = input.nextLine();
	 		 
	 		System.out.print("Unit Price   : ");
	 		float unitPrice = Float.parseFloat(input.nextLine());
	 		 
	 		System.out.print("Quantity     : ");
	 		int quantity = Integer.parseInt(input.nextLine());
	 		System.out.println();	
	 		 
	 		
	 		result.add(new Data(id, name, (float)unitPrice, (int)quantity, Today.today));
			new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + f.listFiles().length).addData(result);
				
	 	}	
	 	 
	 	
	}
	
	
	void searchByName(String name) throws ClassNotFoundException, IOException{
		
		List<Data> result = OperationClass.getInstance().searchByName(name);
		 System.out.println("		 ________________________________________________________________________");	
		 System.out.print("		");
		 
		 System.out.print("||	ID	|");
		 System.out.print("|	Name	|");
		 System.out.print("|	Price	|");
		 System.out.print("|	Quantity	||");
		 System.out.println();
		 System.out.println("		||______________||______________||______________||______________________||");
			
		 
		
	  	 
	 	for(int i = 0 ; i<result.size() ; i++){
	 		 System.out.println("\t\t|| "+result.get(i).getId() + "\t\t|| " + result.get(i).getName() + "\t\t|| " + result.get(i).getUnitPrice() + "\t\t|| " + result.get(i).getStockQuantity() +"\t\t\t||" ); 
	 		 System.out.println("		||______________||______________||______________||______________________||");
	 			 	
		}
		 	 System.out.println("\t\t\t\t\tTotal Record Found: "+ result.size() +"\t");
		 	 System.out.println("		||______________________________________________________________________||");
	 	
		 
	 	
	 	 System.out.println(" _____________________________________________________________________________________");
	 	 System.out.println("| *)Display | W)rite | R)ead | U)pdate | D)elete | F)irst | P)revious | N)ext | L)ast |");
		 System.out.println("|           S)earch | G)o to | Se)t row | B)ack up | Re)store | H)elp | E)xit         |");
		 System.out.println("|_____________________________________________________________________________________|");
	}
	
	
	void setRow(String rows) throws ClassNotFoundException, IOException{
		int row = Integer.parseInt(rows);
		OperationClass.currentPage = 1;
		if(row>10000){
			OperationClass.numberOfRowPerPage = 10000;
			 
		}else{
			OperationClass.numberOfRowPerPage = row;
		}
		
		if( OperationClass.totalRecord % row == 0){
			OperationClass.totalPage = (int) OperationClass.totalRecord / row;		
		}else{
			OperationClass.totalPage = (int) OperationClass.totalRecord / row;				
			OperationClass.totalPage++;
		}		
		
		 readData();
	}
	

	void goTo(String pages) throws ClassNotFoundException, IOException{
		int page = Integer.parseInt(pages);
		OperationClass.currentPage = page;
		long lastId = page * OperationClass.numberOfRowPerPage;
		long startId = lastId - OperationClass.numberOfRowPerPage+1;
		long firstFile = 1;
		long secondFile = 1;
		List<Data> firstResult;
		List<Data> secondResult;
		 
	 		
			/*firstFile  =(long)  Math.ceil(startId/OperationClass.MAXIMUM_RECORD_PER_FILE );
	 		secondFile =(long)  Math.ceil(lastId/OperationClass.MAXIMUM_RECORD_PER_FILE );
			*/
	 		
			if(startId % OperationClass.MAXIMUM_RECORD_PER_FILE != 0){
				firstFile = startId / OperationClass.MAXIMUM_RECORD_PER_FILE;
				firstFile++;
		 	}else{
				firstFile = startId / OperationClass.MAXIMUM_RECORD_PER_FILE;	
		 	}
			
			
			if(lastId % OperationClass.MAXIMUM_RECORD_PER_FILE != 0){
				secondFile = lastId / OperationClass.MAXIMUM_RECORD_PER_FILE;
				secondFile++;
		 	}else{
				secondFile = lastId / OperationClass.MAXIMUM_RECORD_PER_FILE;	
		 	}
			
		 
			List<Data> result = new Vector<>();
	 		
	 		if(firstFile == secondFile){
	 			firstResult =  new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + firstFile).readAllData();	
	 					 
	 			for(int i=0;i<firstResult.size();i++){
	 				if(firstResult.get(i).getId().equals(startId+"")){
	 					
	 					for(int j=i;j<i+OperationClass.numberOfRowPerPage;j++){
	 					try {
	 						result.add(firstResult.get(j));
	 		 						
						} catch (ArrayIndexOutOfBoundsException e) {
							break;
						}catch (Exception e) {
							break;
						}
	 					
	 					 
	 						
	 					}
	 					
	 					readData(result);
	 					return;		
	 				}
	 			}
	 			
	 		}else{
	 		 
	 			
	 			int offSet=OperationClass.numberOfRowPerPage;
				firstResult =  new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + firstFile).readAllData();				
	 			secondResult =new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + secondFile).readAllData();	
	 			
	 			for(int i=0;i<firstResult.size();i++){
	 				if(firstResult.get(i).getId().equals(startId+"")){
	 					for(int j=i;j<firstResult.size();j++){
	 						try {
		 						result.add(firstResult.get(j));
		 		 						
							} catch (ArrayIndexOutOfBoundsException e) {
								break;
							}catch (Exception e) {
								break;
							}
	 						 
	 						offSet--;
	 					}
	 					}
	 			}
	 			for(int i=0;i<offSet;i++){
	 				result.add(secondResult.get(i));
	 			}
	 				readData(result);
					return;		
				
	 		}
	 		
	}
	
	void goToFirstPage() throws ClassNotFoundException, IOException{
		goTo("1");
	}
	void goToLastPage() throws ClassNotFoundException, IOException{
		goTo(OperationClass.totalPage+"");
	}
	void goToNextPage() throws ClassNotFoundException, IOException{
		if(OperationClass.currentPage == OperationClass.totalPage){
			goToFirstPage(); 		
		}else{
			goTo(OperationClass.currentPage+1+"");	
		}
	 
	}
	
	void goToPreviousPage() throws ClassNotFoundException, IOException{
		if(OperationClass.currentPage==1){
			goToLastPage();
		}else{
			goTo(OperationClass.currentPage-1+"");	
		}
		
	
}

	 
}




