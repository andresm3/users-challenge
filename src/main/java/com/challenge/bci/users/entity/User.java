package com.challenge.bci.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@Entity
//@Table(name = "usuario")
public class User {

  @Id
  @GeneratedValue
  @JsonIgnore
  @Column(name = "user_id")
  private Long id;

  @JsonProperty("id")
  private String uuid;
  private String name;
  private String email;
  private String password;
  @OneToMany(fetch = FetchType.LAZY,
      mappedBy = "user",
      cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Phone> phoneList;

  @Column(name = "created_date")
  @JsonProperty("created")
  private Date created;
  @Column(name = "modified_date")
  private Date modified;
  @JsonProperty("isactive")
  private boolean isActive;
}
