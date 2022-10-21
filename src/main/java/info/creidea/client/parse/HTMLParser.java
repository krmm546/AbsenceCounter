package info.creidea.client.parse;

import info.creidea.domain.SubjectInfo;
import org.jsoup.Jsoup;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HTMLParser {
    public List<SubjectInfo> parse(String html) {
        final var document = Jsoup.parse(html);
        final var infos = new ArrayList<SubjectInfo>();
        final var tracks = document.select("tr");
        for (final var track: tracks) {
            try {
                final var 区分 = track.select("td").get(1).text();
                if (!区分.equals("必修")) continue;
                final var 科目名 = Optional.ofNullable(track.selectFirst("td>div>span.mcc-hide")).orElseThrow().text();
                final var 科目番号= track.select("td").get(3).text();
                var 学年 = 1;
                var 前期単位 = 0;
                var 後期単位 = 0;
                for (; 学年 <= 5; 学年++) {
                    final var 学年単位 = track.select("td.c%dm".formatted(学年));
                    final var 前期 = 学年単位.get(0).text();
                    final var 後期 = 学年単位.get(2).text();
                    if (前期.equals("") && 後期.equals("")) continue;
                    if (前期.equals("集中講義")) break;
                    if (後期.equals("集中講義")) break;
                    前期単位 = 前期.equals("") ? 0 : Integer.parseInt(前期);
                    後期単位 = 後期.equals("") ? 0 : Integer.parseInt(後期);
                    break;
                }
                infos.add(new SubjectInfo(区分, 科目名, 科目番号, 学年, 前期単位, 後期単位));
            } catch (Exception ignored) {}
        }
        return infos;
    }
}
