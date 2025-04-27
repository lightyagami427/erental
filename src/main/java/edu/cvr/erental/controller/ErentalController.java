package edu.cvr.erental.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import edu.cvr.erental.model.ErentalUsers;
import edu.cvr.erental.model.ErentalUsersRegistry;
import edu.cvr.erental.model.PropertyImages;
import edu.cvr.erental.model.PropertyImagesRegistry;
import edu.cvr.erental.model.RentalProperties;
import edu.cvr.erental.model.RentalPropertiesRegistry;
import edu.cvr.erental.model.UserProperty;
import edu.cvr.erental.model.UserPropertyRegistry;
import edu.cvr.erental.model.UserPropertyRepository;
import edu.cvr.erental.model.UserRoleRegistry;
import edu.cvr.erental.model.UserRoles;
import edu.cvr.erental.security.AppGrantedAuthority;
import edu.cvr.erental.security.AuthUser;
import edu.cvr.erental.view.BookingView;
import edu.cvr.erental.view.ImageListings;
import edu.cvr.erental.view.ListOfProperty;
import edu.cvr.erental.view.PropertyListings;
import edu.cvr.erental.view.RentalPropertyView;
import edu.cvr.erental.view.SearchProperty;
import edu.cvr.erental.view.UsersView;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
@Slf4j
public class ErentalController {
    @Autowired
    private ErentalUsersRegistry usersRegistry;
    @Autowired
    private RentalPropertiesRegistry propertyRegistry;
    @Autowired
    private PropertyImagesRegistry propertyImagesRegistry;
    @Autowired
    private UserRoleRegistry userRoleRegistry;
    @Autowired
    private UserPropertyRegistry userPropertyRegistry;
    @Autowired
    private UserPropertyRepository userPropertyRepository;
    @GetMapping("/login")
    public String getMethodName() {
        return "login";
    }
        
