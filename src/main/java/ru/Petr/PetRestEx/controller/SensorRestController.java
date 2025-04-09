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

//    private final SensorMapper sensorMapper;

    @Autowired
    public SensorRestController(SensorService sensorService
//            , SensorMapper sensorMapper
    ) {
        this.sensorService = sensorService;
//        this.sensorMapper = sensorMapper;
    }

    /**
     * Получение списка всех сенсоров
     *
     * @return Список сенсоров
     */
    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors() {
        List<Sensor> sensors = sensorService.getAllSensors();
//        List <SensorDTO> sensorsDTO = sensorMapper.toSensorDTOs(sensors);
        return ResponseEntity.ok(sensors);
    }

    /**
     * Получение сенсора по его идентификатору
     *
     * @param id Идентификатор сенсора
     * @return Сенсор с указанным идентификатором
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable("id") Long id) {
        Sensor sensor = sensorService.getSensorById(id);
//        SensorDTO sensorDTO = sensorMapper.toSensorDTO(sensor);
        if (sensor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sensor);
    }

    /**
     * Создание нового сенсора
     *
     * @param sensor Новый сенсор
     * @return Созданный сенсор
     */
    @PostMapping
    public ResponseEntity<Sensor> createSensor(@Valid @RequestBody Sensor sensor) {
//        Sensor createdSensor = sensorMapper.fromSensorDTO(sensorDTO);
        Sensor savedSensor = sensorService.createSensor(sensor);
//        SensorDTO crashedSensorDTO = sensorMapper.toSensorDTO(savedSensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSensor);
    }

    /**
     * Обновление сенсора
     *
     * @param id      Идентификатор сенсора
     * @param sensor  Обновляемые данные сенсора
     * @return Обновленный сенсор
     */
    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable("id") Long id, @Valid @RequestBody Sensor sensor) {
//        Sensor sensor = sensorMapper.fromSensorDTO(sensorDTO);
        Sensor updatedSensor = sensorService.updateSensor(id, sensor);
//        SensorDTO updatedSensorDTO = sensorMapper.toSensorDTO(updatedSensor);
        return ResponseEntity.ok(updatedSensor);
    }

    /**
     * Удаление сенсора по его идентификатору
     *
     * @param id Идентификатор сенсора
     * @return Статус успешного удаления
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable("id") Long id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }
}
