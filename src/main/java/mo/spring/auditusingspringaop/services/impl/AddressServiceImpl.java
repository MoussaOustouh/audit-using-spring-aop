package mo.spring.auditusingspringaop.services.impl;

import mo.spring.auditusingspringaop.traceability.strategy.annotations.TraceAfterDelete;
import mo.spring.auditusingspringaop.traceability.strategy.annotations.TraceAfterCreate;
import mo.spring.auditusingspringaop.traceability_services.impl.TraceAddressServiceImpl;
import mo.spring.auditusingspringaop.dto.AddressDTO;
import mo.spring.auditusingspringaop.dto.mapper.IMapper;
import mo.spring.auditusingspringaop.entities.Address;
import mo.spring.auditusingspringaop.exceptions.NotFoundException;
import mo.spring.auditusingspringaop.exceptions.constants.ErrorMessages;
import mo.spring.auditusingspringaop.repositories.AddressRepository;
import mo.spring.auditusingspringaop.services.IAddressService;
import mo.spring.auditusingspringaop.traceability.strategy.annotations.TraceAfterUpdate;
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
    @TraceAfterCreate(
            targetServiceClass = TraceAddressServiceImpl.class,
            targetMethodName = "traceAfterCreate",
            targetMethodArgsClasses = {AddressDTO.class, String.class, String.class},
            actionInfo = "trace after create description"
    )
    public AddressDTO save(AddressDTO dto) {
        return mapper.map(
                addressRepository.save(mapper.map(dto, Address.class)),
                AddressDTO.class
        );
    }

    @Override
    @TraceAfterUpdate(
            targetServiceClass = TraceAddressServiceImpl.class,
            targetMethodName = "traceAfterUpdate",
            targetMethodArgsClasses = {AddressDTO.class, String.class, String.class},
            actionInfo = "trace after update description"
    )
    public AddressDTO update(AddressDTO dto) {
        return mapper.map(
                addressRepository.save(mapper.map(dto, Address.class)),
                AddressDTO.class
        );
    }

    @Override
    @TraceAfterDelete(
            targetServiceClass = TraceAddressServiceImpl.class,
            targetMethodName = "traceAfterDelete",
            targetMethodArgsClasses = {AddressDTO.class, String.class, String.class, Object[].class},
            actionInfo = "trace after delete description"
    )
    public void deleteById(Long id) {
        if(addressRepository.existsById(id)){
            addressRepository.deleteById(id);
        }
        else{
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " Address { id : "+id+" }");
        }
    }
}
