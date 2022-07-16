package com.vikhani.animfeigner.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class AnimalDto {
    private String nickname;
    private Date birthday;
    private String gender;
    private String species;
}
