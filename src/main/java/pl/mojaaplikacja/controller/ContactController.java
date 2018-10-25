package pl.mojaaplikacja.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.mojaaplikacja.model.Contact;
import pl.mojaaplikacja.repository.ContactRepository;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

	private ContactRepository contactRepository;
	
	@Autowired
	public ContactController(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contact>> allContacts(){
		
		List<Contact> allContacts = contactRepository.findAll(); 
		return ResponseEntity.ok(allContacts);
	}
	
	@GetMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> findContactById(@PathVariable Long id){
		Contact contact = contactRepository.findOne(id);
		return ResponseEntity.ok(contact);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveContact(@RequestBody Contact contact){
		Contact savedContact = contactRepository.save(contact);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedContact.getId())
				.toUri();
		return ResponseEntity.created(location).body(savedContact);
	}
}
