package info.creidea.client;

import info.creidea.client.http.HTTPFetcher;
import info.creidea.client.parse.HTMLParser;
import info.creidea.domain.SubjectInfo;
import java.util.List;

public class SubjectsFetcher implements SubjectsFetchAble {

    private List<SubjectInfo> subjects = null;
    @Override
    public List<SubjectInfo> fetch(int 学年, int 学期) {
        return subjects().stream()
                .filter((subject) -> subject.is学年(4))
                .filter((subject) -> subject.is学期(学期))
                .toList();
    }

    @Override
    public SubjectInfo fetch(int 学年, int 学期, String 科目ID) {
        return fetch(学年, 学期).stream()
                .filter(subject -> subject.is科目ID(科目ID))
                .findFirst()
                .orElseThrow();
    }

    private List<SubjectInfo> subjects() {
        if (subjects == null) {
            subjects = fetchFromWeb();
            System.out.println(subjects);
        }
        return subjects;
    }

    private List<SubjectInfo> fetchFromWeb() {
        final var httpFetcher = new HTTPFetcher();
        final var html = httpFetcher.fetch("https://syllabus.kosen-k.go.jp/Pages/PublicSubjects?school_id=28&department_id=15&year=2022&lang=ja");
        final var parser = new HTMLParser();
        return parser.parse(html.orElseThrow());
    }
}
