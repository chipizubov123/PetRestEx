package ru.Petr.PetRestEx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Petr.PetRestEx.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {


}
