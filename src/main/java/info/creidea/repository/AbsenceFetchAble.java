package info.creidea.repository;

import info.creidea.domain.AuthUser;
import info.creidea.domain.PersonalSubject;
import info.creidea.domain.SubjectInfo;

import java.sql.SQLException;
import java.util.List;

public interface AbsenceFetchAble {
    List<PersonalSubject> fetch(List<SubjectInfo> subjects, AuthUser user) throws SQLException;
}
