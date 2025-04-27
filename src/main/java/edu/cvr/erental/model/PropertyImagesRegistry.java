package edu.cvr.erental.model;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@NoArgsConstructor

public class PropertyImagesRegistry {

    @Autowired
    private PropertyImagesRepository imagesRepository;
    public void save(PropertyImages img) {
        imagesRepository.save(img);
    }
    public List<PropertyImages> findByPropertyId(UUID propertyId)
    {
        return imagesRepository.findByPropertyId(propertyId);
    }

}
