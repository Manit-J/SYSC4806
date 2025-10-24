package org.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.ArrayList;
import java.util.List;

@RepositoryRestResource
public interface AddressBookRepo extends CrudRepository<AddressBook, Long> {
    AddressBook findById(long id);

    @Override
    List<AddressBook> findAll();
}