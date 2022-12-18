package com.dereli.enocahackathon.dto;

import lombok.Data;

@Data
public class SaveCustomerRequest {
    private String email;
    private String password;
}
