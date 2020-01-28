package com.zero.system.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PageInfo {

    @JsonIgnore
    private Integer page = 1;
    @JsonIgnore
    private Integer pageSize = 10;

}
