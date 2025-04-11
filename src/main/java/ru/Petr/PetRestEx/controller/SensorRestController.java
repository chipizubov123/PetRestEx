package ru.Petr.PetRestEx.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Petr.PetRestEx.model.Sensor;
import ru.Petr.PetRestEx.service.SensorService;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorRestController {

    private final SensorService sensorService;

    @Autowired
    public SensorRestController(SensorService sensorService) {
        this.sensorService = sensorService;

    }


    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors() {
        List<Sensor> sensors = sensorService.getAllSensors();

        return ResponseEntity.ok(sensors);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable("id") Long id) {
        Sensor sensor = sensorService.getSensorById(id);

        if (sensor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sensor);
    }


    @PostMapping
    public ResponseEntity<Sensor> createSensor(@Valid @RequestBody Sensor sensor) {
        Sensor savedSensor = sensorService.createSensor(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSensor);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable("id") Long id, @RequestBody Sensor sensor) {
        Sensor updatedSensor = sensorService.updateSensor(id, sensor);
        return ResponseEntity.ok(updatedSensor);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable("id") Long id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }
}
