package com.demo1.service;

import com.demo1.vo.Term;
import com.demo1.mapper.daoInterface.TermMapper;
import com.demo1.vo.TermDtl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.demo1.vo.PageInfo;

import java.util.*;


@Service
@RequiredArgsConstructor
public class TermService {

    final private TermMapper termMapper;


    /*--------------------------- 게시글  수 카운트--------------------------*/
    public int listCount() {
        return termMapper.listCount();
    }

   /*----------------------------게시글 전체 조회 ---------------------------*/
    public List<Term> findAll(PageInfo pi) {
        return termMapper.findAll(pi);
    }

    /*-------------------------상세페이지-------------------------------------*/
    public Term findOne(int no) {
        return termMapper.findOne(no);
    }

    public List<TermDtl> findConts(int no) {
        return termMapper.findConts(no);
    }
//
//    /*------------------------------ 검색 ------------------------------------*/
//    public List<Term> search(Term term, String category) {
//        return jdbcTermRepository.search(sqlSessionTemplate, term, category);
//    }
//
//    /*------------------------------ 등록  ------------------------------------*/
//    @Transactional
//    public int register(Term term) { // method 1개에 sql문 1개...
//        jdbcTermRepository.registerTerm(sqlSessionTemplate, term);
//
//        int result = 0; // 등록 결과 확인용
//        for (TermDtl termDtl : term.getTermDtlList()) {
//            termDtl.setNo(term.getNo()); // "term_no" 값을 설정
//            result += jdbcTermRepository.registerTermDtl(sqlSessionTemplate, termDtl);
//        }
//        return result;
//    }
}