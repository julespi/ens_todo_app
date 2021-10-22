package com.julespi.ens_todo_app_back.dto;

import lombok.Getter;
import lombok.Setter;

public class TodoDto {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Boolean completed;
}
