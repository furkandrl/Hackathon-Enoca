package com.dereli.enocahackathon.service;

import com.dereli.enocahackathon.dto.AddressListResponse;
import com.dereli.enocahackathon.dto.SaveAddressRequest;
import com.dereli.enocahackathon.model.Address;
import com.dereli.enocahackathon.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressListResponse saveAddress(SaveAddressRequest saveAddressRequest){
        Address address = new Address(
            saveAddressRequest.getName(),
                saveAddressRequest.getCity(),
                saveAddressRequest.getProvince(),
                    saveAddressRequest.getCustomer()
        );

        final Address fromDb = addressRepository.save(address);
        AddressListResponse addressListResponse = new AddressListResponse(
            fromDb.getId(),
                fromDb.getName(),
                fromDb.getCity(),
                fromDb.getProvince(),
                fromDb.getCustomer()
        );

        return addressListResponse;
    }

    public List<AddressListResponse> listAddresses(){
        return addressRepository.findAll()
                .stream()
                .map(AddressService::convertResponse)
                .collect(Collectors.toList());
    }

    private static AddressListResponse convertResponse(Address model){
        return new AddressListResponse(model.getName(),
                model.getCity(),
                model.getProvince(),
                model.getCustomer());
    }

    public void deleteAddress(String name){
        addressRepository.deleteByName(name);
    }






}
