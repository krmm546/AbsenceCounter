package info.creidea.domain;

public record AuthUser(String id) {
    public String 学籍番号() {
        return id;
    }
}
