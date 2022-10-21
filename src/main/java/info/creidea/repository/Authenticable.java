package info.creidea.repository;

public interface Authenticable {
    /**
     * idとpasswordがDBに登録されたものであるかを確認する
     * @param id 学籍番号
     * @param password パスワード
     * @return 登録されていた場合にtrueを返す
     */
    boolean 認証(String id, String password);
}
