package ru.Petr.PetRestEx.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.Petr.PetRestEx.model.Workshop;

@Repository
public interface WorkshopRepository extends CrudRepository<Workshop, Long> {
}
