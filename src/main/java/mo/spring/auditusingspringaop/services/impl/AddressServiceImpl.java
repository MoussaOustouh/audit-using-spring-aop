package mo.spring.auditusingspringaop.services.impl;

import mo.spring.auditusingspringaop.entities.Address;
import mo.spring.auditusingspringaop.exceptions.NotFoundException;
import mo.spring.auditusingspringaop.exceptions.constants.ErrorMessages;
import mo.spring.auditusingspringaop.repositories.AddressRepository;
import mo.spring.auditusingspringaop.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<Address> findById(Long id) {
        Optional<Address> optionalAddress = this.addressRepository.findById(id);
        if(optionalAddress.isEmpty()){
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        return optionalAddress;
    }

    @Override
    public List<Address> findAll() {
        return this.addressRepository.findAll();
    }

    @Override
    public Address save(Address entity) {
        return this.addressRepository.save(entity);
    }

    @Override
    public Address update(Address entity) {
        return this.addressRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        this.addressRepository.deleteById(id);
    }
}
