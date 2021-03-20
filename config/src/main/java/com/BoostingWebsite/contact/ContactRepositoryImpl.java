package com.BoostingWebsite.contact;

import org.springframework.stereotype.Repository;

@Repository
class ContactRepositoryImpl implements ContactRepository{
    private final SqlContactRepository repository;

    ContactRepositoryImpl(SqlContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contact save(Contact contact) {
        return Contact.restore(repository.save(contact.getSnapshot()));
    }
}
