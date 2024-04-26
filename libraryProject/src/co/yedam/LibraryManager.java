package co.yedam;

import java.util.List;
import java.util.Scanner;

public class LibraryManager {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MemDAO memDao = new MemDAO();
		BookDAO bookDao = new BookDAO();
		ManageDAO mngDao = new ManageDAO();
		boolean run = true;
		int menu = 0;
		
		while(run) {
			try {
				System.out.println("----------------------------------------------------------");
				System.out.println("[메인메뉴]");
				System.out.println("1.회원관리 2.도서관리 3.대출/반납관리 4.종료");
				System.out.println("----------------------------------------------------------");
				System.out.print("선택> ");
				
				menu = Integer.parseInt(sc.nextLine());
			} catch(Exception e) {
				System.out.println("[선택할 메뉴의 번호를 입력해주세요]");
			}
			
			switch(menu) {
			// 회원관리 메뉴
			case 1:
				boolean run1 = true;
				
				while(run1) {
					try {
						menu = 0;
						System.out.println("----------------------------------------------------------");
						System.out.println("[회원관리]");
						System.out.println("1.회원목록 2.회원조회 3.회원등록 4.회원수정 5.회원삭제 6.이전메뉴");
						System.out.println("----------------------------------------------------------");
						System.out.print("선택> ");

						menu = Integer.parseInt(sc.nextLine());
					} catch(Exception e) {
						System.out.println("[선택할 메뉴의 번호를 입력해주세요]");
					}
					
					switch(menu) {
					// 회원목록
					case 1:
						System.out.println();
						System.out.println("[회원목록]");
						System.out.println("회원번호\t 회원이름\t 연락처\t\t 주소");
						
						List<Member> mems = memDao.memList();
						
						for(Member mem : mems) {
							System.out.println(mem.toString());
						}
						break;
					// 회원조회
					case 2:
						System.out.println();
						System.out.println("[회원조회]");
						System.out.print("이름> ");
						String name = sc.nextLine();
						
						if(name.isEmpty()) {
							System.out.println("[조회할 회원의 이름이 입력되지 않았습니다]");
							break;
						}
						
						System.out.println();
						System.out.println("[조회결과]");
						System.out.println("회원번호\t 회원이름\t 연락처\t\t 주소");
						
						mems = memDao.searchMem(name);
						
						for(Member mem : mems) {
							System.out.println(mem.toString()); 
						}
						break;
					// 회원등록
					case 3:
						System.out.println();
						System.out.println("[회원등록]");
						System.out.print("이름> ");
						name = sc.nextLine();
						System.out.print("연락처> ");
						String phone = sc.nextLine();
						System.out.print("주소> ");
						String address = sc.nextLine();
						
						if(name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
							System.out.println("[회원 등록 시 공백 입력은 불가합니다]");
							break;
						}
						
						Member mem = new Member();
						mem.setName(name);
						mem.setPhone(phone);
						mem.setAddress(address);
						
						System.out.println();
						if(memDao.insertMem(mem)) {
							System.out.println("[등록완료]");
						} else {
							System.out.println("[등록실패]");
						}
						
						break;
					// 회원수정
					case 4:
						int memNo = 0;
						
						do {
							try {
								System.out.println();
								System.out.println("[회원수정]");
								System.out.print("회원번호> ");
								memNo = Integer.parseInt(sc.nextLine());
								if(memNo <= 0) {
									System.out.println("[존재하지 않는 회원번호입니다]");
								}
							} catch(Exception e) {
								System.out.println("[수정할 회원의 회원번호를 입력해주세요]");
							}
						} while(memNo <= 0);
					
						mem = new Member();
						mem.setMemNo(memNo);
						
						boolean run_update = true;
						
						while(run_update) {
							System.out.println();
							System.out.println("[수정할 정보]");
							System.out.println("1.회원이름 2.연락처 3.주소 4.수정완료");
							System.out.print("선택> ");
							menu = Integer.parseInt(sc.nextLine());
							
							switch(menu) {
							case 1:
								System.out.print("이름> ");
								name = sc.nextLine();
								mem.setName(name);
								break;
							case 2:
								System.out.print("연락처> ");
								phone = sc.nextLine();
								mem.setPhone(phone);
								break;
							case 3:
								System.out.print("주소> ");
								address = sc.nextLine();
								mem.setAddress(address);
								break;
							case 4:
								System.out.println();
								if(memDao.updateMem(mem)) {
									System.out.println("[수정완료]");
								} else {
									System.out.println("[수정실패]");
								}
								run_update = false;
							}
						}
						break;
					// 회원삭제
					case 5:
						try {
							System.out.println();
							System.out.println("[회원삭제]");
							System.out.print("회원번호> ");
							memNo = Integer.parseInt(sc.nextLine());
							
							if(memNo <= 0) {
								System.out.println("[존재하지 않는 회원번호입니다]");
								break;
							}
						} catch(Exception e) {
							System.out.println("[삭제할 회원의 회원번호를 입력해주세요]");
							break;
						}
																		
						System.out.println();
						if(memDao.deleteMem(memNo)) {
							System.out.println("[삭제완료]");
						} else {
							System.out.println("[삭제실패]");
						}
						break;
					// 이전메뉴
					case 6:
						System.out.println("[이전메뉴로 돌아갑니다]");
						run1 = false;
						break;
					case 0:
						break;		
					default:
						System.out.println("[선택할 메뉴의 번호를 입력해주세요]");
					}
				}
				break;
			// 도서관리 메뉴
			case 2:
				boolean run2 = true;
				
				while(run2) {
					try {
						menu = 0;
						System.out.println("----------------------------------------------------------");
						System.out.println("[도서관리]");
						System.out.println("1.도서목록 2.도서조회 3.도서등록 4.도서수정 5.도서삭제 6.이전메뉴");
						System.out.println("----------------------------------------------------------");
						System.out.print("선택> ");
						
						menu = Integer.parseInt(sc.nextLine());						
					} catch(Exception e) {
						System.out.println("[선택할 메뉴의 번호를 입력해주세요]");
					}
					
					switch(menu) {
					// 도서목록
					case 1:
						System.out.println();
						System.out.println("[도서목록]");
						System.out.println("도서번호\t 도서제목\t\t 저자\t 출판사");
						
						List<Book> books = bookDao.bookList();
						
						for(Book book : books) {
							System.out.println(book.toString());
						}
						break;
					// 도서조회
					case 2:
						System.out.println();
						System.out.println("[도서조회]");
						System.out.print("제목> ");
						String bookName = sc.nextLine();
						
						if(bookName.isEmpty()) {
							System.out.println("[조회할 도서의 제목이 입력되지 않았습니다]");
							break;
						}
						
						System.out.println();
						System.out.println("[조회결과]");
						System.out.println("도서번호\t 도서제목\t\t 저자\t 출판사");
						
						books = bookDao.searchBook(bookName);
						
						for(Book book : books) {
							System.out.println(book.toString());
						}
						break;
					// 도서등록
					case 3:
						System.out.println();
						System.out.println("[도서등록]");
						System.out.print("제목> ");
						bookName = sc.nextLine();
						System.out.print("저자> ");
						String writer = sc.nextLine();
						System.out.print("출판사> ");
						String publisher = sc.nextLine();
						
						if(bookName.isEmpty() || writer.isEmpty() || publisher.isEmpty()) {
							System.out.println("[도서 등록 시 공백 입력은 불가합니다]");
							break;
						}
						
						Book book = new Book();
						book.setName(bookName);
						book.setWriter(writer);
						book.setPublisher(publisher);
						
						System.out.println();
						if(bookDao.insertBook(book)) {
							System.out.println("[등록완료]");
						} else {
							System.out.println("[등록실패]");
						}
						break;
					// 도서수정
					case 4:						
						int bookNo = 0;
						
						do {
							try {
								System.out.println();
								System.out.println("[도서수정]");
								System.out.print("도서번호> ");
								bookNo = Integer.parseInt(sc.nextLine());
								
								if(bookNo <= 0) {
									System.out.println("[존재하지 않는 도서번호입니다]");
								}
							} catch(Exception e) {
								System.out.println("[수정할 도서의 도서번호를 입력해주세요]");
							}
						} while(bookNo <= 0);
						
						book = new Book();
						book.setBookNo(bookNo);
						
						boolean run_update = true;
						
						while(run_update) {
							System.out.println();
							System.out.println("[수정할 정보]");
							System.out.println("1.도서제목 2.저자 3.출판사 4.수정완료");
							System.out.print("선택> ");
							menu = Integer.parseInt(sc.nextLine());
							
							switch(menu) {
							case 1:
								System.out.print("도서제목> ");
								bookName = sc.nextLine();
								book.setName(bookName);
								break;
							case 2:
								System.out.print("저자> ");
								writer = sc.nextLine();
								book.setWriter(writer);
								break;
							case 3:
								System.out.print("출판사> ");
								publisher = sc.nextLine();
								book.setPublisher(publisher);
								break;
							case 4:
								System.out.println();
								if(bookDao.updateBook(book)) {
									System.out.println("[수정완료]");
								} else {
									System.out.println("[수정실패]");
								}
								run_update = false;
							}
						}
						break;
					// 도서삭제
					case 5:
						try {
							System.out.println();
							System.out.println("[도서삭제]");
							System.out.print("도서번호> ");
							bookNo = Integer.parseInt(sc.nextLine());
							
							if(bookNo <= 0) {
								System.out.println("[존재하지 않는 도서번호입니다]");
								break;
							}
						} catch(Exception e) {
							System.out.println("[삭제할 도서의 도서번호를 입력해주세요]");
							break;
						}						
						
						System.out.println();
						if(bookDao.deleteBook(bookNo)) {
							System.out.println("[삭제완료]");
						} else {
							System.out.println("[삭제실패]");
						}
						
						break;
					// 이전메뉴
					case 6:
						System.out.println("[이전메뉴로 돌아갑니다]");
						run2 = false;
						break;
					case 0:
						break;		
					default:
						System.out.println("[선택할 메뉴의 번호를 입력해주세요]");
					}
				}
				break;
			// 대출/반납관리 메뉴
			case 3:
				boolean run3 = true;
				
				while(run3) {
					try {
						menu = 0;
						System.out.println("----------------------------------------------------------");
						System.out.println("[대출/반납관리]");
						System.out.println("1.전체도서목록 2.대출도서목록 3.대출도서조회 4.대출 5.반납 6.이전메뉴");
						System.out.println("----------------------------------------------------------");
						System.out.print("선택> ");

						menu = Integer.parseInt(sc.nextLine());
					} catch(Exception e) {
						System.out.println("[선택할 메뉴의 번호를 입력해주세요]");
					}
					
					switch(menu) {
					// 전체도서목록
					case 1:
						System.out.println();
						System.out.println("[전체도서목록]");
						System.out.println("도서번호\t 도서제목\t\t 저자\t 출판사");
						
						List<Book> books = bookDao.bookList();
						
						for(Book book : books) {
							System.out.println(book.toString());
						}
						break;
					// 대출도서목록
					case 2:
						System.out.println();
						System.out.println("[대출도서목록]");
						System.out.println("도서번호\t 도서제목\t\t 대출회원\t 대출일자");
						
						List<Manage> mngs = mngDao.borrowList();
						
						for(Manage mng : mngs) {
							System.out.println(mng.toString());
						}
						break;
					// 대출도서조회
					case 3:
						System.out.println();
						System.out.println("[대출도서조회]");
						System.out.print("제목> ");
						String bookName = sc.nextLine();
						
						if(bookName.isEmpty()) {
							System.out.println("[조회할 도서의 제목이 입력되지 않았습니다]");
							break;
						}
						
						System.out.println();
						System.out.println("[조회결과]");
						System.out.println("도서번호\t 도서제목\t\t 대출회원\t 대출일자");
						
						mngs = mngDao.searchBook(bookName);
						
						for(Manage mng : mngs) {
							System.out.println(mng.toString());
						}						
						break;
					// 대출
					case 4:
						int bookNo = 0;
						
						do {
							try {
								System.out.println();
								System.out.println("[대출]");
								System.out.print("대출도서번호> ");
								bookNo = Integer.parseInt(sc.nextLine());
								
								if(bookNo <= 0) {
									System.out.println("[존재하지 않는 도서번호입니다]");
								}
							} catch(Exception e) {
								System.out.println("[대출할 도서의 도서번호를 입력해주세요]");
							}				
						} while(bookNo <= 0);
						
						System.out.println();
						System.out.println("[회원조회]");
						System.out.print("이름> ");
						String name = sc.nextLine();
						
						System.out.println();
						System.out.println("[조회결과]");
						System.out.println("회원번호\t 회원이름\t 연락처\t\t 주소");
						
						List<Member> mems = memDao.searchMem(name);
						
						for(Member mem : mems) {
							System.out.println(mem.toString()); 
						}
						
						int memNo = 0;
						
						do {
							try {
								System.out.println();
								System.out.print("대출회원번호> ");
								memNo = Integer.parseInt(sc.nextLine());
								
								if(memNo <= 0) {
									System.out.println("[존재하지 않는 회원번호입니다]");
								}
							} catch(Exception e) {
								System.out.println("[대출할 회원의 회원번호를 입력해주세요]");
							}				
						} while(memNo <= 0);
						
						System.out.println();
						if(mngDao.borrowBook(bookNo, memNo)) {
							System.out.println("[대출완료]");
						} else {
							System.out.println("[대출실패]");
						}
						break;
					// 반납
					case 5:
						try {
							System.out.println();
							System.out.println("[반납]");
							System.out.print("반납도서번호> ");
							bookNo = Integer.parseInt(sc.nextLine());
							
							if(bookNo <= 0) {
								System.out.println("[존재하지 않는 도서번호입니다]");
								break;
							}
						} catch(Exception e) {
							System.out.println("[반납할 도서의 도서번호를 입력해주세요]");
							break;
						}
						
						System.out.println();
						if(mngDao.returnBook(bookNo)) {
							System.out.println("[반납완료]");
						} else {
							System.out.println("[반납실패]");
						}
						break;
					// 이전메뉴
					case 6:
						System.out.println("[이전메뉴로 돌아갑니다]");
						run3 = false;
						break;
					case 0:
						break;		
					default:
						System.out.println("[선택할 메뉴의 번호를 입력해주세요]");
					}
				}
				break;
			// 종료
			case 4:
				System.out.println("[프로그램을 종료합니다]");
				run = false;
				break;
			case 0:
				break;		
			default:
				System.out.println("[선택할 메뉴의 번호를 입력해주세요]");
			}
		}
		sc.close();
	}

}
