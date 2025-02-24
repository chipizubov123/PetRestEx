package ru.Petr.PetRestEx.repository;

import org.springframework.data.repository.CrudRepository;
import ru.Petr.PetRestEx.model.Workshop;

public interface WorkshopRepository extends CrudRepository<Workshop, Long> {
}
