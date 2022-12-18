package com.dereli.enocahackathon.dto;

import com.dereli.enocahackathon.model.Customer;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class AddressListResponse {
    private String name;
    private String city;
    private String province;
    private String Customer;

    public AddressListResponse(Long id, String name, String city, String province, com.dereli.enocahackathon.model.Customer customer) {
    }

    public AddressListResponse(String name, String city, String province, com.dereli.enocahackathon.model.Customer customer) {
    }
}
