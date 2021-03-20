package com.BoostingWebsite.contact;

import org.springframework.data.repository.CrudRepository;

interface SqlContactRepository extends CrudRepository<ContactSnapshot, Long> {
    ContactSnapshot save(ContactSnapshot contact);
}
