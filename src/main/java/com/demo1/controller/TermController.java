package com.demo1.controller;

import com.demo1.vo.PageInfo;
import com.demo1.vo.Term;
import com.demo1.service.TermService;
import com.demo1.vo.TermDtl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RequiredArgsConstructor
@Controller @RequestMapping("/terms")
public class TermController {

    private final TermService termService;

//    @Autowired
//    public TermController (TermService termService) {
//        this.termService = termService;
//    }


    /*--------------------------------메인페이지(BD데이터 List)----------------------------------------*/
    @GetMapping("/")
    public String home(@RequestParam(value = "boardLimit", required=false, defaultValue = "30") int boardLimit,
                       @RequestParam(value = "pageNum", required=false, defaultValue = "1") int pageNum,
                       Model model) {

        //전체 게시글 구하기
        //int listCount = termService.listCount();
        int listCount = 100;
        int pageLimit = 5;	// 보여질 페이지 수(하단 페이지 번호)

        PageInfo pageInfo = Pagination.getPageInfo(listCount, pageNum, pageLimit, boardLimit);


        List<Term> terms = termService.findAll(pageInfo);
        model.addAttribute("terms", terms);
        model.addAttribute("pi", pageInfo);

        return "home";
    }

    /*-----------------------------------------검색 search------------------------------------------------*/
//    @PostMapping("/search")
//    public String search(@RequestParam(value="type", required = false) String searchType,
//                         @RequestParam(value="pageNum", required = false, defaultValue = "1") int pageNum,
//                         Term term, Model model) {
//
//        List<Term> terms = termService.search(term, searchType);
//        int listCount = terms.size();
//        int boardLimit = 30;
//        int pageLimit = 5;	// 보여질 페이지 수(하단 페이지 번호)
//        PageInfo pageInfo = Pagination.getPageInfo(listCount, pageNum, pageLimit, boardLimit);
//
//        model.addAttribute("terms", terms);
//        model.addAttribute ("searchType", searchType);
//        model.addAttribute("pi", pageInfo);
//
//        return "home";
//    }
//
    /*------------------------------등록 페이지 & 상세 페이지--------------------------------------*/
    @GetMapping(value = {"/detail/" , "/detail/{strNo}"})
    public String detail(@PathVariable("strNo") Optional<String> strNo,
                         Model model) {

        if(strNo.isPresent()) { // 상세페이지
            int no = Integer.parseInt(strNo.get());
            model.addAttribute("term", termService.findOne(no));
            model.addAttribute("list" ,termService.findConts(no));
            return "terms/detail";

        } else { //등록 페이지
            model.addAttribute("term", null);
            model.addAttribute("list" , null);
            return "/terms/detail";
        }
    }

//    /*--------------------------------------신규 등록 (ajax-form-serialize)----------------------------------------------*/
//    @PostMapping(value = "/register")
//    @ResponseBody
//    public int register (@RequestBody Term term) {
//        return termService.register(term);
//    }
}
