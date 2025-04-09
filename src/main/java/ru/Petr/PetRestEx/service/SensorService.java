package ru.Petr.PetRestEx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Petr.PetRestEx.model.Sensor;
import ru.Petr.PetRestEx.repository.SensorRepository;

import java.util.List;

@Service
public class SensorService {

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
//        existingSensor.setLocalDateTime(updatedSensor.getLocalDateTime());

        return sensorRepository.save(existingSensor);
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public void deleteSensor(Long id) {
        sensorRepository.deleteById(id);
    }

}
