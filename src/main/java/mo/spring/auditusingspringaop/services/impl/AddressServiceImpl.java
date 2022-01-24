package mo.spring.auditusingspringaop.services.impl;

import mo.spring.auditusingspringaop.entities.Address;
import mo.spring.auditusingspringaop.repositories.AddressRepository;
import mo.spring.auditusingspringaop.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<Address> findOne(Long id) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        return null;
    }

    @Override
    public Address save(Address entity) {
        return null;
    }

    @Override
    public Address update(Address entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
