package ru.Petr.PetRestEx.controller;


import org.springframework.beans.factory.annotation.Autowired;
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

//@RestController
//@RequestMapping("/api/inspectors/workshops/sensors")
//public class SensorRestController {
//
//    @Autowired
//    private SensorService sensorService;
//
//    @PostMapping
//    public Sensor createSensor(@RequestBody Sensor sensor) {
//        return sensorService.createSensor(sensor);
//    }
//
//    @GetMapping("/{id}")
//    public Sensor getSensorById(@PathVariable Long id) {
//        return sensorService.getSensorById(id);
//    }
//
//    @PutMapping("/{id}")
//    public Sensor updateSensor(@PathVariable Long id, @RequestBody Sensor sensor) {
//        return sensorService.updateSensor(id, sensor);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteSensor(@PathVariable Long id) {
//        sensorService.deleteSensor(id);
//    }
//}
