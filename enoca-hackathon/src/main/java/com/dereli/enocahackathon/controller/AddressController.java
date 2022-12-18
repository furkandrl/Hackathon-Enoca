package com.dereli.enocahackathon.controller;

import com.dereli.enocahackathon.dto.AddressListResponse;
import com.dereli.enocahackathon.dto.SaveAddressRequest;
import com.dereli.enocahackathon.model.Address;
import com.dereli.enocahackathon.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(name = "/save")
    public ResponseEntity<AddressListResponse> saveAddress(@Valid @RequestBody SaveAddressRequest request){
        return ResponseEntity
                .ok((addressService.saveAddress(request)));
    }

    @GetMapping(name = "/list")
    public ResponseEntity<List<AddressListResponse>> listAll(){
        return  ResponseEntity
                .ok(addressService.listAddresses());
    }

    @DeleteMapping(name = "/delete/{name}")
    public void deleteAddress(@PathVariable String name){
        addressService.deleteAddress(name);
    }

}
