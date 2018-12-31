package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	public static MemberDAO instance;
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	//db연결
	public Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
		return ds.getConnection();
	}
	//추가
	public void MemberSignUp(MemberDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "insert into member values(member_seq.nextval,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPass());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getAge());
			ps.setString(5, dto.getGender());
			ps.setString(6, dto.getEmail());
			ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con,ps);
		}
		
	}
	//전체보기
	public ArrayList<MemberDTO> memberList(){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> arr = new ArrayList<>();
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from member";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setAge(rs.getString("age"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				arr.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, st, rs);
		}
		return arr;
	}
	//로그인
	public HashMap<String, String> memberLogin(String id, String pass) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		HashMap<String, String> hm = new HashMap<>();
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from member where id = '"+id+"'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				if(rs.getString("pass").equals(pass)) {
					hm.put("id", rs.getString("id"));
					hm.put("pass", rs.getString("pass"));
					hm.put("name", rs.getString("name"));
					return hm;
				}
				hm.put("id", rs.getString("id"));
				hm.put("pass", rs.getString("pass"));
				return hm;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, st, rs);
		}
		return hm;
	}
	//상세보기
	public MemberDTO memberInfo(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MemberDTO dto = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from member where num ="+num;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setAge(rs.getString("age"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, st, rs);
		}
		return dto;
	}
	//수정
	public void memberUpdate(MemberDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "update member set id=?,name=?,age=?,gender=?,email=? where num = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getAge());
			ps.setString(4, dto.getGender());
			ps.setString(5, dto.getEmail());
			ps.setInt(6, dto.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, ps);
		}
	}
	//삭제
	public void memberDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con  = getConnection();
			st = con.createStatement();
			String sql = "delete from member where num ="+num;
			st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, st);
		}
		
	}
	
	//닫는함수
		public void CloseOn(Connection con, PreparedStatement ps ) {
			try {
				if(con!=null) {con.close();}
				if(ps!=null) {ps.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void CloseOn(Connection con, Statement st ) {
			try {
				if(con!=null) {con.close();}
				if(st!=null) {st.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void CloseOn(Connection con, Statement st , ResultSet rs) {
			try {
				if(con!=null) {con.close();}
				if(st!=null) {st.close();}
				if(rs!=null) {rs.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
