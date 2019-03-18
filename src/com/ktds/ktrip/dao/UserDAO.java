package com.ktds.ktrip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.ktrip.domain.UserVO;
import com.ktds.ktrip.jdbc.DBCon;
import com.ktds.ktrip.util.SHA256;



public class UserDAO {

	/*
	 * 회원가입 메소드
	 * 
	 * 회원가입 창에서 사용자가 입력한 정보를 DB에 저장하는 메소드 사용자가 입력한 ID가 DB에 존재 시 회원가입 실패
	 * 
	 * @author SongJiHyeon
	 * 
	 * @param vo 사용자의 정보를 담는 클래스 UserVO 객체
	 * 
	 * @return 성공 시 0 반환, 실패 시 -1 반환
	 */
	@SuppressWarnings("resource")
	public int insertUser(UserVO vo, String picturePath) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBCon.connect();

			// id 중복검사
			String id = vo.getId();
			String idSelectSql = "select id from user where id = ?";
			pstmt = conn.prepareStatement(idSelectSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			String existID = "";

			if (rs.next()) {
				existID = rs.getString(1);
			}

			/*
			 * id가 존재하지 않는 경우 -1 반환
			 */
			if (existID.equals(vo.getId())) {
				System.out.println("회원가입 실패: 아이디 중복");
				return -1;
			}

			String insertSql = "INSERT INTO user(id,pwd,name,phone_num,email,sex,nationality,residential_country,photo) VALUES (?, ?, ?, ?, ? ,? ,? ,?, ?);";
			pstmt = conn.prepareStatement(insertSql);

			pstmt.setString(1, vo.getId());
			pstmt.setString(2, SHA256.getSHA256(vo.getPwd()));
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone_num());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getSex());
			pstmt.setString(7, vo.getNational());
			pstmt.setString(8, vo.getResidential_contry());
			pstmt.setString(9, picturePath);

			pstmt.executeUpdate();
			System.out.println("회원가입 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBCon.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/*
	 * 로그인 메소드
	 * 
	 * 로그인창에서 사용자가 입력한 정보를 확인 후 로그인 여부를 결정
	 * 
	 * @author SongJiHyeon
	 * 
	 * @param vo 사용자의 정보를 담는 클래스 UserVO 객체
	 * 
	 * @return 일반 유저 로그인 시 UserVo의 type에 1을 할당해 반환, 가이드 유저 로그인 시 UserVo의 type에 2을 할당해 반환, 실패 시 -1 반환
	 */
	public UserVO login(UserVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBCon.connect();

			/*
			 * login.jsp form에서 넘어온 ID,password
			 */
			String id = vo.getId();
			String password = SHA256.getSHA256(vo.getPwd());

			// id가 존재하는 id인지 검사
			String idSelectSql = "select id from user where id = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(idSelectSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			String existID = null;

			if (rs.next()) {
				existID = rs.getString(1);
			}

			/*
			 * id가 존재하지 않는 경우 result attribute에 idErr로 setting 후 jsp 페이지로 이동
			 */
			if (existID == null) {
				System.out.println("로그인 실패: 존재하지 않는 ID");
				return null;
			}

			/*
			 * id가 존재하는 경우 password와 id가 일치하는 검사
			 */
			String selectSql = "select pwd from user where id = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(selectSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			String checkpwd = null;

			if (rs.next()) {
				checkpwd = rs.getString("pwd");
			}

			// password null exception 처리
			if (checkpwd == null) {
				System.out.println("로그인 실패: 비밀번호 입력하지 않음");
				return null;
			}

			System.out.println("폼에서 넘어온 id " + id);
			System.out.println("폼에서 넘어온 pwd " + password);
			System.out.println("db에서 넘어온 id " + existID);
			System.out.println("db에서 넘어온 pwd " + checkpwd);

			/*
			 * 패스워드가 일치하면 login result attribute를 success로 setting 하고 jsp 페이지로 이동
			 */
			if (checkpwd.equals(password)) {
				String user_id_selectSql = "select user_id, type from user where id = ?";
				pstmt = (PreparedStatement) conn.prepareStatement(user_id_selectSql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int type = rs.getInt("type");
					System.out.println("숫자 아이디 "+rs.getInt("user_id"));
					if (type == 1) {
						vo.setNum_id(rs.getInt("user_id"));
						vo.setType(1);
					} else if (type == 2) {
						vo.setNum_id(rs.getInt("user_id"));
						vo.setType(2);
					}
				}
				System.out.println("로그인 성공");
				return vo;
			} else {
				// 패스워드가 일치하지 않는경우
				// result attribute를 passwdErr로 setting 후 jsp 페이지로 이동
				System.out.println("id와 pwd불일치");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBCon.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	/*
	 * 회원 정보 조회 메소드
	 * 
	 * 사용자가 회원가입 시 입력한 회원 정보(아이디, 비밀번호, 이메일, 이름, 성별, 전화번호, 국적, 거주국가)를 확인
	 * 
	 * @author SongJiHyeon
	 * 
	 * @param id 사용자 아이디
	 * 
	 * @return UerVO 객체 vo
	 */
	public UserVO userinfo(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO vo = new UserVO();

		try {
			conn = DBCon.connect();

			String selectSql = "select * from user where user_id = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(selectSql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo.setNum_id(rs.getInt("user_id"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setEmail(rs.getString("email"));
				vo.setName(rs.getString("name"));
				vo.setSex(rs.getString("sex"));
				vo.setPhone_num(rs.getString("phone_num"));
				vo.setNational(rs.getString("nationality"));
				vo.setResidential_contry(rs.getString("residential_country"));
			}
			System.out.println("유저정보보기");
			vo.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBCon.close(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	/*
	 * 회원 정보 변경 메소드
	 * 
	 * 사용자가 자신의 비밀번호, 전화번호, 거주국가를 수정
	 * 
	 * @author SongJiHyeon
	 * 
	 * @param id 사용자 아이디
	 * 
	 * @param pwd 사용자 비밀번호
	 * 
	 * @param phone_num 사용자 전화번호
	 * 
	 * @param residential_country 사용자 거주 국가
	 * 
	 * @return void
	 */
	public void updateUser(int id, String pwd, String phone_num, String residential_country) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBCon.connect();

			String updateSql = "update user set pwd=?,phone_num=?,residential_country=? where user_id = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(updateSql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, phone_num);
			pstmt.setString(3, residential_country);
			pstmt.setInt(4, id);

			pstmt.executeUpdate();

			System.out.println("회원정보 수정 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBCon.close(pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * 회원 가이드 등록 신청 메소드
	 * 
	 * 사용자가 가이드로 등록하기 위해 정보를 입력 승인이 되면 DB에서 auth가 변경
	 * 
	 * @author SongJiHyeon
	 * 
	 * @param user_id 사용자 아이디
	 * 
	 * @param second_lang 가능한 제 2 외국어
	 * 
	 * @param stay_duration 거주 기간
	 * 
	 * @param introduction 가이드 자기 소개
	 * 
	 * @param portfolio DB에 저장할 서버에 존재하는 가이드 포트폴리오 경로
	 * 
	 * @param id_document DB에 저장할 서버에 존재하는 가이드 증빙자료 경로
	 * 
	 * @return 성공 시 0 반환, 같은 사용자가 등록하려는 경우 실패 -1 반환
	 */
	public int registerGuide(int user_id, String second_lang, String stay_duration, String introduction,
			String portfolio, String id_document) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBCon.connect();

			String guideinsertSql = "insert into guide_reg (user_id, second_lang, stay_duration, introduction, portfolio, id_document)"
					+ "values (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(guideinsertSql);
			pstmt.setInt(1, user_id);
			pstmt.setString(2, second_lang);
			pstmt.setString(3, stay_duration);
			pstmt.setString(4, introduction);
			pstmt.setString(5, portfolio);
			pstmt.setString(6, id_document);

			pstmt.executeUpdate();

			System.out.println("가이드 신청 완료");
		}catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				DBCon.close(pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
