package info.creidea.repository;

public interface Authenticable {
    /**
     * @return ユーザ情報が合致していたらtrue, していなければfalseを返す
     */
    boolean 認証(String id, String password);
}
