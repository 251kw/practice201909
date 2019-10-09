package dto;

// ユーザ情報を保持するクラス
public class UserDTO {
	private String loginId;		// ログインID
	private String password;	// パスワード
	private String userName;	// ユーザ名
	private String icon;		// ユーザアイコン
	private String profile;		// プロフィール
    private String selected;
    private String selected1;
    private String selected2;
    private String checked;

	public UserDTO(){

	}

	public UserDTO(String loginId, String password, String userName, String icon, String profile, String selected, String selected1,String selected2,String checked) {
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
		this.selected = selected;
		this.selected1 = selected;
		this.selected2 = selected;
		this.checked = checked;
		}

	// 各メンバ変数の getter および setter
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getSelected1() {
		return selected1;
	}

	public void setSelected1(String selected1) {
		this.selected1 = selected1;
	}

	public String getSelected2() {
		return selected2;
	}

	public void setSelected2(String selected2) {
		this.selected2 = selected2;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;

	}
}
