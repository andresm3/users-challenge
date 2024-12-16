package com.challenge.bci.users.entity;

import com.challenge.bci.users.dto.PhoneRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
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
//@Table(name = "telefono")
public class Phone {

  @Id
  @GeneratedValue
  @JsonIgnore
  private Long id;
  private String number;
  private String cityCode;
  private String countryCode;
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY,
      cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;


  public Phone(PhoneRequest phoneRequest){
    number = phoneRequest.getNumber();
    cityCode = phoneRequest.getCityCode();
    countryCode = phoneRequest.getCountryCode();
  }
}
