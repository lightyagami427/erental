package edu.cvr.erental.model;

import java.util.UUID;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="properties_image")

public class PropertyImages {

     @Id
     @GeneratedValue
     @Column(name="id")
     private UUID id;
     @Column(name="propertyid")
     private UUID propertyId;   
     @Column(name="imgname")
     @Nonnull
     private String imgName;
     @Column(name="img")
     @Nonnull
     private byte[] img;
}
