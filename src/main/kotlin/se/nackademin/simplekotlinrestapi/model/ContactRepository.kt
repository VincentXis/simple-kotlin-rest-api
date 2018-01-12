package se.nackademin.simplekotlinrestapi.model

import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository : JpaRepository<Contact, Long>