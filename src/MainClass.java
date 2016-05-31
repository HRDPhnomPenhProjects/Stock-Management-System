 
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
	public static final String READ_SHORT = "R#";
	public static final String DELETE_SHORT = "D#";
	public static final String WRITE_SHORT = "W#";
	public static final String UPDATE_SHORT = "U#";
	
	
	
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
	public static final int EXIT_SHORT = 27;
	
	public static final int BACK_SPACE = 8;
	public static final int SHIFT = 16;
	
	
	
	List<Data> result;
	 
	
	
	 public static Scanner input;
 
	
	public static void main(String[] args) {
		double old = new Date().getTime();
	
	  	MainClass mainClass = new MainClass();
		String short_cut[];
		
		
	  	// input = new Scanner(System.in);
	  	 
       OperationClass.totalRecord =  10000000l;
		
	   	  try {
			OperationClass.totalRecord = OperationClass.getInstance().getRecordHistory().getCurrentRecord();
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
			System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
		} catch (Exception e) {
			System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
		}
	  	
  try {
			mainClass.initializeData();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
	 	
	  
	  	
	  	
	 	try {
			mainClass.readData();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
		} catch (Exception e) {
			System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
		}
	   
	  	
	 		System.out.println();
			System.out.println("Speed " +(new Date().getTime() - old)/1000 +" seconds");
			String choice = "";
			 
			
			
			while (true) {
				
			System.out.println("\n");
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
				} else if(MyConsoleListener.keyboardCode == EXIT_SHORT){
					System.exit(0);
					break;
				} 
				
			 
				
			} 
			 
			 
		 
	 
			 
			 MyConsoleListener.option = "";
		/*	 
		 	 while (input.hasNextLine()) {
		 	System.out.println(input.nextLine());
			}	  */
			  
			 
			 short_cut  = choice.split("#");
			  if(short_cut.length==2){
				  if(short_cut[0].toUpperCase().equals(READ)){
					  choice = "R#";  
				  }else if(short_cut[0].toUpperCase().equals(DELETE)){
					  choice = "D#";
				  }else if(short_cut[0].toUpperCase().equals(WRITE)){
					  choice = "W#";
				  }else if(short_cut[0].toUpperCase().equals(UPDATE)){
					  choice = "U#";
				  }
				   
			  }
			  
			 input = new Scanner(System.in);
			 
			 
			 
 			switch (choice.toUpperCase().trim()) {
			case DISPLAY:
				try {
					mainClass.readData();
				}catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");

				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
				break;

			case WRITE:
				try {
					mainClass.addData();
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
					}
			 	 
				break;
				
			case WRITE_SHORT:
				try {
					String fields[] = short_cut[1].split(";");
				
					if(fields.length == 3){
						mainClass.addData(fields[0].trim(),fields[1].trim(),fields[2].trim());	
					} 
					
					 
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
					}
			 	 
				break;
				
				
			case READ:
				 
				System.out.println("---READ---");
				System.out.print("Product ID: ");
				
				try {
					 
					mainClass.readData(input.nextLine());
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
			 	break;
			 	
			case READ_SHORT:			

				System.out.println("---READ---");

				try {
					 
					mainClass.readData(short_cut[1].trim());
						
				} catch (ClassNotFoundException | IOException e) {
				 	System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				 	
				} catch (Exception e) {
			 	System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");		
				}
			 	break;
			 
			case UPDATE:
				 
				System.out.println("---UPDATE---");
				System.out.print("Product ID: ");
				try {
					mainClass.update(input.nextLine());
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
				
				break;

			
			case UPDATE_SHORT:
				 
				String fields[] = short_cut[1].split(";");
				
				
				try {
				 	 
					 if(fields.length == 2){
						System.out.println();
						if(OperationClass.getInstance().updateAll(fields[0].trim(),fields[1].trim())){
							System.out.println("Update succesfully...");
						}else{
							System.out.println("Error while Updating...");			
						}
					 
					}
					if(fields.length == 3){
						System.out.println();
						if(OperationClass.getInstance().updateAll(fields[0].trim(),fields[1].trim(),fields[2].trim())){
							System.out.println("Update succesfully...");
						}else{
							System.out.println("Error while Updating...");			
						}
					 
					}
					if(fields.length == 4){
						System.out.println();
						if(OperationClass.getInstance().updateAll(fields[0].trim(),fields[1].trim(),fields[2].trim(),fields[3].trim())){
							System.out.println("Update succesfully...");
						}else{
							System.out.println("Error while Updating...");			
						}
					 
					} 
					
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
				
				break;
				
			case DELETE_SHORT:			

				System.out.println("---READ---");

				try {
					 
					mainClass.delete(short_cut[1].trim());
						
				} catch (ClassNotFoundException | IOException e) {
				 	System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				 	
				} catch (Exception e) {
			 	System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");		
				}
			 	break;

			case DELETE:
				 
				System.out.println("---DELETE---");
				System.out.print("Product ID: ");
				try {
					mainClass.delete(input.nextLine());
				}catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
			 	break;
			 	
			case FIRST:
			 
				try {
					mainClass.goToFirstPage();
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
				break;

			case PREVIOUS:
			 
				try {
					mainClass.goToPreviousPage();
				}catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
				break;

			case NEXT:
				 
				try {
					mainClass.goToNextPage();
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
				
				break;

			case LAST:
			 
				try {
					mainClass.goToLastPage();
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
				
				break;

			case SEARCH:
				 
				System.out.println("---SEARCH---");
				System.out.print("Input Name: ");
				try {
					mainClass.searchByName(input.nextLine());
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
				break;
			
			case GO_TO:
				 
				System.out.println("---GO TO---");
				System.out.print("Input Page No#: ");
				try {
					mainClass.goTo(input.nextLine());
				}catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
					
				break;

			case SET_ROW:
			 
				System.out.println("---SET ROWS (MAX = 1000 rows)---");
				System.out.print("Input rows num: ");
				try {
					mainClass.setRow(input.nextLine());
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (Exception e) {
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}
				break;
			case BACK_UP:
				try {
					OperationClass.getInstance().backupAllData();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}				
				break;
			case RESTORE:
				try {
					OperationClass.getInstance().restoreAllData(); 
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Error 404 ! Contact PHNOM PENH Group 3 For more infomation");
				}				
				break;
			 
			case HELP:
				mainClass.help();
				break;
			case EXIT:
				System.exit(0);
				break;

				
			default:
				break;
			}
		}
			
	}
	
private void addData(String name, String price, String qty) throws ClassNotFoundException, IOException {
		 
	File f = new File(FileNameClass.STOCK_RECORD_DIRECTORY_PATH);
	 
 	List<Data> result =(List<Data>)new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" +f.listFiles().length).readAllData();
 	
 	List<Data> newData = new ArrayList<>(); 
 	
 	if(result.size() == OperationClass.MAXIMUM_RECORD_PER_FILE){
 		String id = OperationClass.getInstance().getRecordHistory().getCurrentRecord()+1 + "";
 		 
 	 
 		boolean isNum = false;
			int validate;
			
			try {
				
				validate = Integer.parseInt(name);
			isNum = true;
		} catch (Exception e) {
			// TODO: handle exception
			
		}

			finally{
				if(isNum){
					System.out.println("Name can't be number");
					return ;
				}
			} 
 	 
 		float unitPrice = Float.parseFloat(price);
 		 
 	 
 		int quantity = Integer.parseInt(qty);
 		System.out.println();	
 		 
 		
 		newData.add(new Data(id, name, (float)unitPrice, (int)quantity, Today.today));
		new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + (f.listFiles().length+1)).addData(newData);
	 	setRow(OperationClass.numberOfRowPerPage+"");
 		
 	}else{
 		String id = OperationClass.getInstance().getRecordHistory().getCurrentRecord()+1 + "";
 	 
 		boolean isNum = false;
			int validate;
			
			try {
				
				validate = Integer.parseInt(name);
			isNum = true;
		} catch (Exception e) {
			// TODO: handle exception
			
		}

			finally{
				if(isNum){
					System.out.println("Name can't be number");
					return ;
				}
			}  
 		
 		
 		float unitPrice = Float.parseFloat(price);
 		int quantity = Integer.parseInt(qty);
 		System.out.println();	
 		 
 		
 		newData.add(new Data(id, name, (float)unitPrice, (int)quantity, Today.today));
		new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + f.listFiles().length).addData(newData);
		setRow(OperationClass.numberOfRowPerPage+"");
			
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
	
	private void help() {
		// TODO Auto-generated method stub
 
		System.out.println("--------------------------------------------------------------------------------------------------------");
		System.out.println("=======================================================================================================");
		System.out.println(" 			1. Move Page																				");
		System.out.println(" 			   - NEXT ARROW move to NEXT PAGE															");
		System.out.println(" 			   - BACK ARROW move to NEXT PAGE														   ");
		System.out.println("  			   - UP ARROW move to NEXT PAGE															   ");
		System.out.println(" 			   - DOWN KEY move to NEXT PAGE															   ");
		System.out.println(" 			    				  																	   ");
		System.out.println(" 			2. Read Data																			   ");
		System.out.println(" 			   - [r|R] + # + ID which you want to read 												   ");
		System.out.println(" 			    				  																	   ");
		System.out.println(" 			3. DELETE Data																			   ");
		System.out.println(" 			   - [d|D] + # + ID which you want to delete 												");
		System.out.println(" 			4. WRITE Data																			   ");
		System.out.println(" 			   - [w|W] + # + ID which you want to write and + ; to splite the data 					   ");		
		System.out.println(" 			5. update Data																			   ");
		System.out.println(" 			   - [u|U] + # + ID which you want to write and + ; to splite the data 					   ");	
		System.out.println(" 			6. EXIT progam																			   ");
		System.out.println(" 			   - Press ESC 																			   ");	
		System.out.println(" 			    				  																	   ");
		System.out.println("========================================================================================================");
		System.out.println("--------------------------------------------------------------------------------------------------------");
		
	
	
			
	}

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
		
		OperationClass.currentPage = 1;
		
		File f = new File(FileNameClass.STOCK_RECORD_DIRECTORY_PATH);
		
		
		
		 result =  new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_1" /*+ f.listFiles().length*/).readAllData();	
		 System.out.println("\n\n\n");
			
	  	
		 System.out.println("		 ____________________________________________________________________________________________");	
		 System.out.print("		");
		 
		 System.out.print("||	ID		|");
		 System.out.print("|	Name		|");
		 System.out.print("|	Price	    |");
		 System.out.print("|	Quantity	    ||");
		 System.out.println();
		 System.out.println("		||______________________||______________________||__________________||______________________||");
			
		 
		String pattern = "%-20s || %-20s || %-16s || %-21s||";
	  	 
	 	for(int i = 0 ; i<OperationClass.numberOfRowPerPage ; i++){
	 		 System.out.format(pattern,"\t\t||\t" + result.get(i).getId() ,result.get(i).getName() , result.get(i).getUnitPrice() , result.get(i).getStockQuantity());
	 		 System.out.println();
	 		 System.out.println("		||______________________||______________________||__________________||______________________||");
	 			 	
		}
		 	 System.out.println("\t\t\t Page: " + OperationClass.currentPage +"/"+ OperationClass.totalPage +"\t\t\tTotal Record: "+ OperationClass.getInstance().getRecordHistory().getCurrentRecord() +"\t");
		 	 System.out.println("		||__________________________________________________________________________________________||");
	 	
	 	 System.out.println("\t\t     _____________________________________________________________________________________");
	 	 System.out.println("\t\t    | *)Display | W)rite | R)ead | U)pdate | D)elete | F)irst | P)revious | N)ext | L)ast |");
		 System.out.println("\t\t    |           S)earch | G)o to | Se)t row | B)ack up | Re)store | H)elp | E)xit         |");
		 System.out.println("\t\t    |_____________________________________________________________________________________|");

	}
	
	
	
	void readData(List<Data> result) throws ClassNotFoundException, IOException{
		
		System.out.println(result.size());
		
		System.out.println("\n\n\n");
	
		 System.out.println("		 ____________________________________________________________________________________________");	
		 System.out.print("		");
		 
		 System.out.print("||	ID		|");
		 System.out.print("|	Name		|");
		 System.out.print("|	Price	    |");
		 System.out.print("|	Quantity	    ||");
		 System.out.println();
		 System.out.println("		||______________________||______________________||__________________||______________________||");
			
		 
		 String pattern = "%-20s || %-20s || %-16s || %-21s||";
	  	 
	
		
	  	 
	 	for(int i = 0 ; i<result.size() ; i++){
	 		 System.out.format(pattern,"\t\t||\t" + result.get(i).getId(),result.get(i).getName() , result.get(i).getUnitPrice() , result.get(i).getStockQuantity());
	 		 System.out.println();
	 		 System.out.println("		||______________________||______________________||__________________||______________________||");
 			 	
		}
	 	 System.out.println("\t\t\t Page: " + OperationClass.currentPage +"/"+ OperationClass.totalPage +"\t\t\tTotal Record: "+ OperationClass.getInstance().getRecordHistory().getCurrentRecord() +"\t");
	 	 System.out.println("		||__________________________________________________________________________________________||");
 	
	
		 
	 	
	 	 System.out.println("\t\t     _____________________________________________________________________________________");
	 	 System.out.println("\t\t    | *)Display | W)rite | R)ead | U)pdate | D)elete | F)irst | P)revious | N)ext | L)ast |");
		 System.out.println("\t\t    |           S)earch | G)o to | Se)t row | B)ack up | Re)store | H)elp | E)xit         |");
		 System.out.println("\t\t    |_____________________________________________________________________________________|");

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
					setRow(OperationClass.numberOfRowPerPage+"");
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
			System.out.print("Option: ");
			String choice = input.nextLine();
			switch (choice.toUpperCase()) {
			case ALL:
				
				System.out.print("Name: ");
				String name = input.nextLine();
				
				System.out.print("Price: ");
				String price = input.nextLine();
				
				System.out.print("Quantity: ");
				String qty = input.nextLine();
				
				if(OperationClass.getInstance().updateAll(id, name, price, qty)){
					System.out.println("Update succesfully...");
				}else{
					System.out.println("Error while Updating...");			
				}
				
				
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
	 	
	 	List<Data> newData = new ArrayList<>(); 
	 	
	 	if(result.size() == OperationClass.MAXIMUM_RECORD_PER_FILE){
	 		String id = OperationClass.getInstance().getRecordHistory().getCurrentRecord()+1 + "";
	 		System.out.print("Product ID   : " + id);
	 		System.out.println();	
	 		
	 		System.out.print("Product Name : ");
	 		String name = input.nextLine();
	 		boolean isNum = false;
 			int validate;
 			
 			try {
 				
 				validate = Integer.parseInt(name);
				isNum = true;
			} catch (Exception e) {
				// TODO: handle exception
				
			}

 			finally{
 				if(isNum){
 					System.out.println("Name can't be number");
 					return ;
 				}
 			} 
	 		System.out.print("Unit Price   : ");
	 		float unitPrice = Float.parseFloat(input.nextLine());
	 		 
	 		System.out.print("Quantity     : ");
	 		int quantity = Integer.parseInt(input.nextLine());
	 		System.out.println();	
	 		 
	 		
	 		newData.add(new Data(id, name, (float)unitPrice, (int)quantity, Today.today));
			new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + (f.listFiles().length+1)).addData(newData);
		 	setRow(OperationClass.numberOfRowPerPage+"");
	 		
	 	}else{
	 		String id = OperationClass.getInstance().getRecordHistory().getCurrentRecord()+1 + "";
		 	System.out.print("Product ID   : " + id);
	 		System.out.println();	
	 		
	 		System.out.print("Product Name : ");
	 		String name = input.nextLine();
	 		boolean isNum = false;
 			int validate;
 			
 			try {
 				
 				validate = Integer.parseInt(name);
				isNum = true;
			} catch (Exception e) {
				// TODO: handle exception
				
			}

 			finally{
 				if(isNum){
 					System.out.println("Name can't be number");
 					return ;
 				}
 			}  
	 		
	 		
	 		System.out.print("Unit Price   : ");
	 		float unitPrice = Float.parseFloat(input.nextLine());
	 		 
	 		System.out.print("Quantity     : ");
	 		int quantity = Integer.parseInt(input.nextLine());
	 		System.out.println();	
	 		 
	 		
	 		newData.add(new Data(id, name, (float)unitPrice, (int)quantity, Today.today));
			new OperationClass(FileNameClass.STOCK_RECORD_PATH + "_" + f.listFiles().length).addData(newData);
			setRow(OperationClass.numberOfRowPerPage+"");
				
	 	}	
	 	 
	 	
	}
	
	
	void searchByName(String name) throws ClassNotFoundException, IOException{
		
		List<Data> result = OperationClass.getInstance().searchByName(name);
		 System.out.println("		 ____________________________________________________________________________________________");	
		 System.out.print("		");
		 
		 System.out.print("||	ID		|");
		 System.out.print("|	Name		|");
		 System.out.print("|	Price	    |");
		 System.out.print("|	Quantity	    ||");
		 System.out.println();
		 System.out.println("		||______________________||______________________||__________________||______________________||");
			
		 
		 String pattern = "%-20s || %-20s || %-16s || %-21s||";
	  
		
	  	 
	 	for(int i = 0 ; i<result.size() ; i++){
	 		 System.out.format(pattern,"\t\t||\t" + result.get(i).getId(),result.get(i).getName() , result.get(i).getUnitPrice() , result.get(i).getStockQuantity());
	 		 System.out.println();
	 		 System.out.println("		||______________________||______________________||__________________||______________________||");
			 	
		}
		 	 System.out.println("\t\t\t\t\tTotal Record Found: "+ result.size() +"\t");
		 	 System.out.println("		||______________________________________________________________________||");
	 	
		 
	 	
	 	 System.out.println("\t\t     _____________________________________________________________________________________");
	 	 System.out.println("\t\t    | *)Display | W)rite | R)ead | U)pdate | D)elete | F)irst | P)revious | N)ext | L)ast |");
		 System.out.println("\t\t    |           S)earch | G)o to | Se)t row | B)ack up | Re)store | H)elp | E)xit         |");
		 System.out.println("\t\t    |_____________________________________________________________________________________|");
	}
	
	
 	void setRow(String rows) throws ClassNotFoundException, IOException{
		int row = Integer.parseInt(rows);
		
		
		if(row <=0){
			System.out.println("--- Allow Only postive number 1 -> 1000");
			return;
		}
		
		
		OperationClass.currentPage = 1;
		if(row>10000){
			OperationClass.numberOfRowPerPage = 1000;
			 
		}else{
			OperationClass.numberOfRowPerPage = row;
		}
		
		if( OperationClass.getInstance().getRecordHistory().getCurrentRecord() % row == 0){
			OperationClass.totalPage = (int) OperationClass.getInstance().getRecordHistory().getCurrentRecord() / row;		
		}else{
			OperationClass.totalPage = (int) OperationClass.getInstance().getRecordHistory().getCurrentRecord() / row;				
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
		//goTo("1");
		readData();
	}
	void goToLastPage() throws ClassNotFoundException, IOException{
		goTo(OperationClass.totalPage+"");String pattern = "%-20s || %-20s || %-16s || %-21s||";
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




