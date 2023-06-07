package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import common.DBConnection;
import dto.MemberDto;

public class MemberDao {
	
	Connection 		  con = null;
	PreparedStatement ps  = null;
	ResultSet         rs  = null;
	
	
	// 회원가입
	public int memberJoin(MemberDto dto) {
		int result = 0;
		String query="insert into bike_김진욱_member\r\n" + 
				"(id, name, password, area, address, mobile_1, mobile_2, mobile_3, gender, hobby_travel, hobby_reading, hobby_sports, reg_date, password_len)\r\n" + 
				"values\r\n" + 
				"('"+dto.getId()+"', '"+dto.getName()+"', '"+dto.getPassword()+"', '"+dto.getArea()+"', '"+dto.getAddress()+"', '"+dto.getMobile_1()+"', '"+dto.getMobile_2()+"', '"+dto.getMobile_3()+"', '"+dto.getGender()+"', '"+dto.getHobby_travel()+"', '"+dto.getHobby_reading()+"', '"+dto.getHobby_sports()+"', to_date('"+dto.getReg_date()+"','yyyy-MM-dd HH:mi:ss'), '"+dto.getPassword_len()+"')";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("memberJoin() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
    // ID 중복검사
	public int getIdCount(String id) {
		int count = 0;
		String query = "select count(*) as count\r\n" + 
				"from bike_김진욱_member\r\n" + 
				"where id = '"+id+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		}catch(Exception e){
			System.out.println("getIdCount() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}

	// 로그인
	public MemberDto getLoginName(String id, String password) {
		MemberDto dto = null;
		String query = "select name, memberLevel\r\n" + 
				" from bike_김진욱_member\r\n" + 
				" where id = '"+id+"'\r\n" + 
				" and password = '"+password+"'\r\n" + 
				" and exit_date is null";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String memberLevel = rs.getString("memberLevel");
				dto = new MemberDto(name, memberLevel);
			}
		}catch(Exception e) {
			System.out.println("getLoginName() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	// 로그인 시간 업데이트
	public int setLoginTime(String id, String todayTime) {
		int result = 0;
		String query = "update bike_김진욱_member\r\n" + 
				" set last_login_date =  to_date('"+todayTime+"','yyyy-MM-dd hh24:mi:ss')\r\n" + 
				" where id = '"+id+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("setLoginTime() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}

	public MemberDto getMemberInfo(String id) {
		MemberDto dto = null;
		String query = "select id, name, password, area, address, mobile_1, mobile_2, mobile_3, \r\n" + 
				" gender, hobby_travel, hobby_reading, hobby_sports,\r\n" + 
				" to_char(reg_date, 'yyyy-MM-dd hh24:mi:ss')as reg_date, \r\n" + 
				" to_char(update_date, 'yyyy-MM-dd hh24:mi:ss')as update_date,\r\n" + 
				" to_char(last_login_date, 'yyyy-MM-dd hh24:mi:ss')as last_login_date,\r\n" + 
				" to_char(exit_date, 'yyyy-MM-dd hh24:mi:ss')as exit_date ,\r\n" + 
				" memberlevel, password_len\r\n" + 
				" from bike_김진욱_member\r\n" + 
				" where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String name            = rs.getString("name");
				String password        = rs.getString("password");
				String area            = rs.getString("area");
				String address         = rs.getString("address");
				String mobile_1        = rs.getString("mobile_1");
				String mobile_2        = rs.getString("mobile_2");
				String mobile_3        = rs.getString("mobile_3");
				String gender          = rs.getString("gender");
				String hobby_travel    = rs.getString("hobby_travel");
				String hobby_reading   = rs.getString("hobby_reading");
				String hobby_sports    = rs.getString("hobby_sports");
				String reg_date        = rs.getString("reg_date");
				String update_date 	   = rs.getString("update_date");
				String last_login_date = rs.getString("last_login_date");
				String exit_date       = rs.getString("exit_date");
				String memberLevel     = rs.getString("memberlevel");
				int password_len       = rs.getInt("password_len");
				
				dto = new MemberDto(id, name, password, area, address, mobile_1, mobile_2, mobile_3, gender, hobby_travel, hobby_reading, hobby_sports, reg_date, update_date, last_login_date, exit_date, memberLevel, password_len);
				
			}
			
		}catch(Exception e) {
			System.out.println("getMemberInfo() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return dto;
	}
	
	// 정보수정 비밀번호 확인
	public int getCheckPassword(String id, String pw) {
		int count = 0;
		String query = "select count(*) as count\r\n" + 
				" from bike_김진욱_member\r\n" + 
				" where id = '"+id+"'\r\n" + 
				" and password = '"+pw+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		}catch(Exception e) {
			System.out.println("getCheckPassword() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return count;
	}
	
	//회원정보수정
	public int memberUpdate(MemberDto dto) {
		int result = 0;
		String query = "update bike_김진욱_member\r\n" + 
				" set name='"+dto.getName()+"', area='"+dto.getArea()+"', address='"+dto.getAddress()+"', mobile_1='"+dto.getMobile_1()+"', mobile_2='"+dto.getMobile_2()+"',\r\n" + 
				" mobile_3='"+dto.getMobile_3()+"', gender='"+dto.getGender()+"', hobby_travel='"+dto.getHobby_travel()+"', hobby_reading='"+dto.getHobby_reading()+"', hobby_sports='"+dto.getHobby_sports()+"',\r\n" + 
				" update_date=to_date('"+dto.getUpdate_date()+"','yyyy-MM-dd hh24:mi:ss')\r\n" + 
				" where id = '"+dto.getId()+"'";
		System.out.println(query);
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result= ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("memberUpdate() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	// 비밀번호 찾기 확인용
	public String getCheckMember(String id, String mobile_1, String mobile_2, String mobile_3) {
		String name ="";
		String query="select name\r\n" + 
				" from bike_김진욱_member\r\n" + 
				" where id = '"+id+"'\r\n" + 
				" and mobile_1='"+mobile_1+"'\r\n" + 
				" and mobile_2='"+mobile_2+"'\r\n" + 
				" and mobile_3='"+mobile_3+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				name = rs.getString("name");
			}
		}catch(Exception e){
			System.out.println("getCheckMember() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return name;
	}
	
	// 비밀번호 초기화(메일발송), 비밀번호 수정
	public int setMemberPassword(String id, String newPassword, int newPasswordLength) {
		int result = 0;
		String query = "update bike_김진욱_member\r\n" + 
				" set password = '"+newPassword+"',\r\n" + 
				" password_len="+newPasswordLength+" "+
				" where id = '"+id+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("setMemberPassword() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	// 회원탈퇴
	public int setMemberExit(String id, String exitDate) {
		int result = 0;
		String query ="update bike_김진욱_member\r\n" + 
				" set exit_date = to_date('"+exitDate+"','yyyy-MM-dd hh24:mi:ss')\r\n" + 
				" where id = '"+id+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("setMemberExit() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	// 새로운 비밀번호 생성
	public String getNewPassword(int pwLength) {
        StringBuffer temp =new StringBuffer();
        Random rnd = new Random();
        
        for(int i=0;i<pwLength;i++)
        {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
            case 0:
                // a-z
                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                break;
            case 1:
                // A-Z
                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                break;
            case 2:
                // 0-9
                temp.append((rnd.nextInt(10)));
                break;
            }
//            System.out.println("pw :"+temp.toString());	
        }
        return temp.toString();		
	}
	
	// 암호화
    public String encryptSHA256(String value) throws NoSuchAlgorithmException{
        String encryptData ="";
         
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(value.getBytes());
 
        byte[] digest = sha.digest();
        for (int i=0; i<digest.length; i++) {
            encryptData += Integer.toHexString(digest[i] &0xFF).toUpperCase();
        }
         
        return encryptData;
    }



	
    
}