    @PostMapping("/registeruser")
    public String postMethodName(@ModelAttribute UsersView users) {
        log.info("username={},email={}",users.getUserName(),users.getEmail());

        ErentalUsers user=new ErentalUsers();
        user.setUserName(users.getUserName());
        user.setEmail(users.getEmail());
        user.setPassword(users.getPassword());
        user=usersRegistry.save(user);

        UserRoles userRole = new UserRoles();
        userRole.setUserId(user.getId());
        userRole.setRole(users.getRole());

        userRole=userRoleRegistry.save(userRole);
        return "success";
    }
    @GetMapping("/home")
    public String getHome(Model model)
    {

        Collection<? extends GrantedAuthority> ur=SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        AuthUser user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getErusers().getUserName();
        model.addAttribute("username", username);
        if(ur.stream().anyMatch(ga->ga.getAuthority().equals("owner"))){
        displayProperties(model);
        return "owner";}
        else{
        model.addAttribute("searchValue","");
        model.addAttribute("priceValue",0);
        return "home";}
    }
    @GetMapping("/listing")
    public String getListing(Model model) {
        return "listing";
    }
    @PostMapping("/saveproperty")
    public String postSaveProperty(@ModelAttribute RentalPropertyView propertyView,Model model){
        log.info("username={},email={}",propertyView.getRentalTitle());
        RentalProperties property=new RentalProperties();
        property.setArea(propertyView.getArea());
        property.setHNo(propertyView.getHNo());
        property.setCity(propertyView.getCity());
        property.setDescription(propertyView.getDescription());
        property.setLandMark(propertyView.getLandMark());
        property.setMonthlyPrice(propertyView.getMonthlyPrice());
        property.setPincode(propertyView.getPincode());
        property.setRentalTitle(propertyView.getRentalTitle());
        property.setState(propertyView.getState());
        property.setStreet(propertyView.getStreet());
        property=propertyRegistry.save(property);
        try{
        PropertyImages propertyImages=new PropertyImages();
        propertyImages.setPropertyId(property.getId());    
        propertyImages.setImgName(propertyView.getImg().getOriginalFilename());
        propertyImages.setImg(propertyView.getImg().getBytes());
        propertyImagesRegistry.save(propertyImages);
        }
        catch(IOException ioException)
        {
            log.error("failed to save Image", ioException);
        }
        UserProperty userproperty=new UserProperty();
        userproperty.setPropertyId(property.getId());
        AuthUser user=(AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userproperty.setUserId(user.getErusers().getId());
        userproperty.setStatus("owner");
        userPropertyRegistry.save(userproperty);
        displayProperties(model);

        

        return "owner";
    }
    private void displayProperties(Model model)
    {
        
        AuthUser user=(AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<UserProperty> pageContent=userPropertyRegistry.getOwnedProperties(user.getErusers().getId(),PageRequest.of(0, 10));

        List<PropertyListings> listings=new ArrayList<>();
        List<PropertyListings> bookedlistings=new ArrayList<>();
        for(UserProperty up:pageContent.getContent())
        {
            Optional<RentalProperties> rentalproperty=propertyRegistry.findById(up.getPropertyId());
            if(rentalproperty.isPresent())
            {
                PropertyListings prop_list=new PropertyListings();
                prop_list.setArea(rentalproperty.get().getArea());
                prop_list.setCity(rentalproperty.get().getCity());
                prop_list.setDescription(rentalproperty.get().getDescription());
                prop_list.setHNo(rentalproperty.get().getHNo());
                prop_list.setLandMark(rentalproperty.get().getLandMark());
                prop_list.setMonthlyPrice(rentalproperty.get().getMonthlyPrice());
                prop_list.setPincode(rentalproperty.get().getPincode());
                prop_list.setRentalTitle(rentalproperty.get().getRentalTitle());
                prop_list.setState(rentalproperty.get().getState());
                prop_list.setStreet(rentalproperty.get().getStreet());
                
                List<PropertyImages> prop_img=propertyImagesRegistry.findByPropertyId(up.getPropertyId());
                for(PropertyImages pm:prop_img)
                {
                    prop_list.setImgName(pm.getImgName());
                    prop_list.setImgBase64(Base64.getEncoder().encodeToString(pm.getImg()));
                }
                listings.add(prop_list);
                List<PropertyListings> bookings=displayBookedProperties(up.getPropertyId());
                bookedlistings.addAll(bookings);
            }
           
        }
        model.addAttribute("bookings", bookedlistings);
        model.addAttribute("listings",listings);
    }
    @PostMapping("/searchproperty")
    public String postSearchProperty(@ModelAttribute SearchProperty searchProperty,Model model) {

        Page<RentalProperties> pageData=propertyRegistry.getProperties(searchProperty.getSearch(),searchProperty.getPrice(), PageRequest.of(0, 10));

        List<PropertyListings> property_listings=new ArrayList<>();
        
        for(RentalProperties rp:pageData.getContent())
        {
            List<PropertyImages> properties_images=propertyImagesRegistry.findByPropertyId(rp.getId());
            PropertyListings propertyLists=new PropertyListings();
            List<ImageListings> image_listings=new ArrayList<>();
            for(PropertyImages pm:properties_images)
             {
            //     ImageListings imgListings=new ImageListings();
            //     imgListings.setImgName(pm.getImgName());
            //     imgListings.setImg(pm.getImg());
            //     image_listings.add(imgListings);
                    propertyLists.setImgName(pm.getImgName());
                    propertyLists.setImgBase64(Base64.getEncoder().encodeToString(pm.getImg()));
                    break;
             }

            propertyLists.setArea(rp.getArea());
            propertyLists.setCity(rp.getCity());
            propertyLists.setDescription(rp.getDescription());
            propertyLists.setHNo(rp.getHNo());
            propertyLists.setLandMark(rp.getLandMark());
            propertyLists.setMonthlyPrice(rp.getMonthlyPrice());
            propertyLists.setPincode(rp.getPincode());
            propertyLists.setRentalTitle(rp.getRentalTitle());
            propertyLists.setStreet(rp.getStreet());
            propertyLists.setState(rp.getState());
            propertyLists.setId(rp.getId());

            property_listings.add(propertyLists);
        }
        log.info("total elements={}",pageData.getTotalElements());
        model.addAttribute("searchValue",searchProperty.getSearch());
        model.addAttribute("priceValue",searchProperty.getPrice());
        model.addAttribute("property_listings", property_listings);

        return "home";
        
    }
    @PostMapping("/booking")
    public String putMethodName(Model model,@ModelAttribute BookingView bookingView) {
        //TODO: process PUT request

        UserProperty up=new UserProperty();
        RentalProperties property=new RentalProperties();
        AuthUser user=(AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserProperty> booking=userPropertyRegistry.getBookedProperties(user.getErusers().getId(),bookingView.getBookedProperty());
        if(booking.isPresent()){
            log.info("This property is already booked!!");
        }
        else{
        up.setPropertyId(bookingView.getBookedProperty());
        up.setUserId(user.getErusers().getId());
        up.setStatus("Booked");
        userPropertyRegistry.save(up);
        }
        model.addAttribute("search value", "");
        model.addAttribute("price value", 0);
        return "home";        
        
    }
    private List<PropertyListings> displayBookedProperties(UUID propertyId)
    {
        Page<UserProperty> userProperty=userPropertyRegistry.findByPropertyIdAndStatus(propertyId,"Booked",PageRequest.of(0, 10));
        List<PropertyListings> bookings = new ArrayList<>();
        for (UserProperty up : userProperty.getContent()) 
        {
            Optional<RentalProperties> rentalProperty = propertyRegistry.findById(up.getPropertyId());
            if (rentalProperty.isPresent()) 
            {
                PropertyListings propList = new PropertyListings();
                propList.setArea(rentalProperty.get().getArea());
                propList.setCity(rentalProperty.get().getCity());
                propList.setDescription(rentalProperty.get().getDescription());
                propList.setHNo(rentalProperty.get().getHNo());
                propList.setLandMark(rentalProperty.get().getLandMark());
                propList.setMonthlyPrice(rentalProperty.get().getMonthlyPrice());
                propList.setPincode(rentalProperty.get().getPincode());
                propList.setRentalTitle(rentalProperty.get().getRentalTitle());
                propList.setState(rentalProperty.get().getState());
                Optional<ErentalUsers> tenant=usersRegistry.findById(up.getUserId());
                if(tenant.isPresent())
                {
                    propList.setUserName(tenant.get().getUserName());
                }
                List<PropertyImages> prop_img=propertyImagesRegistry.findByPropertyId(up.getPropertyId());
                for(PropertyImages pm:prop_img)
                {
                    propList.setImgName(pm.getImgName());
                    propList.setImgBase64(Base64.getEncoder().encodeToString(pm.getImg()));
                }
                bookings.add(propList);
            }
        }  
        return bookings;
    }
    @PostMapping("/logout")
    public String logout() {
        //TODO: process POST request
        
        return "login";
    }
    @PostMapping("/logoutowner")
    public String logoutowner() {
        //TODO: process POST request
        
        return "login";
    }
    
    
    
    
}