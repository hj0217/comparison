package com.demo1.customer.dto;

import com.demo1.customer.tracker.Captured;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class Customer {

    private int id;
    private String name;
    private int age;

}
