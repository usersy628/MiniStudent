package ex220317;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Util u = new Util();
		Student[] stuArr = new Student[10];
		Student s = null;

		ArrayList<UserInfo> userArr = new ArrayList<>();
		UserInfo ui = null;

		boolean run = true;
		boolean loginCk = false;
		boolean regCK = true;
		int cnt = 0;

		String loginId = "";
		String loginPw = "";
		String loginName = "";

		String[] name = { "이순신", "강감찬", "이도열", "보라동", "돌이군", "이장사", "꼬리동", "스프링", "자바동", "홍길동" };

		for (int i = 0; i < name.length; i++) {
			ui = new UserInfo();
			ui.setId("t" + i);
			ui.setPw("t" + i);
			ui.setName(name[i]);
			userArr.add(ui);
		}

		for (int i = 0; i < name.length; i++) {
			s = new Student();
			s.setName(userArr.get(i).getName());
			s.setKor(u.rdnum());
			s.setEng(u.rdnum());
			s.setMat(u.rdnum());

			s.setTot(u.tot(s.getKor(), s.getEng(), s.getMat()));
			s.setAve(u.ave(s.getTot(), 3));
			s.setGrade(u.grade(s.getAve()));

			stuArr[cnt] = s;
			cnt++;
		}

		while (run) {

			System.out.println("\t<\t\t성적프로그램\t\t>\t");

			if (loginCk) {
				System.out.println("1.등록 2.리스트 3.정보수정 4.로그아웃(" + loginName + "님) 5.검색 6.회원리스트 0.종료");
			} else {
				System.out.println("1.등록 2.리스트 3.회원가입 4.로그인 5.검색 6.회원리스트 0.종료");
			}
			int menu = u.menuCheck("선택");

			switch (menu) {
			case 1:
				if (loginCk) {
					s = new Student();
					s.setName(u.nameCheck("이름"));
					s.setKor(u.inCheck("국어"));
					s.setEng(u.inCheck("영어"));
					s.setMat(u.inCheck("수학"));

					s.setTot(u.tot(s.getKor(), s.getEng(), s.getMat()));
					s.setAve(u.ave(s.getTot(), 3));
					s.setGrade(u.grade(s.getAve()));

					stuArr[cnt] = s;
					cnt++;

				} else {
					System.out.println("로그인 후 이용가능합니다.");
				}
				break;
			case 2:
				if (loginCk) {
					System.out.println("\t\t[ 성적리스트 ]");
					System.out.println("이름\t국어\t영어\t수학\t총점\t평균\t학점");
					System.out.println("==========================================================");

					for (int i = 0; i < stuArr.length; i++) {
						if (stuArr[i] != null) {
							System.out.printf("%s\t%d\t%d\t%d\t%d\t%.2f\t%s\n", stuArr[i].getName(), stuArr[i].getKor(),
									stuArr[i].getEng(), stuArr[i].getMat(), stuArr[i].getTot(), stuArr[i].getAve(),
									stuArr[i].getGrade());
						}
					}
				} else {
					System.out.println("로그인 후 이용가능합니다.");
				}
				break;
			case 3:
				if (loginCk) {
					System.out.println("[1]비밀번호변경\n[2]회원탈퇴\n");
					int mm = u.menuCheck("메뉴");
					switch (mm) {
					case 1:
						for (int i = 0; i < userArr.size(); i++) {
							if (loginPw.equals(userArr.get(i).getPw())) {
								System.out.println("변경할 비밀번호를 입력해주세요 : ");
								userArr.get(i).setPw(sc.nextLine());
								System.out.println("비밀번호가 변경되었습니다.");
							}
						}
						break;
					case 2:
						System.out.println("회원을 탈퇴 하시겠습니까?");
						int yn = u.menuCheck("[1]예\n[2]아니오\n");
						if (yn == 1) {
							for (int i = 0; i < userArr.size(); i++) {
								if (loginId.equals(userArr.get(i).getId())) {
									userArr.remove(i);
									System.out.println("탈퇴되었습니다.");
									loginCk = !loginCk;
								}
							}
						}
						break;
					default:
						System.out.println("메뉴를 다시 선택해주세요.");
					}

				} else {
					ui = new UserInfo();
					System.out.println("\t*** 회원가입페이지 ***");
					String id = "";
					boolean run2 = true;
					while (run2) {
						id = u.nameCheck("id");
						for (UserInfo info : userArr) {
							if (id.equals(info.getId())) {
								System.out.println("중복된 아이디 입니다.");
								regCK = false;
								break;
							} else {
								regCK = true;
							}
						}
						if (regCK) {
							break;
						}
					}

					ui.setId(id);
					ui.setPw(u.nameCheck("pw"));
					ui.setName(u.nameCheck("이름"));
					userArr.add(ui);

				}
				break;
			case 4:
				if (loginCk) {
					System.out.println("로그아웃 성공");
					loginCk = !loginCk;
					break;
				} else {
					System.out.println("\t\t[ 로그인 ]");
					String idd = u.nameCheck("ID");
					String pww = u.nameCheck("PASSWORD");
					for (UserInfo info : userArr) {
						if (idd.equals(info.getId())) {
							if (pww.equals(info.getPw())) {
								System.out.println("로그인 성공");
								loginId = info.getId();
								loginPw = info.getPw();
								loginName = info.getName();

								loginCk = true;
							} else {
								System.out.println("비밀번호를 확인하세요");
							}
						}
					}

				}
				break;
			case 5:
				if (loginCk) {
					System.out.println("\t\t[ 검색페이지 ]");
					System.out.println("[1]이름으로 검색\n[2]학점으로 검색\n");
					int mm = u.menuCheck("메뉴");
					switch (mm) {
					case 1:
						String ns = u.nameCheck("이름");
						System.out.println("이름\t아이디\t비밀번호");
						System.out.println("----------------------");
						for (int i = 0; i < userArr.size(); i++) {
							if (userArr.get(i).getName().equals(ns)) {
								System.out.printf("%s\t%s\t%s\n", userArr.get(i).getName(), userArr.get(i).getId(),
										userArr.get(i).getPw());
							}
						}
						break;
					case 2:
						System.out.println("(A~F까지 입력 가능합니다)");
						String gs = u.nameCheck("학점");
						System.out.println("이름\t국어\t영어\t수학\t총점\t평균\t학점");
						System.out.println("==========================================================");
						for (int i = 0; i < stuArr.length; i++) {
							if (stuArr[i] != null) {
								if (stuArr[i].getGrade().equals(gs)) {
									System.out.printf("%s\t%d\t%d\t%d\t%d\t%.2f\t%s\n", stuArr[i].getName(),
											stuArr[i].getKor(), stuArr[i].getEng(), stuArr[i].getMat(),
											stuArr[i].getTot(), stuArr[i].getAve(), stuArr[i].getGrade());
								}
							}
						}
						break;
					default:
						System.out.println("메뉴를 다시 선택해주세요.");
					}
				} else {
					System.out.println("로그인 후 이용가능합니다.");
				}
				break;

			case 6:
				if (loginCk) {
					System.out.println("\t\t[ 회원리스트 ]");
					System.out.println("이름\tID\tPW");
					System.out.println("---------------------------");
					for (int i = 0; i < userArr.size(); i++) {
						System.out.printf("%s\t%s\t%s\n", userArr.get(i).getName(), userArr.get(i).getId(),
								userArr.get(i).getPw());
					}
				} else {
					System.out.println("로그인 후 이용 가능합니다.");
				}

				break;
			case 0:
				System.out.println("프로그램 종료");
				run = false;
				break;
			default:
				System.out.println("메뉴를 다시 선택해주세요.");
			}
		}
		sc.close();
	}
}
