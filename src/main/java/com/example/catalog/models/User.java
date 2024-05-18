package com.example.catalog.models;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String email;
    String password;
}
