package info.creidea.presentation;

import info.creidea.domain.PersonalSubject;
import java.util.HashMap;
import java.util.Map;

public record PersonalSubjectContent(PersonalSubject subject) {
    public Map<String, Object> model() {
        final var model = new HashMap<String, Object>();
        model.put("name", subject.科目名());
        model.put("absence", subject.欠課時数());
        model.put("maxAbsence", subject.最大欠課時数());
        model.put("late", subject.遅刻回数());
        return model;
    }
}
