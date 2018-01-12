package se.nackademin.simplekotlinrestapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.nackademin.simplekotlinrestapi.model.Contact
import se.nackademin.simplekotlinrestapi.model.ContactRepository
import kotlin.streams.toList

@RestController
@RequestMapping("/rest/contact")
class ContactRepositoryController(val contactRepository: ContactRepository) {

    @GetMapping("/all")
    fun getContacts() = contactRepository.findAll()

    @GetMapping("/add/{firstName},{lastName},{email},{city},{street}")
    fun addContact(
            @PathVariable firstName: String, @PathVariable lastName: String, @PathVariable email: String,
            @PathVariable city: String, @PathVariable street: String): List<Contact> {
        contactRepository.save(Contact(firstName, lastName, email, city, street))
        return contactRepository.findAll()
    }

    @GetMapping("/firstName/{firstName}")
    fun getByFirstName(@PathVariable firstName: String) =
            contactRepository.findAll().stream().filter({ it.firstName.contains(firstName, ignoreCase = true) }).toList()

    @GetMapping("/lastName/{lastName}")
    fun getByLastName(@PathVariable lastName: String) =
            contactRepository.findAll().stream().filter({ it.lastName.contains(lastName, ignoreCase = true) }).toList()

    @GetMapping("/email/{email}")
    fun getByEmail(@PathVariable email: String) =
            contactRepository.findAll().stream().filter({ it.email.contains(email, ignoreCase = true) }).toList()

    @GetMapping("/city/{city}")
    fun getByCity(@PathVariable city: String) =
            contactRepository.findAll().stream().filter({ it.city.contains(city, ignoreCase = true) }).toList()

    @GetMapping("/street/{street}")
    fun getByStreet(@PathVariable street: String) =
            contactRepository.findAll().stream().filter({ it.street.contains(street, ignoreCase = true) }).toList()

    @GetMapping("/id/{id}")
    fun getById(@PathVariable id: String): Contact? {
        val inputId = id.toLongOrNull()
        return try {
            if (inputId != null) contactRepository.getOne(id.toLongOrNull())
            else null
        } catch (ex: Exception) {
            null
        }
    }

    @GetMapping("/anyMatch/{query}")
    fun getByAnyMatch(@PathVariable query: String): List<Contact> {
        return contactRepository.findAll().stream().filter(
                {
                    it.firstName.contains(query, ignoreCase = true) ||
                            it.lastName.contains(query, ignoreCase = true) ||
                            it.email.contains(query, ignoreCase = true) ||
                            it.city.contains(query, ignoreCase = true) ||
                            it.street.contains(query, ignoreCase = true) ||
                            it.id.toString().contains(query)
                }).toList()
    }
}