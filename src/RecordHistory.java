import java.io.Serializable;

public class RecordHistory implements Serializable {
	private long totalRecord;
	private long currentRecord;
	
	
	
	public long getCurrentRecord() {
		return currentRecord;
	}

	public void setCurrentRecord(long currentRecord) {
		this.currentRecord = currentRecord;
	}

	public RecordHistory(long totalRecord,long currentRecord) {
		this.totalRecord = totalRecord;
		this.currentRecord = currentRecord;
	}
	
	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}
}
