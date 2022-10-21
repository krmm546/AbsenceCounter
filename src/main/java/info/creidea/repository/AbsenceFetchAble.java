package info.creidea.repository;

import info.creidea.domain.AuthUser;
import info.creidea.domain.PersonalSubject;
import info.creidea.domain.SubjectInfo;

import java.sql.SQLException;
import java.util.List;

public interface AbsenceFetchAble {
    /**
     *
     * @param subjects 取得したい科目IDのリスト
     * @param user 取得したいユーザ
     * @return 個人データのリスト
     */
    List<PersonalSubject> fetch(List<SubjectInfo> subjects, AuthUser user) throws SQLException;
}
