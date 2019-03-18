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
	 * ȸ������ �޼ҵ�
	 * 
	 * ȸ������ â���� ����ڰ� �Է��� ������ DB�� �����ϴ� �޼ҵ� ����ڰ� �Է��� ID�� DB�� ���� �� ȸ������ ����
	 * 
	 * @author SongJiHyeon
	 * 
	 * @param vo ������� ������ ��� Ŭ���� UserVO ��ü
	 * 
	 * @return ���� �� 0 ��ȯ, ���� �� -1 ��ȯ
	 */
	@SuppressWarnings("resource")
	public int insertUser(UserVO vo, String picturePath) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBCon.connect();

			// id �ߺ��˻�
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
			 * id�� �������� �ʴ� ��� -1 ��ȯ
			 */
			if (existID.equals(vo.getId())) {
				System.out.println("ȸ������ ����: ���̵� �ߺ�");
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
			System.out.println("ȸ������ �Ϸ�");
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
	 * �α��� �޼ҵ�
	 * 
	 * �α���â���� ����ڰ� �Է��� ������ Ȯ�� �� �α��� ���θ� ����
	 * 
	 * @author SongJiHyeon
	 * 
	 * @param vo ������� ������ ��� Ŭ���� UserVO ��ü
	 * 
	 * @return �Ϲ� ���� �α��� �� UserVo�� type�� 1�� �Ҵ��� ��ȯ, ���̵� ���� �α��� �� UserVo�� type�� 2�� �Ҵ��� ��ȯ, ���� �� -1 ��ȯ
	 */
	public UserVO login(UserVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBCon.connect();

			/*
			 * login.jsp form���� �Ѿ�� ID,password
			 */
			String id = vo.getId();
			String password = SHA256.getSHA256(vo.getPwd());

			// id�� �����ϴ� id���� �˻�
			String idSelectSql = "select id from user where id = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(idSelectSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			String existID = null;

			if (rs.next()) {
				existID = rs.getString(1);
			}

			/*
			 * id�� �������� �ʴ� ��� result attribute�� idErr�� setting �� jsp �������� �̵�
			 */
			if (existID == null) {
				System.out.println("�α��� ����: �������� �ʴ� ID");
				return null;
			}

			/*
			 * id�� �����ϴ� ��� password�� id�� ��ġ�ϴ� �˻�
			 */
			String selectSql = "select pwd from user where id = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(selectSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			String checkpwd = null;

			if (rs.next()) {
				checkpwd = rs.getString("pwd");
			}

			// password null exception ó��
			if (checkpwd == null) {
				System.out.println("�α��� ����: ��й�ȣ �Է����� ����");
				return null;
			}

			System.out.println("������ �Ѿ�� id " + id);
			System.out.println("������ �Ѿ�� pwd " + password);
			System.out.println("db���� �Ѿ�� id " + existID);
			System.out.println("db���� �Ѿ�� pwd " + checkpwd);

			/*
			 * �н����尡 ��ġ�ϸ� login result attribute�� success�� setting �ϰ� jsp �������� �̵�
			 */
			if (checkpwd.equals(password)) {
				String user_id_selectSql = "select user_id, type from user where id = ?";
				pstmt = (PreparedStatement) conn.prepareStatement(user_id_selectSql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int type = rs.getInt("type");
					System.out.println("���� ���̵� "+rs.getInt("user_id"));
					if (type == 1) {
						vo.setNum_id(rs.getInt("user_id"));
						vo.setType(1);
					} else if (type == 2) {
						vo.setNum_id(rs.getInt("user_id"));
						vo.setType(2);
					}
				}
				System.out.println("�α��� ����");
				return vo;
			} else {
				// �н����尡 ��ġ���� �ʴ°��
				// result attribute�� passwdErr�� setting �� jsp �������� �̵�
				System.out.println("id�� pwd����ġ");
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
	 * ȸ�� ���� ��ȸ �޼ҵ�
	 * 
	 * ����ڰ� ȸ������ �� �Է��� ȸ�� ����(���̵�, ��й�ȣ, �̸���, �̸�, ����, ��ȭ��ȣ, ����, ���ֱ���)�� Ȯ��
	 * 
	 * @author SongJiHyeon
	 * 
	 * @param id ����� ���̵�
	 * 
	 * @return UerVO ��ü vo
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
			System.out.println("������������");
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
	 * ȸ�� ���� ���� �޼ҵ�
	 * 
	 * ����ڰ� �ڽ��� ��й�ȣ, ��ȭ��ȣ, ���ֱ����� ����
	 * 
	 * @author SongJiHyeon
	 * 
	 * @param id ����� ���̵�
	 * 
	 * @param pwd ����� ��й�ȣ
	 * 
	 * @param phone_num ����� ��ȭ��ȣ
	 * 
	 * @param residential_country ����� ���� ����
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

			System.out.println("ȸ������ ���� �Ϸ�");
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
	 * ȸ�� ���̵� ��� ��û �޼ҵ�
	 * 
	 * ����ڰ� ���̵�� ����ϱ� ���� ������ �Է� ������ �Ǹ� DB���� auth�� ����
	 * 
	 * @author SongJiHyeon
	 * 
	 * @param user_id ����� ���̵�
	 * 
	 * @param second_lang ������ �� 2 �ܱ���
	 * 
	 * @param stay_duration ���� �Ⱓ
	 * 
	 * @param introduction ���̵� �ڱ� �Ұ�
	 * 
	 * @param portfolio DB�� ������ ������ �����ϴ� ���̵� ��Ʈ������ ���
	 * 
	 * @param id_document DB�� ������ ������ �����ϴ� ���̵� �����ڷ� ���
	 * 
	 * @return ���� �� 0 ��ȯ, ���� ����ڰ� ����Ϸ��� ��� ���� -1 ��ȯ
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

			System.out.println("���̵� ��û �Ϸ�");
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
