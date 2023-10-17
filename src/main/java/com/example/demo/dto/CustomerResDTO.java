package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResDTO {

    private Long id;
    private String name;
    private String email;
    private Long age;
    private LocalDateTime entryDate;
}