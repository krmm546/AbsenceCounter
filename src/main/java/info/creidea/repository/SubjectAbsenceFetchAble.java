package info.creidea.repository;

import info.creidea.domain.AuthUser;
import info.creidea.domain.PersonalSubject;

public interface SubjectAbsenceFetchAble {
    PersonalSubject fetch(AuthUser user, String 科目ID);
}
