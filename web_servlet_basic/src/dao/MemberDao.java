package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.DBConnection;
import dto.MemberDto;


public class MemberDao {
	
	Connection         con = null;
	PreparedStatement  ps  = null;
	ResultSet          rs  = null;
	
	//삭제
	public int memberDelete(String id) {
		int result = 0;
		String query = "delete from h_김진욱_member\r\n" + 
				" where id = '"+id+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("memberDelete() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	//정보수정
	public int memberUpdate(MemberDto dto) {
		int result = 0;
		String query = "update  h_김진욱_member\r\n" + 
				" set name='"+dto.getName()+"', age='"+dto.getAge()+"', reg_date='"+dto.getReg_date()+"'\r\n" + 
				" where id = '"+dto.getId()+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("memberUpdate() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	//상세조회
	public MemberDto getMemberView(String id) {
		MemberDto dto = null;
		String query = "select id, name, age, to_char(reg_date,'yyyy-MM-dd') as reg_date\r\n" + 
				"from  h_김진욱_member\r\n" + 
				"where id = '"+id+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String reg_date = rs.getString("reg_date");
				dto = new MemberDto(id, name, age, reg_date);
			}
		}catch(Exception e) {
			System.out.println("getMemberView() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dto;
	}
	
	// 회원등록
	public int memberSave(MemberDto dto){
		int result = 0;
		String query = "insert into h_김진욱_member\r\n" + 
				" (id, name, age, reg_date)\r\n" + 
				" values\r\n" + 
				" ('"+dto.getId()+"','"+dto.getName()+"',"+dto.getAge()+",'"+dto.getReg_date()+"')";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("memberSave() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	// 회원 목록
	public ArrayList<MemberDto> getMemberList(String gubun, String search){
		ArrayList<MemberDto> dtos = new ArrayList<>();
		
		String query = "select id, name, age, to_char(reg_date,'yyyy-MM-dd') as reg_date\r\n" + 
				"from  h_김진욱_member\r\n" + 
				"where "+gubun+" like '%"+search+"%'\r\n" + 
				"order by reg_date desc";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String reg_date = rs.getString("reg_date");
				MemberDto dto = new MemberDto(id, name, age, reg_date);
				dtos.add(dto);
				
			}
		}catch(Exception e) {
			System.out.println("getMemberList() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
		
	}
	
	

	

}
