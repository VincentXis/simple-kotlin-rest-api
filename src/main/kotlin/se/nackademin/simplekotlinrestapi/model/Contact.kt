package se.nackademin.simplekotlinrestapi.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Contact(val firstName: String = "",
                   val lastName: String = "",
                   val email: String = "",
                   val city: String = "",
                   val street: String = "",
                   @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0)