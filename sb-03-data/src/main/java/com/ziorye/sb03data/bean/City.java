package com.ziorye.sb03data.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class City {
    private Long id;
    private String name;
    private String state;
    private String country;
}
