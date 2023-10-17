package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerReqForm {

    private Long id;

    @NotEmpty(message="Name은 필수 입력항목입니다.") // " " 허용
    private String name;

    @NotNull(message="Age는 필수 입력항목입니다.") // " " 허용
    private Long age;

    @NotBlank(message = "Email은 필수 입력항목입니다.") // " " 허용하지 않음
    @Email(message="Email 형식이 아닙니다.")
    private String email;
}
