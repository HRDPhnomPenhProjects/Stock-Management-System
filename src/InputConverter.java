
public class InputConverter {

	public static int toInteger(String value){
		int result = 1;
		try {
		  result = Integer.parseInt(value);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	public static long toLong(String value) {
		long result = 1;
		try {
			  result = Long.parseLong(value);	
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return result;
	}
	
	public static double toDouble(String value) {
		double result = 1;
		try {
			  result = Double.parseDouble(value);	
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return result;
	}
	
	
}
