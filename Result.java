package ch20.oracle.sec12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Result {
	//Field
		private Scanner scanner = new Scanner(System.in);
		private Connection conn;
		
		public Result() {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				
					conn = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521/XE", 
							"김자바", // 사용자아이디
							"12345" // 사용자비번
					);
					
			}catch(Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		
		//Constructor
		
		//Method
		
		public void list() {
			System.out.println();
			System.out.println("[게시물 목록]");
			System.out.println("----------------------------------------------------");
			System.out.printf("%-6s%-12s%-16s%-40s\n", "no","writer","date","title");
			System.out.println("----------------------------------------------------");
			
			try {
				String sql = "" + "SELECT bno, btitle, bcontent, bwriter, bdate "+
			"FROM boards "+"ORDER BY bno DESC";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Board board = new Board();
					board.setBno(rs.getInt("bno"));
					board.setBtitle(rs.getString("btitle"));
					board.setBcontent(rs.getString("bcontent"));
					board.setBwriter(rs.getString("bwriter"));
					board.setBdate(rs.getDate("bdate"));
					System.out.printf("%-6s%-12s%-16s%-40s \n",
							board.getBno(),
							board.getBwriter(),
							board.getBdate(),
							board.getBtitle());
				}
				rs.close();
				pstmt.close();
			
			} catch(SQLException e) {
				e.printStackTrace();
				exit();
			}
			//System.out.printf("%-6s%-12s%-16s%-40s\n", "1","winter","2023.05.12","게시판에 오신 것을 환영합니다.");
			//System.out.printf("%-6s%-12s%-16s%-40s\n", "2","winter","2023.05.12","올 겨울은 많이 춥습니다.");
			mainMenu();
		}
		
		public void mainMenu() {
			System.out.println();
			System.out.println("----------------------------------------------------");
			System.out.println("메인 메뉴 : 1.Create | 2.Read | 3.Clear | 4.Exit");
			System.out.print("메뉴 선택 : ");
			String menuNo = scanner.nextLine();
			System.out.println();
			
			switch(menuNo) {
			case "1"->create();
			case "2"->read();
			case "3"->clear();
			case "4"->exit();
			}
		}
		
		public void create() {
			System.out.println(" *** create() 메소드 실행됨");
			list();
		}
		public void read() {
			System.out.println(" *** read() 메소드 실행됨");
			list();
		}
		public void clear() {
			System.out.println(" *** clear() 메소드 실행됨");
			list();
		}
		public void exit() {
			System.exit(0);
		}
	public static void main(String[] args) {

		Result boardExample = new Result();
		boardExample.list();
		
	}

}