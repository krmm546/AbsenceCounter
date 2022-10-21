package info.creidea.domain;

public record SubjectInfo(String 区分, String 科目名, String 番号, int 学年, int 前期単位数, int 後期単位数) {
    /**
     * @param 学年 1~5
     * @return その学年の科目かどうか
     */
    public boolean is学年(int 学年) {
        return this.学年 == 学年;
    }

    /**
     * @param 学期 1 or 2
     * @return その学期の科目かどうか
     */
    public boolean is学期(int 学期) {
        if (学期 == 1) return this.前期単位数 > 0;
        if (学期 == 2) return this.後期単位数 > 0;
        throw new IllegalArgumentException("不正な学期値: " + 学期);
    }

    public String 科目名() {
        return 科目名;
    }

    public String 科目ID() {
        return "20220-28-15-" + 番号;
    }

    public int 最大欠課時数() {
        return (前期単位数 + 後期単位数) * 5;
    }
}