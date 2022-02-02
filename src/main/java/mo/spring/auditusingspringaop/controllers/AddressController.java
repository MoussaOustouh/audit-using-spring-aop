package mo.spring.auditusingspringaop.controllers;

import mo.spring.auditusingspringaop.dto.AddressDTO;
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
    public List<AddressDTO> getAllAddresses() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable(value = "id") Long addressId) {
        AddressDTO addressDTO = addressService.findById(addressId);
        return ResponseEntity.ok().body(addressDTO);
    }

    @PostMapping()
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok(addressService.save(addressDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable(value = "id") Long addressId, @RequestBody AddressDTO addressDetails) {
        AddressDTO addressDTO = addressService.findById(addressId);

        addressDTO.setState(addressDetails.getState());
        addressDTO.setStreet1(addressDetails.getStreet1());
        addressDTO.setStreet2(addressDetails.getStreet2());
        addressDTO.setCity(addressDetails.getCity());
        addressDTO.setZip(addressDetails.getZip());

        addressDTO = addressService.update(addressDTO);
        return ResponseEntity.ok(addressDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAddress(@PathVariable(value = "id") Long addressId){
        addressService.deleteById(addressId);
        return ResponseEntity.ok("Deleted");
    }
}
