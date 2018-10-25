package pl.mojaaplikacja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mojaaplikacja.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
