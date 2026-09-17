package com.transportation.fleet.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class PersonNamePersistenceEmbeddable {
    @Column(name = "given_name")
    private String givenNames;

    @Column(name = "paternal_surname")
    private String paternalSurname;

    @Column(name = "maternal_surname")
    private String maternalSurname;

    public PersonNamePersistenceEmbeddable() {
    }

    public PersonNamePersistenceEmbeddable(String givenNames,
                                           String paternalSurname,
                                           String maternalSurname) {
        this.givenNames = givenNames;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
    }

}
