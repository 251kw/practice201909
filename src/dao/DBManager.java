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

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null; // 登録ユーザ情報

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);

			try(ResultSet rset = pstmt.executeQuery();){

				// 検索結果があれば
				if (rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setUserId(rset.getString(1));
					user.setLoginId(rset.getString(2));
					user.setPassword(rset.getString(3));
					user.setUserName(rset.getString(4));
					user.setIcon(rset.getString(5));
					user.setProfile(rset.getString(6));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	//ユーザーIDから最新のログイン情報を取り出す
	public UserDTO getLoginUserAgain(String userId) {

		String sql = "SELECT * FROM users WHERE userId=?";
		UserDTO user = null; // 登録ユーザ情報

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, userId);

			try(ResultSet rset = pstmt.executeQuery();){
				// 検索結果があれば
				if (rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setUserId(rset.getString(1));
					user.setLoginId(rset.getString(2));
					user.setPassword(rset.getString(3));
					user.setUserName(rset.getString(4));
					user.setIcon(rset.getString(5));
					user.setProfile(rset.getString(6));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}



	//loginIdの重複チェックメソッド
	public boolean registerCheck(String loginId) {

		boolean result = false;

		String sql = "SELECT * FROM users WHERE loginId=?";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){

			pstmt.setString(1, loginId);

			try(ResultSet rset = pstmt.executeQuery(sql);){

				// 検索結果を取得し分岐させる
				if (rset.next()) {
					//検索結果があれば重複になるのでresultにfalseを代入
					result = false;
				} else {
					//検索結果がなければ重複ではないのでresultにtrueを代入
					result = true;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 書き込み内容リストの getter
	public ArrayList<ShoutDTO> getShoutList() {

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		// SELECT 文の実行
		String sql = "SELECT * FROM shouts ORDER BY date DESC";

		try(Connection conn = getConnection(); Statement pstmt = conn.createStatement(); ResultSet rset = pstmt.executeQuery(sql);) {

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
				ShoutDTO shout = new ShoutDTO();
				shout.setShoutsId(rset.getString(1));
				shout.setUserName(rset.getString(3));
				shout.setIcon(rset.getString(4));
				shout.setDate(rset.getString(5));
				shout.setWriting(rset.getString(6));
				shout.setLoginId(rset.getString(2));

				// 書き込み内容をリストに追加
				list.add(shout);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//叫びを削除
	public void deleteShouts(String shoutsId) throws SQLException {

		//デリート文の実行
		String sql = "DELETE FROM shouts WHERE shoutsId=?";

		try (Connection conn =getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, shoutsId);

			pstmt.executeUpdate();

		}
	}


	//該当の人の叫びを全て削除
	public void deleteUserShouts(String loginId) throws SQLException {

		//デリート文の実行
		String sql = "DELETE FROM shouts WHERE loginId=?";

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, loginId);

			pstmt.executeUpdate();

		}
	}





	// ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	public boolean setWriting(String nowLoginId, String nowLoginUser, String nowLoginIcon, String writing) {

		// INSERT 文の登録と実行
		String sql = "INSERT INTO shouts(loginId, userName, icon, date, writing) VALUES(?, ?, ?, ?, ?)";

		boolean result = false;

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){

			pstmt.setString(1, nowLoginId);
			pstmt.setString(2, nowLoginUser);
			pstmt.setString(3, nowLoginIcon);
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			pstmt.setString(4, sdf.format(calender.getTime()));
			pstmt.setString(5, writing);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// INSERT 文の実行結果が１なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//ユーザー情報をDBに登録する
	public void register(String loginId, String userName, String password, String icon, String profile)
			throws SQLException {

		String sql = "INSERT INTO users(loginId, userName, password, icon, profile) VALUES(?, ?, ?, ?, ?)";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
			//インサート分の実行
			pstmt.setString(1, loginId);
			pstmt.setString(2, userName);
			pstmt.setString(3, password);
			pstmt.setString(4, icon);
			pstmt.setString(5, profile);

			pstmt.executeUpdate();

		}
	}

	//ユーザー情報の更新
	public void change(String userName, String password, String icon, String profile, String userId)
			throws SQLException {

		//アップデート文の実行
		String sql = "UPDATE users SET userName=?, password=?, icon=?, profile=? WHERE userId=?";

		try(Connection conn = getConnection(); PreparedStatement pstmt =conn.prepareStatement(sql);) {
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			pstmt.setString(3, icon);
			pstmt.setString(4, profile);
			pstmt.setString(5, userId);

			pstmt.executeUpdate();
		}
	}

	//叫びの名前とアイコンId変更
	public void changeShouts(String userName, String icon, String changeUserLoginId) throws SQLException {

		//アップデート文の実行
		String sql = "UPDATE shouts SET userName=?, icon=? WHERE loginId=?";

		try(Connection conn = getConnection(); PreparedStatement pstmt =conn.prepareStatement(sql);) {
			pstmt.setString(1, userName);
			pstmt.setString(2, icon);
			pstmt.setString(3, changeUserLoginId);

			pstmt.executeUpdate();

		}
	}



	//ユーザー情報を削除する
	public void delete(String loginId) throws SQLException {

		//デリート文の実行
		String sql = "DELETE FROM users WHERE loginId=?";

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){

			pstmt.setString(1, loginId);

			pstmt.executeUpdate();

		}
	}

	//複数ユーザーの削除
	public void multipleDelete(ArrayList<UserDTO> deleteList) throws SQLException {

		//デリート文の実行
		String sql = "DELETE FROM users WHERE loginId=?";

			try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
				for (UserDTO deleteLoginId : deleteList) {

					String delete = deleteLoginId.getLoginId();

					pstmt.setString(1, delete);

					pstmt.executeUpdate();
				}

			}
	}

	//ユーザー名をもらってDB検索→リストで返す
	public ArrayList<UserDTO> userNameSearch(String userNameSearch) {
		ResultSet rset = null; // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE userName like ?";
		UserDTO user = null; // 登録ユーザ情報

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, "%" + userNameSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}

	//ログインIdをもらってDB検索→リストで返す
	public ArrayList<UserDTO> loginIdSearch(String loginIdSearch) {

		ResultSet rset = null; // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE loginId like ?";
		UserDTO user = null; // 登録ユーザ情報

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, "%" + loginIdSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));
				searchResultList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}

	//自己紹介をもらってDB検索→リストで返す
	public ArrayList<UserDTO> profileSearch(String profileSearch) {
		ResultSet rset = null; // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE profile like ?";
		UserDTO user = null; // 登録ユーザ情報

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ) {
			pstmt.setString(1, "%" + profileSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;

	}

	public ArrayList<UserDTO> iconSearch(String iconSearch) {

		ResultSet rset = null; // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE icon=?";
		UserDTO user = null; // 登録ユーザ情報

		try (Connection conn = getConnection(); PreparedStatement pstmt =  conn.prepareStatement(sql);){

			pstmt.setString(1, iconSearch);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}



	//名前とログインIdをもらってDB検索→リストで返す
	public ArrayList<UserDTO> nameIdSearch(String userNameSearch, String loginIdSearch) {

		ResultSet rset = null; // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE userName like ? AND loginId like ?";
		UserDTO user = null; // 登録ユーザ情報

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, "%" + userNameSearch + "%");
			pstmt.setString(2, "%" + loginIdSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}

	//ログインIdと自己紹介をもらってDB検索→リストで返す
	public ArrayList<UserDTO> idProSearch(String loginIdSearch, String profileSearch) {
		ResultSet rset = null; // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE loginId like ? AND profile like ?";
		UserDTO user = null; // 登録ユーザ情報

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, "%" + loginIdSearch + "%");
			pstmt.setString(2, "%" + profileSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}

	//名前と自己紹介をもらってDB検索→リストで返す
	public ArrayList<UserDTO> nameProSearch(String userNameSearch, String profileSearch) {

		ResultSet rset = null; // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE userName like ? AND profile like ?";
		UserDTO user = null; // 登録ユーザ情報

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){

			pstmt.setString(1, "%" + userNameSearch + "%");
			pstmt.setString(2, "%" + profileSearch + "%");
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}

	//名前とアイコンをもらって検索→結果をリストで返す
	public ArrayList<UserDTO> nameIconSearch(String userNameSearch, String iconSearch) {

		ResultSet rset = null; // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE userName like ? AND icon=?";
		UserDTO user = null; // 登録ユーザ情報

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){

			pstmt.setString(1, "%" + userNameSearch + "%");
			pstmt.setString(2, iconSearch);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}

	//IDとアイコンをもらって検索→結果をリストで返す
	public ArrayList<UserDTO> idIconSearch(String loginIdSearch, String iconSearch) {

		ResultSet rset = null; // 検索結果
		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE loginId like ? AND icon=?";
		UserDTO user = null; // 登録ユーザ情報

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){

			pstmt.setString(1, "%" + loginIdSearch + "%");
			pstmt.setString(2, iconSearch);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}

	//自己紹介とアイコンをもらってDB検索→リストで返す
	public ArrayList<UserDTO> proIconSearch(String profileSearch, String iconSearch) {

		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE profile like ? AND icon=?";
		UserDTO user = null; // 登録ユーザ情報

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, "%" + profileSearch + "%");
			pstmt.setString(2, iconSearch);
			try(ResultSet rset = pstmt.executeQuery();) {

				// 検索結果があれば
				while (rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setUserName(rset.getString(4));
					user.setLoginId(rset.getString(2));

					searchResultList.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}



	//名前とIDと自己紹介をもらってDB検索→リストで返す
	public ArrayList<UserDTO> nameIdProSearch(String userNameSearch, String loginIdSearch, String profileSearch) {

		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE userName like ? AND loginId like ? AND profile like ?";
		UserDTO user = null; // 登録ユーザ情報

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, "%" + userNameSearch + "%");
			pstmt.setString(2, "%" + loginIdSearch + "%");
			pstmt.setString(3, "%" + profileSearch + "%");

			try(ResultSet rset = pstmt.executeQuery();){

				// 検索結果があれば
				while (rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setUserName(rset.getString(4));
					user.setLoginId(rset.getString(2));

					searchResultList.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}

	//名前とアイコンと自己紹介をもらってDB検索→リストで返す
	public ArrayList<UserDTO> nameIconProSearch(String userNameSearch, String iconSearch, String profileSearch) {

		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE userName like ? AND icon=? AND profile like ?";
		UserDTO user = null; // 登録ユーザ情報

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){

			pstmt.setString(1, "%" + userNameSearch + "%");
			pstmt.setString(2, iconSearch);
			pstmt.setString(3, "%" + profileSearch + "%");

			try(ResultSet rset = pstmt.executeQuery();){
				// 検索結果があれば
				while (rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setUserName(rset.getString(4));
					user.setLoginId(rset.getString(2));

					searchResultList.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}


	//名前とIDと自己紹介をもらってDB検索→リストで返す
	public ArrayList<UserDTO> idIconNameSearch(String loginIdSearch, String iconSearch, String userNameSearch) {

		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE loginId like ? AND icon=? AND userName like ?";
		UserDTO user = null; // 登録ユーザ情報

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, "%" + loginIdSearch + "%");
			pstmt.setString(2, iconSearch);
			pstmt.setString(3, "%" + userNameSearch + "%");

			try(ResultSet rset = pstmt.executeQuery();){

				// 検索結果があれば
				while (rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setUserName(rset.getString(4));
					user.setLoginId(rset.getString(2));

					searchResultList.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}


	//名前とIDと自己紹介をもらってDB検索→リストで返す
	public ArrayList<UserDTO> idProIconSearch(String loginIdSearch,  String profileSearch, String iconSearch) {

		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE loginId like ? AND profile like ? AND icon=?";
		UserDTO user = null; // 登録ユーザ情報

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, "%" + loginIdSearch + "%");
			pstmt.setString(2, "%" + profileSearch + "%");
			pstmt.setString(3, iconSearch);

			try(ResultSet rset = pstmt.executeQuery();){
				// 検索結果があれば
				while (rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setUserName(rset.getString(4));
					user.setLoginId(rset.getString(2));

					searchResultList.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}



	//名前とIDと自己紹介とアイコンをもらってDB検索→リストで返す
	public ArrayList<UserDTO> nameIdProIconSearch(String userNameSearch, String loginIdSearch, String profileSearch, String iconSearch) {

		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE userName like ? AND loginId like ? AND profile like ? AND icon=?";
		UserDTO user = null; // 登録ユーザ情報

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){

			pstmt.setString(1, "%" + userNameSearch + "%");
			pstmt.setString(2, "%" + loginIdSearch + "%");
			pstmt.setString(3, "%" + profileSearch + "%");
			pstmt.setString(4, iconSearch);

			try(ResultSet rset = pstmt.executeQuery();){
				// 検索結果があれば
				while (rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setUserName(rset.getString(4));
					user.setLoginId(rset.getString(2));

					searchResultList.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}



	//BDに入っている全件のユーザー名とログインIdデータをリストで返す
	public ArrayList<UserDTO> allSearch() {

		ArrayList<UserDTO> searchResultList = new ArrayList<>();

		String sql = "SELECT * FROM users";
		UserDTO user = null; // 登録ユーザ情報

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rset = pstmt.executeQuery();){

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setUserName(rset.getString(4));
				user.setLoginId(rset.getString(2));

				searchResultList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResultList;
	}

	//該当するユーザーの全情報をリストに入れて返す
	public ArrayList<UserDTO> getUserInformation(String loginId) {

		ArrayList<UserDTO> list = new ArrayList<>();

		String sql = "SELECT * FROM users WHERE loginId=?";
		UserDTO user = null;    // 登録ユーザ情報

		try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, loginId);

			try(ResultSet rset = pstmt.executeQuery();){

				// 検索結果があれば
				if (rset.next()) {
					// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
					user = new UserDTO();
					user.setUserId(rset.getString(1));
					user.setLoginId(rset.getString(2));
					user.setPassword(rset.getString(3));
					user.setUserName(rset.getString(4));
					user.setIcon(rset.getString(5));
					user.setProfile(rset.getString(6));

					list.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//複数のユーザーIDを受け取って、それぞれの情報をリストで返す
	public ArrayList<UserDTO> getUsersInformation(String[] deletes) {

		ArrayList<UserDTO> list = new ArrayList<>();

			String sql = "SELECT * FROM users WHERE loginId=?";
			UserDTO user = null;    // 登録ユーザ情報

			try(Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
				for (String deleteLoginId : deletes) {

					pstmt.setString(1, deleteLoginId);

					try(ResultSet rset = pstmt.executeQuery();){
						// 検索結果があれば
						if(rset.next()) {
							user = new UserDTO();
							user.setUserId(rset.getString(1));
							user.setLoginId(rset.getString(2));
							user.setPassword(rset.getString(3));
							user.setUserName(rset.getString(4));
							user.setIcon(rset.getString(5));
							user.setProfile(rset.getString(6));

							list.add(user);
						}
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return list;
	}
}
