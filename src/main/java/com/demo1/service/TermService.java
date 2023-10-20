package com.demo1.service;

import com.demo1.domain.Term;
import com.demo1.mapper.TermDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.demo1.domain.PageInfo;

import java.util.*;


@Service
@RequiredArgsConstructor
public class TermService {

    //private final JdbcTermRepository jdbcTermRepository;


    //private SqlSessionTemplate sqlSessionTemplate;

    private final TermDao termDao;

//    @Autowired
//    public TermService (TermMapper termMapper){
//        this.termMapper = termMapper;
//    }

    /*--------------------------- 게시글  수 카운트--------------------------*/
    public int listCount() {
        return termDao.listCount();
    }

   /*----------------------------게시글 전체 조회 ---------------------------*/
    public List<Term> findAll(PageInfo pi) {

        return termDao.findAll(pi);
    }

//    public List<Term> findAll(int offset, int boardLimit) {
//        Map<String, Integer> paramMap = new HashMap<>();
//        paramMap.put("offset", boardLimit);
//        paramMap.put("boardLimit", boardLimit);
//
//        return termDao.findAll(paramMap);
//    }

//    /*-------------------------상세페이지-------------------------------------*/
//    public Term findOne(int no) {
//        return jdbcTermRepository.findOne(sqlSessionTemplate, no);
//    }
//
//    public List<TermDtl> findConts(int no) {
//        return jdbcTermRepository.findConts(sqlSessionTemplate, no);
//    }
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