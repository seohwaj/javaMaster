package co.yedam;

import java.util.List;
import java.util.Scanner;

public class MemManager {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		MemDAO dao = new MemDAO();
		
		while(run) {
			System.out.println("<<수영회원관리>>");
			System.out.println("1.회원목록 2.회원등록 3.정보수정 4.정보삭제 5.회원조회 6.종료");
			System.out.print("선택>> ");
			int menu = Integer.parseInt(sc.nextLine());
			
			switch(menu) {
			case 1:
				List<Member> mems = dao.memList();
				System.out.println("회원번호 회원명    회원연락처   회원생일 성별");
				System.out.println("---------------------------------");
				for(Member mem : mems) {
					System.out.println(mem.toString());
				}
				break;
			case 2:
				System.out.print(" 회원명>> ");
				String name = sc.nextLine();
				System.out.print(" 연락처>> ");
				String phone = sc.nextLine();
				System.out.print(" 생일>> ");
				String birth = sc.nextLine();
				System.out.print(" 성별>> ");
				String sex = sc.nextLine();
				System.out.print(" 이메일>> ");
				String email = sc.nextLine();
				
				Member mem = new Member();
				mem.setName(name);
				mem.setPhone(phone);
				mem.setBirth(birth);
				mem.setSex(sex);
				mem.setEmail(email);
				
				if(dao.insertMem(mem)) {
					System.out.println("저장완료");
				} else {
					System.out.println("저장실패");
				}
				break;
			case 3:
				System.out.print(" 수정할 회원번호>> ");
				String memNo = sc.nextLine();
				System.out.print(" 연락처>> ");
				phone = sc.nextLine();
				
				mem = new Member();
				mem.setMemNo(Integer.parseInt(memNo));
				mem.setPhone(phone);
				
				if(dao.updateMem(mem)) {
					System.out.println("수정완료");
				} else {
					System.out.println("수정실패");
				}
				break;
			case 4:
				System.out.print(" 삭제할 회원번호>> ");
				memNo = sc.nextLine();
				
				mem = new Member();
				mem.setMemNo(Integer.parseInt(memNo));
				
				if(dao.deleteMem(mem)) {
					System.out.println("삭제완료");
				} else {
					System.out.println("삭제실패");
				}
				break;
			case 5:
				System.out.print("조회할 회원번호>> ");
				memNo = sc.nextLine();
				mem = new Member();
				mem.setMemNo(Integer.parseInt(memNo));
				System.out.println(dao.searchMem(mem));
				break;
			case 6:
				// 종료
				System.out.println("종료");
				run = false;
			}
		}
		sc.close();
	}

}