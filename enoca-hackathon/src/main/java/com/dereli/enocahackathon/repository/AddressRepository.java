package com.dereli.enocahackathon.repository;

import com.dereli.enocahackathon.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> deleteByName(String name);//optional to prevent null pointer exception


}
