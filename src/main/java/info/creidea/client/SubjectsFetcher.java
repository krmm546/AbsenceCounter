package info.creidea.client;

import info.creidea.client.http.HTTPFetcher;
import info.creidea.client.parse.HTMLParser;
import info.creidea.domain.SubjectInfo;

import java.util.List;

public class SubjectsFetcher implements SubjectsFetchAble {
    @Override
    public List<SubjectInfo> fetch(int 学年, int 学期) {
        final var httpFetcher = new HTTPFetcher();
        final var html = httpFetcher.fetch("https://syllabus.kosen-k.go.jp/Pages/PublicSubjects?school_id=28&department_id=15&year=2022&lang=ja");
        final var parser = new HTMLParser();
        final var subjects = parser.parse(html.orElseThrow());
        return subjects.stream()
                .filter((subject) -> subject.is学年(4))
                .filter((subject) -> subject.is学期(学期))
                .toList();
    }
}
