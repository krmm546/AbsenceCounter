package info.creidea.presentation;

import info.creidea.domain.PersonalSubject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record PersonalSubjectsContent(List<PersonalSubject> subjects) {

    public Map<String, Object> model() {
        final var personals = subjects.stream()
                .map(subject -> new HashMap<String, Object>() {{
                        put("id", subject.科目ID());
                        put("name", subject.科目名());
                        put("absence", subject.欠課時数());
                        put("maxAbsence", subject.最大欠課時数());
                        put("late", subject.遅刻回数());
                    }}
                )
                .toList();
        return new HashMap<>() {{ put("subjects", personals); }};
    }
}
