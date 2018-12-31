package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GuestDAO {
	public static GuestDAO instance;
	public static GuestDAO getInstance() {
		if(instance == null) {
			instance = new GuestDAO();
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
	public void guestInsert(GuestDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "insert into guestbook values(guestbook_seq.nextval,?,?,?,sysdate,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getContent());
			ps.setString(3, dto.getGrade());
			ps.setString(4, dto.getIpaddr());
			ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, ps);
		}
		
	}
	//전체보기
//	public ArrayList<GuestDTO> guestList() {
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		ArrayList<GuestDTO> arr = new ArrayList<>();
//		
//		try {
//			con = getConnection();
//			st = con.createStatement();
//			String sql = "select * from guestbook";
//			rs = st.executeQuery(sql);
//			while(rs.next()) {
//				GuestDTO dto = new GuestDTO();
//				dto.setNum(rs.getInt("num"));
//				dto.setName(rs.getString("name"));
//				dto.setContent(rs.getString("content"));
//				dto.setGrade(rs.getString("grade"));
//				dto.setCreated(rs.getString("created"));
//				dto.setIpaddr(rs.getString("ipaddr"));
//				arr.add(dto);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			CloseOn(con, st, rs);
//		}
//		return arr;
//	}
	//전체보기,페이징
	public ArrayList<GuestDTO> guestList(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<GuestDTO> arr = new ArrayList<>();
		
		try {
			con = getConnection();
			String sql = "select * from"
					+ " (select rownum rn, aa.* from"
					+ " (select * from guestbook order by num desc)aa) where rn >=? and rn <=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, endRow);
			rs = ps.executeQuery();
			while(rs.next()) {
				GuestDTO dto = new GuestDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setContent(rs.getString("content"));
				dto.setGrade(rs.getString("grade"));
				dto.setCreated(rs.getString("created"));
				dto.setIpaddr(rs.getString("ipaddr"));
				arr.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, ps, rs);
		}
		return arr;
	}
	
	//검색 보기
		public ArrayList<GuestDTO> guestList(String field,String word,int startRow, int endRow) {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			ArrayList<GuestDTO> arr = new ArrayList<>();
			
			try {
				con = getConnection();
				String sql = "select * from"
						+ " (select rownum rn, aa.* from"
						+ " (select * from guestbook where "+field+" like '%"+word+"%' order by num desc)aa) where rn >=? and rn <=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, startRow);
				ps.setInt(2, endRow);
				rs = ps.executeQuery();
				while(rs.next()) {
					GuestDTO dto = new GuestDTO();
					dto.setNum(rs.getInt("num"));
					dto.setName(rs.getString("name"));
					dto.setContent(rs.getString("content"));
					dto.setGrade(rs.getString("grade"));
					dto.setCreated(rs.getString("created"));
					dto.setIpaddr(rs.getString("ipaddr"));
					arr.add(dto);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				CloseOn(con, ps, rs);
			}
			return arr;
		}
	
	//총게시물수
	public int guestCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select count(*) cnt from guestbook";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, st, rs);
		}
		return count;
	}
	
	//검색게시물수
		public int guestCount(String field, String word) {
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			int count = 0;
			
			try {
				con = getConnection();
				st = con.createStatement();
				String sql = "select count(*) cnt from guestbook where "+field+" like '%"+word+"%'";
				rs = st.executeQuery(sql);
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				CloseOn(con, st, rs);
			}
			return count;
		}
	
	//상세보기
	public GuestDTO guestView(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		GuestDTO dto = null;
	
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from guestbook where num ="+num;
			rs  = st.executeQuery(sql);
			if(rs.next()) {
				dto = new GuestDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setContent(rs.getString("content"));
				dto.setGrade(rs.getString("grade"));
				dto.setCreated(rs.getString("created"));
				dto.setIpaddr(rs.getString("ipaddr"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, st, rs);
		}
		return dto;
	}
	//로그인  member db 사용
	public int guestLogin(String id, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from member where id ='"+id+"'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				if(rs.getString("pass").equals(pwd)) {
					return 1;
				}
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, st, rs);
		}
		return -1;
	}
	//삭제
	public void guestDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "delete from guestbook where num = "+num;
			st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, st);
		}
	}
	//member db 에서 id,name, num 가져옴
	public MemberDTO getMember(String id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MemberDTO mdto = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from member where id = '"+id+"'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				mdto = new MemberDTO();
				mdto.setId(id);
				mdto.setName(rs.getString("name"));
				mdto.setNum(rs.getInt("num"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseOn(con, st, rs);
		}
		return mdto;
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
