package ru.Petr.PetRestEx.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Petr.PetRestEx.model.Sensor;
import ru.Petr.PetRestEx.repository.SensorRepository;

import java.util.List;

@Service
@Transactional
public class SensorService {

    private static final Logger log = LoggerFactory.getLogger(SensorService.class);

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor createSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor not found"));
    }

    public Sensor updateSensor(Long id, Sensor updatedSensor) {
        Sensor existingSensor = sensorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor not found"));

        existingSensor.setMetric(updatedSensor.getMetric());
        existingSensor.setValue(updatedSensor.getValue());
        existingSensor.setWorkshop(updatedSensor.getWorkshop());

//        existingSensor.setLocalDateTime(updatedSensor.getLocalDateTime());

        return sensorRepository.save(existingSensor);
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Transactional
    public void deleteSensor(Long id) {
        log.info("Deleting sensor with ID: {}", id);
        sensorRepository.deleteById(id);
    }

}
