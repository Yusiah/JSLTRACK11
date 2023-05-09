package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.DBConnection;
import dto.SnackDto;

public class SnackDao {
	
	Connection         con = null;
	PreparedStatement  ps  = null;
	ResultSet          rs  = null;
	
	// 제품코드 중복검사
	public int checkPcode(String p_code) {
		int count = 0;
		String query = "select count(*) as count \r\n" + 
				" from h_김진욱_snack\r\n" + 
				" where p_code = '"+p_code+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		}catch(Exception e) {
			System.out.println("checkPcode() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return count;		
	}
	
	// 삭제
	public int snackDelete(String p_code) {
		int result = 0;
		String query = "delete from h_김진욱_snack\r\n" + 
				" where P_code = '"+p_code+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("snackDelete() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	// 제품 수정
	public int snackUpdate(SnackDto dto) {
		int result = 0;
		String query = "update h_김진욱_snack\r\n" + 
				" set p_name = '"+dto.getP_name()+"', m_code = '"+dto.getM_code()+"', price = "+dto.getPrice()+"\r\n" + 
				" where p_code = '"+dto.getP_code()+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result =ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("snackUpdate() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	
	// 상세보기
	public SnackDto getSnackView(String p_code) {
		SnackDto dto = null;
		String query = "select  s.p_code, s.p_name, c.m_code, c.m_name, to_char(s.price, '999,999') as price\r\n" + 
				" from h_김진욱_snack s, commonsnack c\r\n" + 
				" where s.m_code = c.m_code\r\n" + 
				" and s.p_code = '"+p_code+"'";
		System.out.println(query);
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String p_name   = rs.getString("p_name");
				String m_code   = rs.getString("m_code");
				String m_name   = rs.getString("m_name");
				String strPrice = rs.getString("price");
				dto = new SnackDto(p_code, p_name, m_code, m_name, strPrice);
			}
		}catch(Exception e) {
			System.out.println("getSnackView() 오류 : "+query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dto;
	}
	
	// 목록 조회
	public ArrayList<SnackDto> getSnackList(String gubun, String search){
		ArrayList<SnackDto> dtos = new ArrayList<>();
		String query = "select  s.p_code, s.p_name, c.m_name, to_char(s.price, '999,999') as price\r\n" + 
				" from h_김진욱_snack s, commonsnack c\r\n" + 
				" where s.m_code = c.m_code\r\n" + 
				" and "+gubun+" like '%"+search+"%'\r\n" + 
				" order by p_code desc";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String p_code   = rs.getString("p_code");
				String p_name   = rs.getString("p_name");
				String m_name   = rs.getString("m_name");
				String strPrice = rs.getString("price");
				SnackDto dto = new SnackDto(p_code, p_name, m_name, strPrice);
				dtos.add(dto);
			}
		}catch(Exception e) {
			System.out.println("getSnackList() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}
	
	
	// 제품 등록
	public int snackSave(SnackDto dto) {
		int result = 0;
		String query = "insert into h_김진욱_snack\r\n" + 
				" (p_code, p_name, m_code, price)\r\n" + 
				" values('"+dto.getP_code()+"', '"+dto.getP_name()+"', '"+dto.getM_code()+"', "+dto.getPrice()+")";
		System.out.println(query);
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("snackSave() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	// 제조사 목록
	public ArrayList<SnackDto> getCompanyList(){
		 ArrayList<SnackDto> dtos = new ArrayList<>();
		 String query = "select m_code, m_name\r\n" + 
		 		" from commonsnack\r\n" + 
		 		" order by m_name";
		 try {
			 con = DBConnection.getConnection();
			 ps = con.prepareStatement(query);
			 rs = ps.executeQuery();
			 while(rs.next()) {
				 String m_code = rs.getString("m_code");
				 String m_name = rs.getString("m_name");
				 SnackDto dto = new SnackDto(m_code, m_name);
				 dtos.add(dto);
			 }
		 }catch(Exception e) {
			 System.out.println("getCompanyList() 오류 : "+query);
			 e.printStackTrace();
		 }finally {
			 DBConnection.closeDB(con, ps, rs);
		 }
		 return dtos;
		 
		 
	}

}
