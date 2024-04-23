package co.yedam;

public class Member {
	private int memNo;
	private String name;
	private String phone;
	private String birth;
	private String sex;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return String.format("%d %s %s %s %s", memNo, name, phone, birth, sex);
	}
	
	public String showDetail() {
		return "회원번호: " + memNo + "\t성별: " + sex
			+ "\n회원이름: " + name + " 연락처: " + phone
			+ "\n이메일: " + email + "\n생년월일: " + birth;
	}
	
}