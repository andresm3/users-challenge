package com.challenge.bci.users.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

@Data
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String email;
  private String password;
  @OneToMany(fetch = FetchType.LAZY,
      mappedBy = "customer",
      cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Phone> phoneList;
}
