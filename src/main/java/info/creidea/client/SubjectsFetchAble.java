package info.creidea.client;

import info.creidea.domain.SubjectInfo;

import java.util.List;

public interface SubjectsFetchAble {
    /**
     * @param 学年 1~5
     * @param 学期 前期: 1, 後期: 2
     */
    List<SubjectInfo> fetch(int 学年, int 学期);
}
