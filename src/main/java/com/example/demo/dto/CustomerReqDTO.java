package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerReqDTO {
    @NotEmpty(message="Name은 필수 입력항목입니다.") // " " 허용
    private String name;

    @NotNull(message="Age은 필수 입력항목입니다.") // " " 허용
    private long age;

    @NotBlank(message = "Email은 필수 입력항목입니다.") // " " 허용하지 않음
    @Email(message="Email 형식이 아닙니다.")
    private String email;



}
