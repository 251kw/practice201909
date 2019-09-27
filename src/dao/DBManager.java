package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.ShoutDTO;
import dto.UserDTO;

public class DBManager extends SnsDAO {

	// ログインID とパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId, String password) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return user;
	}

	//loginIdの重複チェックメソッド
	public boolean registerCheck(String loginId) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果
		boolean result = false;

		String sql = "SELECT * FROM users WHERE loginId=?";

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			// 検索結果を取得し分岐させる
			if (rset.next()) {
				//検索結果があれば重複になるのでresultにfalseを代入
				result = false;
			}else {
				//検索結果がなければ重複ではないのでresultにtrueを代入
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return result;

	}



	// 書き込み内容リストの getter
	public ArrayList<ShoutDTO> getShoutList() {
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();
			pstmt = conn.createStatement();

			// SELECT 文の実行
			String sql = "SELECT * FROM shouts ORDER BY date DESC";
			rset = pstmt.executeQuery(sql);

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
				ShoutDTO shout = new ShoutDTO();
				shout.setUserName(rset.getString(2));
				shout.setIcon(rset.getString(3));
				shout.setDate(rset.getString(4));
				shout.setWriting(rset.getString(5));

				// 書き込み内容をリストに追加
				list.add(shout);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return list;
	}

	// ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT 文の登録と実行
			String sql = "INSERT INTO shouts(userName, icon, date, writing) VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getIcon());
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			pstmt.setString(3, sdf.format(calender.getTime()));
			pstmt.setString(4, writing);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// INSERT 文の実行結果が１なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	//ユーザー情報をDBに登録する
	public void register(String loginId, String userName, String password, String icon, String profile) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
				conn = getConnection();
				//インサート分の実行
				String sql = "INSERT INTO users(loginId, userName, password, icon, profile) VALUES(?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loginId);
				pstmt.setString(2, userName);
				pstmt.setString(3, password);
				pstmt.setString(4, icon);
				pstmt.setString(5, profile);

				pstmt.executeUpdate();

		}finally{
			// データベース切断処理
				close(conn);
				close(pstmt);

		}


	}

	public ArrayList<UserDTO> userNameSearch(String userNameSearch) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE userName like ?";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, "%" + userNameSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
				while(rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setUserName(rset.getString(4));
					user.setLoginId(rset.getString(2));

					searchResultList.add(user);
				}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return searchResultList;

	}

	public ArrayList<UserDTO> loginIdSearch(String loginIdSearch) {

		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE loginId like ?";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, "%" + loginIdSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
				while(rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setUserName(rset.getString(4));
					user.setLoginId(rset.getString(2));
					searchResultList.add(user);
				}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return searchResultList;

	}

	public ArrayList<UserDTO> profileSearch(String profileSearch) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();


		String sql = "SELECT * FROM users WHERE profile like ?";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, "%" + profileSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while(rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return searchResultList;

	}

	public ArrayList<UserDTO> nameIdSearch(String userNameSearch, String loginIdSearch) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();


		String sql = "SELECT * FROM users WHERE userName like ? AND loginId like ?";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, "%" + userNameSearch + "%");
			pstmt.setString(2, "%" + loginIdSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while(rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return searchResultList;

	}


	public ArrayList<UserDTO> idProSearch(String loginIdSearch, String profileSearch) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();


		String sql = "SELECT * FROM users WHERE loginId like ? AND profile like ?";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, "%" + loginIdSearch + "%");
			pstmt.setString(2, "%" + profileSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while(rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return searchResultList;

	}

	//名前と自己紹介をもらってDB検索→リストで返す
	public ArrayList<UserDTO> nameProSearch(String userNameSearch, String profileSearch) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();


		String sql = "SELECT * FROM users WHERE userName like ? AND profile like ?";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, "%" + userNameSearch + "%");
			pstmt.setString(2, "%" + profileSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while(rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return searchResultList;

	}

	//名前とIDと自己紹介をもらって、DB検索→リストで返す
	public ArrayList<UserDTO> nameIdProSearch(String userNameSearch, String loginIdSearch, String profileSearch) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();


		String sql = "SELECT * FROM users WHERE userName like ? AND loginId like ? AND profile like ?";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, "%" + userNameSearch + "%");
			pstmt.setString(2, "%" + loginIdSearch + "%");
			pstmt.setString(3, "%" + profileSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while(rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return searchResultList;

	}




	public ArrayList<UserDTO> allSearch() {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();


		String sql = "SELECT * FROM users";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while(rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return searchResultList;

	}

	public ArrayList<UserDTO> getUserInformation(String loginId) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果
		ArrayList<UserDTO> userInfomationList = new ArrayList<>();

		String sql = "SELECT * FROM users ";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));

				userInfomationList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return userInfomationList;

	}
}
