package ru.Petr.PetRestEx.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.Petr.PetRestEx.model.Sensor;
import ru.Petr.PetRestEx.service.SensorService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sensors")
public class SensorMVCController {

    private final SensorService sensorService;


    @Autowired
    public SensorMVCController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public String allSensors(Model model) {
        List<Sensor> sensors = sensorService.getAllSensors();
        model.addAttribute("sensors", sensors);
        return "sensors/list-sensors";
    }

    @GetMapping("/{id}")
    public String getOneSensor(@PathVariable("id") Long id, Model model) {
        Optional <Sensor> sensorById = Optional.ofNullable(sensorService.getSensorById(id));
        if (sensorById.isPresent()) {
            model.addAttribute("sensor", sensorById.get());
            return "sensors/sensor";
        } else {
            return "redirect:/list-sensors";
        }
    }

    @GetMapping("/new")
    public String createNewSensorForm(@ModelAttribute("sensor") Sensor sensor) {

        return "sensors/create-sensor";
    }

    @PostMapping
    public String createSensor(@ModelAttribute("sensor") @Valid Sensor sensor,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sensors/create-sensor";
        }

        sensorService.createSensor(sensor);
        return "redirect:/list-sensors";
    }

    @GetMapping("/{id}/edit")
    public String editSensorForm(@PathVariable("id") Long id, Model model) {
        Optional <Sensor> sensorById = Optional.ofNullable(sensorService.getSensorById(id));

        if (sensorById.isPresent()) {
            model.addAttribute("sensor", sensorById.get());
            return "sensors/edit-sensor";
        } else {
            return "redirect:/sensors";
        }
    }

    @PatchMapping("/{id}")
    public String editSensor(@ModelAttribute("sensor") @Valid Sensor sensor, @PathVariable("id") Long id,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sensors/edit-sensor";
        }

        sensorService.updateSensor(id, sensor);
        return "redirect:/sensors";
    }

    @DeleteMapping("/{id}")
    public String deleteSensor(@PathVariable("id") Long id) {
        sensorService.deleteSensor(id);
        return "redirect:/sensors";
    }


}
