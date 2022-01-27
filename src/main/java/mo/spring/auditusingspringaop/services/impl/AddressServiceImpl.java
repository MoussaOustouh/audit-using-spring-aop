package mo.spring.auditusingspringaop.services.impl;

import mo.spring.auditusingspringaop.dto.AddressDTO;
import mo.spring.auditusingspringaop.dto.mapper.IMapper;
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
    private IMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDTO findById(Long id) {
        Optional<Address> optionalAddress = this.addressRepository.findById(id);
        if(optionalAddress.isEmpty()){
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " Address { id : "+id+" }");
        }
        return mapper.map(optionalAddress.get(), AddressDTO.class);
    }

    @Override
    public List<AddressDTO> findAll() {
        return mapper.mapList(addressRepository.findAll(), AddressDTO.class);
    }

    @Override
    public AddressDTO save(AddressDTO dto) {
        return mapper.map(
                addressRepository.save(mapper.map(dto, Address.class)),
                AddressDTO.class
        );
    }

    @Override
    public AddressDTO update(AddressDTO dto) {
        return mapper.map(
                addressRepository.save(mapper.map(dto, Address.class)),
                AddressDTO.class
        );
    }

    @Override
    public void deleteById(Long id) {
        if(addressRepository.existsById(id)){
            addressRepository.deleteById(id);
        }
        else{
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " Address { id : "+id+" }");
        }
    }
}
