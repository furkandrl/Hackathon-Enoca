package com.dereli.enocahackathon.dto;

import com.dereli.enocahackathon.model.Address;
import com.dereli.enocahackathon.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String email;
    private String password;
    private UserRole role;
    private List<Address> addresses;

    public CustomerDto(String email, String password, List<Address> addresses) {
    }
}
