package com.demo1.customer;


import com.demo1.customer.dto.Customer;
import com.demo1.customer.tracker.FieldComparison;
import com.demo1.customer.tracker.HistoryDto;
import com.demo1.customer.tracker.NumberCaptured;
import com.demo1.customer.tracker.StringCaptured;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@Controller
@RequestMapping("/customer")
public class CustomerController {


    //object -> json 변환
    static ObjectMapper om = new ObjectMapper();

    @GetMapping("update")
    public String updateCustomerInformation (@RequestParam int index, Model model) throws IOException, ClassNotFoundException {
        //oldValue 객체 1 (원래 DB조회로 데이터를 가져와야하지만 그냥 test객체 만들어 사용)
        Customer asIs = new Customer();
        asIs.setId(100001);
        asIs.setName("일부엉");
        asIs.setAge(30);
        //newValue 객체 2 (원래 client 쪽에서 parameter로 넘겨받아야하지만 그냥 test객체 만들어 사용)
        Customer toBe = new Customer();
        toBe.setId(asIs.getId());
        toBe.setName("이부엉");
        toBe.setAge(40);

        //HistoryDto hd = new HistoryDto(); // DB히스토리 table에 넣어질 객체

        FieldComparison fieldComparison = new FieldComparison();
        fieldComparison.comparison(asIs.getClass(), toBe.getClass());

        //StringCaptured stringCaptured = new StringCaptured("성명", asIs.getName(), toBe.getName());
        //NumberCaptured numberCaptured = new NumberCaptured("나이", asIs.getAge(), toBe.getAge());


        //hd.addIfChanged(stringCaptured);
        //hd.addIfChanged(numberCaptured);
        //hd.setIndex(asIs.getId());

       // String result  = om.writeValueAsString(hd); // {"index": 100001, "changes":[{"name":"성명", "type": "STRING", "oldValue": "일부엉", "newValue": "이부엉"},{"name":"나이", "type": "NUMBER", "oldValue": "30", "newValue": "40"}]}
//System.out.println(result);

        //deserialised = HistoryDto로 변경하기
//        HistoryDto historyDto = om.readValue(result, HistoryDto.class);
//System.out.println(historyDto);
//        model.addAttribute("historyDto", historyDto);
        return "history/detail";
    }

/*
   -
* No.   |  변경필드 |  변경 전  |  변경 후 | 변경 일시  | 변경인
* -------------------------------------------------------------
*   1   |  이름    |  일부엉   |  이부엉  | 2023.03.29| 김슬기
*   2   |  나이    |    30    |   40    | 2023.03.29| 김슬기
*
* */



}
