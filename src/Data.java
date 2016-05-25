import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Data implements Serializable{
	
	private String id;
	private String name;
	private float unitPrice;
	private int stockQuantity;
	private String importedDate;
	
	
	
	Data(String id,String name,float unitPrice,int stockQuantity,String importedDate){
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.stockQuantity = stockQuantity;
		this.importedDate = importedDate;
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		stockQuantity = stockQuantity;
	}
	public String getImportedDate() {
		return importedDate;
	}
	public void setImportedDate(String importedDate) {
		this.importedDate = importedDate;
	}
	 
	
}
