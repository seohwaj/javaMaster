package co.yedam;

public class Manage {
	private int mngNo;
	private int bookNo;
	private String bookName;
	private int memNo;
	private String memName;
	private String borrowDate;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}
	
	public int getMngNo() {
		return mngNo;
	}
	
	public void setMngNo(int mngNo) {
		this.mngNo = mngNo;
	}
	
	public int getBookNo() {
		return bookNo;
	}
	
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	
	public int getMemNo() {
		return memNo;
	}
	
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	
	public String getBorrowDate() {
		return borrowDate;
	}
	
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	@Override
	public String toString() {
		return bookNo + "\t " + bookName + "\t " + memName + "\t " + borrowDate;
	}
	
}
