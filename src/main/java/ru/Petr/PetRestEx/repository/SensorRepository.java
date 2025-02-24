package ru.Petr.PetRestEx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.Petr.PetRestEx.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

}
