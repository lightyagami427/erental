package edu.cvr.erental.view;

import org.springframework.web.multipart.MultipartFile;

import edu.cvr.erental.model.RentalProperties;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class RentalPropertyView extends RentalProperties {
    private MultipartFile img;

}
