package com.tannur.yandex.services;

import com.tannur.yandex.models.Citizen;
import com.tannur.yandex.repositories.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class CitizenService {

    private final CitizenRepository citizenRepository;

    private Map<Citizen, Integer> citizensByImportId = new HashMap<>();
    private int currentImportId = 0;

    @Autowired
    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public List<Citizen> findAll(){
        return citizenRepository.findAll();
    }

//    public Person findOne(int id){
//        Optional<Person> foundPerson =  peopleRepository.findById(id);
//        return foundPerson.orElseThrow(PersonNotFoundException::new);
//    }

    @Transactional
    public void save(Citizen citizen, int import_id){
        citizensByImportId.put(citizen, import_id);
        citizenRepository.save(citizen);

        System.out.println(import_id);
        System.out.println(citizensByImportId);
    }

//    private void enrichPerson(Person person){
//        person.setCreatedAt(LocalDateTime.now());
//        person.setUpdatedAt(LocalDateTime.now());
//        person.setCreatedWho("ADMIN");
//    }

//    @Transactional
//    public void update(int id, Person updatedPerson){
//        updatedPerson.setId(id);
//        peopleRepository.save(updatedPerson);
//    }
//
//    @Transactional
//    public void delete(int id){
//        peopleRepository.deleteById(id);
//    }
}