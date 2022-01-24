package mo.spring.auditusingspringaop.repositories;

import mo.spring.auditusingspringaop.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
