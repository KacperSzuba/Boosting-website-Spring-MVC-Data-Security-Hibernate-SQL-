package com.BoostingWebsite.contact;

import com.BoostingWebsite.contact.dto.ContactDto;

class ContactFactory {
    Contact from(ContactDto contactDto){
        return Contact.restore(
                new ContactSnapshot(
                        contactDto.getId(),
                        contactDto.getName(),
                        contactDto.getEmail(),
                        contactDto.getSubject(),
                        contactDto.getQuestion(),
                        contactDto.getDate()
                )
        );
    }
}
