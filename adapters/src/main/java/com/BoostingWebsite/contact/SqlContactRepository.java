package com.BoostingWebsite.contact;

import org.springframework.data.repository.CrudRepository;

interface SqlContactRepository extends ContactRepository, CrudRepository<Contact, Long> {
    Contact save(Contact contact);
}
