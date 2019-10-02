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

	private String userName;
	private Object ps;

	// ログインID とパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId, String password) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
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

	//取得したログインIDをDBで検索し同じログインIDがある場合はデータ登録しない。
	public boolean Determine(String loginId) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=?";
		boolean result = true;
		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				//
				result = false;
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

	//会員情報をDBに登録
	public boolean registerUser(
			String loginId, String password, String userName, String icon, String profile) {
		Connection conn = null;// データベース接続情報
		PreparedStatement pstmt = null;// SQL 管理情報
		boolean result = false;

		try {
			conn = getConnection();// データベース接続情報取得

			// INSERT 文の登録と実行
			String sql = "INSERT INTO users(loginId,password,userName, icon, profile) VALUES(?, ?, ?, ?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			pstmt.setString(3, userName);
			pstmt.setString(4, icon);
			pstmt.setString(5, profile);

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

	// userNameリストのゲッター
	public ArrayList<UserDTO> getUserList(String userName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<UserDTO> searchlist = new ArrayList<UserDTO>();

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			String searchWord = userName;

			// SELECT 文の実行
			String sql = "SELECT * FROM users WHERE userName LIKE '" + searchWord + "%' ";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery(sql);

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
				UserDTO user = new UserDTO();
				user.setUserName(rset.getString(4));

				// 書き込み内容をリストに追加
				searchlist.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return searchlist;
	}

	// userNameを受け取り、登録ユーザ一覧に一致したものがあるか検索
	public UserDTO getChangeUser(String userName) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE userName=?";
		UserDTO user = null; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, userName);
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

	//会員情報の変更
	public boolean ChangeUserInformation(
			String loginId, String password, String userName, String icon, String profile) {
		Connection conn = null;// データベース接続情報
		PreparedStatement pstmt = null;// SQL 管理情報
		boolean result = false;
		String searchWord = loginId;//サーチキーワードをloginIdに

		try {
			conn = getConnection();// データベース接続情報取得

			// UPDATE文の登録と実行
			String sql = "UPDATE users SET loginId=?,password=?,userName=?,icon=?,profile=? WHERE loginId='"
					+ searchWord + "'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			pstmt.setString(3, userName);
			pstmt.setString(4, icon);
			pstmt.setString(5, profile);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// UPDATE文の実行結果が１なら登録成功
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

	// iconリストのゲッター
	public ArrayList<UserDTO> getUserList2(String icon) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<UserDTO> searchlist = new ArrayList<UserDTO>();

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			String searchWord = icon;

			// SELECT 文の実行
			String sql = "SELECT * FROM users WHERE icon='" + searchWord + "'";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery(sql);

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
				UserDTO user = new UserDTO();
				user.setUserName(rset.getString(4));

				// 書き込み内容をリストに追加
				searchlist.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return searchlist;
	}

	// profileリストのゲッター
	public ArrayList<UserDTO> getUserList3(String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<UserDTO> searchlist = new ArrayList<UserDTO>();

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			String searchWord = profile;

			// SELECT 文の実行
			String sql = "SELECT * FROM users WHERE profile LIKE '" + searchWord + "%' ";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery(sql);

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
				UserDTO user = new UserDTO();
				user.setUserName(rset.getString(4));

				// 書き込み内容をリストに追加
				searchlist.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return searchlist;
	}

	public boolean Delete(String loginId) {
		Connection conn = null;// データベース接続情報
		PreparedStatement pstmt = null;// SQL 管理情報
		boolean result = false;
		String searchWord = loginId;//サーチキーワードをloginIdに

		try {
			conn = getConnection();// データベース接続情報取得

			// UPDATE文の登録と実行
			String sql = "DELETE FROM users WHERE loginId='" + searchWord + "'";
			pstmt = conn.prepareStatement(sql);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// UPDATE文の実行結果が１なら登録成功
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
	// userNameを受け取り、登録ユーザ一覧に一致したものがあるか検索(削除時)
		public UserDTO getChangeUser3(String userName) {
			Connection conn = null; // データベース接続情報
			PreparedStatement pstmt = null; // SQL 管理情報
			ResultSet rset = null; // 検索結果

			String sql = "SELECT * FROM users WHERE userName=?";
			UserDTO user3 = null; // 登録ユーザ情報

			try {
				// データベース接続情報取得
				conn = getConnection();

				// SELECT 文の登録と実行
				pstmt = conn.prepareStatement(sql); // SELECT 構文登録
				pstmt.setString(1, userName);
				rset = pstmt.executeQuery();

				// 検索結果があれば
				if (rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user3 = new UserDTO();
					user3.setLoginId(rset.getString(2));
					user3.setPassword(rset.getString(3));
					user3.setUserName(rset.getString(4));
					user3.setIcon(rset.getString(5));
					user3.setProfile(rset.getString(6));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// データベース切断処理
				close(rset);
				close(pstmt);
				close(conn);
			}

			return user3;
		}
		// userNameを受け取り、登録ユーザ一覧に一致したものがあるか検索(登録情報変更時)
				public UserDTO getChangeUser2(String userName) {
					Connection conn = null; // データベース接続情報
					PreparedStatement pstmt = null; // SQL 管理情報
					ResultSet rset = null; // 検索結果

					String sql = "SELECT * FROM users WHERE userName=?";
					UserDTO user2 = null; // 登録ユーザ情報

					try {
						// データベース接続情報取得
						conn = getConnection();

						// SELECT 文の登録と実行
						pstmt = conn.prepareStatement(sql); // SELECT 構文登録
						pstmt.setString(1, userName);
						rset = pstmt.executeQuery();

						// 検索結果があれば
						if (rset.next()) {
							// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
							user2 = new UserDTO();
							user2.setLoginId(rset.getString(2));
							user2.setPassword(rset.getString(3));
							user2.setUserName(rset.getString(4));
							user2.setIcon(rset.getString(5));
							user2.setProfile(rset.getString(6));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						// データベース切断処理
						close(rset);
						close(pstmt);
						close(conn);
					}

					return user2;
				}

}