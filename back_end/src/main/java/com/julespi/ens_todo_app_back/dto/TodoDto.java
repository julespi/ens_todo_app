package com.julespi.ens_todo_app_back.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

public class TodoDto {

    @Getter @Setter
    private Long id;

    @NotBlank(message = "name is required")
    @Getter @Setter
    private String name;

    @Getter @Setter
    private Boolean completed;
}
