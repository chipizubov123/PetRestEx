package ru.Petr.PetRestEx.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return "list-sensors";
    }

    @GetMapping("/{id}")
    public String getOneSensor(@PathVariable("id") Long id, Model model) {
        Optional <Sensor> sensorById = Optional.ofNullable(sensorService.getSensorById(id));
        if (sensorById.isPresent()) {
            model.addAttribute("sensor", sensorById.get());
            return "sensor";
        } else {
            return "redirect:/list-sensors";
        }
    }

    @GetMapping("/new")
    public String createNewSensorForm(@ModelAttribute("sensor") Sensor sensor) {

        return "create-sensor";
    }

    @PostMapping
    public String createSensor(@ModelAttribute("sensor") @Valid Sensor sensor,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-sensor";
        }

        sensorService.createSensor(sensor);
        return "redirect:/list-sensors";
    }

    @GetMapping("/edit")
    public String editSensorForm(@RequestParam("id") Long id, Model model) {
        Optional <Sensor> sensorById = Optional.ofNullable(sensorService.getSensorById(id));

        if (sensorById.isPresent()) {
            model.addAttribute("sensor", sensorById.get());
            return "edit-sensor";
        } else {
            return "redirect:/sensor";
        }
    }

    @PostMapping("/edit")
    public String editSensor(@ModelAttribute("sensor") @Valid Sensor sensor,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-sensor";
        }

        sensorService.updateSensor(sensor.getId(), sensor);
        return "redirect:/sensor";
    }

    @PostMapping("/delete")
    public String deleteSensor(@RequestParam("id") Long id) {
        sensorService.deleteSensor(id);
        return "redirect:/sensor";
    }


}
