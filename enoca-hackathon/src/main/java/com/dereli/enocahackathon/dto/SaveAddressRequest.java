package com.dereli.enocahackathon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
public class SaveAddressRequest {
    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private String province;
    @NotNull
    private String Customer;

}
