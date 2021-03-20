package com.BoostingWebsite.contact;

import com.BoostingWebsite.account.exception.EmailNotFoundException;

class ContactBusiness {
    private final ContactRepository contactRepository;

    ContactBusiness(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    void save(Contact contact) throws EmailNotFoundException {
        if (contact.canCreate()){
            contact.create();
            contactRepository.save(contact);
        }

        throw new EmailNotFoundException("Your e-mail is incorrect!");
    }
}
