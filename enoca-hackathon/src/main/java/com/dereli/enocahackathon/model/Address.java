package com.dereli.enocahackathon.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "address")
public class Address extends BaseEntity{

    @Column(unique = true)
    private String name;

    private String city;

    private String province;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Address(String name, String city, String province, String customer) {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return getId()!= null && Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
