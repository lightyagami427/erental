package edu.cvr.erental.model;

import java.util.UUID;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="rental_properties")
public class RentalProperties {
     @Id
     @GeneratedValue
     @Column(name="id")
     private UUID id;
     @Column(name="rentaltitle")
     @Nonnull
     private String rentalTitle;
     @Column(name="description")
     @Nonnull
     private String description;
     @Column(name="monthlyprice")
     @Nonnull
     private int monthlyPrice;
     @Column(name="hno")
     @Nonnull
     private String hNo;
     @Column(name="street")
     @Nonnull
     private String street;
     @Column(name="area")
     @Nonnull
     private String area;
     @Column(name="landmark")
     private String landMark;
     @Column(name="city")
     @Nonnull
     private String city;
     @Column(name="state")
     @Nonnull
     private String state;
     @Column(name="pincode")
     @Nonnull
     private int pincode;
}
