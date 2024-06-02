package com.example.catalog.responses;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
public class LoginResponse {

    String status;
    String message;
    Integer id;
}
