package info.creidea.repository;

import info.creidea.domain.AuthUser;

public interface AbsenceRegisterAble {
    /**
     *
     * @param user 登録ユーザ
     * @param subjectID 科目ID
     * @param date 登録する日付
     * @param absence 欠課時数
     * @param late 遅刻の有無
     */
    void register(AuthUser user, String subjectID, String date, int absence, boolean late);
}
