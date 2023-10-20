package com.demo1.demo1.repository;
import com.demo1.domain.PageInfo;
import com.demo1.domain.Term;
import com.demo1.domain.TermDtl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
public class JdbcTermRepository  {

    //@RequiredArgsconstructor 어노테이션으로 생성자 생략 가능
    //public JdbcTermRepository(DataSource dataSource) {
    //    this.dataSource = dataSource;
    //}

//    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//    private final DataSource dataSource;
//
//
//    /* ------------------------------------- 게시글  수 카운트 -------------------------------------*/
//        @Override
//        public int listCount (SqlSessionTemplate sqlSession) {
//            return  sqlSession.selectOne("termMapper.listCount");
//        }
//
//    /*--------------------------------------게시글 전체 조회 -----------------------------------------*/
//        @Override
//        public List<Term> findAll(SqlSessionTemplate sqlSession, PageInfo pi) {
//          return sqlSession.selectList("termMapper.findAll", pi);
//        }
//
//    /*------------------------------------------글 상세보기-----------------------------------------*/
//        //1. term
//        @Override
//        public Term findOne(SqlSessionTemplate sqlSession, int no) {
//            return sqlSession.selectOne("termMapper.findOne", no);
//        }
//        //2. termDtl
//        @Override
//        public List<TermDtl> findConts(SqlSessionTemplate sqlSession, int no) {
//            return sqlSession.selectList("termMapper.findConts", no);
//        }
//
//    /*-------------------------------------- 글 등록 ----------------------------------------------*/
//        //1. term
//        @Override
//        public int registerTerm(SqlSessionTemplate sqlSession, Term term) {
//            return sqlSession.insert("termMapper.registerTerm", term);
//        }
//        //2. termDtl
//        @Override
//        public int registerTermDtl (SqlSessionTemplate sqlSession, TermDtl termDtl) {
//            return sqlSession.insert("termMapper.registerTermDtl", termDtl);
//        }
//
//    /*-----------------------------------------검색------------------------------------------------*/
//        @Override
//        public List<Term> search(SqlSessionTemplate sqlSession, Term term, String category) {
//            Map<String, Object> paramMap = new HashMap<>();
//            paramMap.put("term", term);
//            paramMap.put("category" , category);
//            return sqlSession.selectList("termMapper.search", paramMap);
//        }
} //
