package com.demo1.mapper.daoInterface;

import com.demo1.vo.PageInfo;
import com.demo1.vo.Term;
import com.demo1.vo.TermDtl;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


public interface TermMapper {

    //전체 검색
    List<Term> findAll (PageInfo pageInfo);

    //전체 게시글 조회
    int listCount();

    //상세 페이지
    Term findOne(int no);

    //상세 페이지
    List<TermDtl> findConts(int no);
}
