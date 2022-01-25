package mo.spring.auditusingspringaop.controllers;

import mo.spring.auditusingspringaop.entities.Address;
import mo.spring.auditusingspringaop.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final IAddressService addressService;

    @Autowired
    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping()
    public List<Address> getAllAddresses() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") Long addressId) {
        Address address = addressService.findById(addressId);
        return ResponseEntity.ok().body(address);
    }

    @PostMapping()
    public Address createAddress(@RequestBody Address address) {
        return addressService.save(address);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable(value = "id") Long addressId, @RequestBody Address addressDetails) {
        Address address = addressService.findById(addressId);

        address.setState(addressDetails.getState());
        address.setStreet1(addressDetails.getStreet1());
        address.setStreet2(addressDetails.getStreet2());
        address.setCity(addressDetails.getCity());
        address.setZip(addressDetails.getZip());

        address = addressService.save(address);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAddress(@PathVariable(value = "id") Long addressId){
        addressService.deleteById(addressId);
        return ResponseEntity.ok("Deleted");
    }
}
