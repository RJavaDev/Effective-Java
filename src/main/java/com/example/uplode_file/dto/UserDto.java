package com.example.uplode_file.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String firstname;
    private String lastname;
    private String middleName;
    private List<String> attachId;
    private List<String> url;
}
