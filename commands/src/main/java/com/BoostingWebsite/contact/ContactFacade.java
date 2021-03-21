package com.BoostingWebsite.contact;

import com.BoostingWebsite.account.exception.EmailNotFoundException;
import com.BoostingWebsite.contact.dto.ContactDto;

class ContactFacade {
    private final ContactBusiness contactBusiness;
    private final ContactFactory contactFactory;

    ContactFacade(ContactBusiness contactBusiness, ContactFactory contactFactory) {
        this.contactBusiness = contactBusiness;
        this.contactFactory = contactFactory;
    }

    void save(ContactDto contactDto) throws EmailNotFoundException {
        contactBusiness.save(contactFactory.from(contactDto));
    }
}
