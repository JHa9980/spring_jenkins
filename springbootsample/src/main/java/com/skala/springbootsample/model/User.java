package com.skala.springbootsample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity (name="users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private String email;

  @ManyToOne
  @JoinColumn(name = "region_id")
  @NotNull(message = "Region is required")
  private Region region;

  //region 제외
  public User(String name, String email, Region region) {
      this.name = name;
      this.email = email;
      this.region = region;
  }
}