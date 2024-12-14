package com.challenge.bci.users.entity;

import com.challenge.bci.users.dto.PhoneRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "telefono")
public class Phone {

  @Id
  @GeneratedValue
  private Long id;
  private String number;
  private String cityCode;
  private String countryCode;
  /*@ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id",
      referencedColumnName = "id")*/
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
  @JoinColumn(name = "customerId")
  private Customer customer;


  public Phone(PhoneRequest phoneRequest){
    number = phoneRequest.getNumber();
    cityCode = phoneRequest.getCityCode();
    countryCode = phoneRequest.getCountryCode();
  }
}
