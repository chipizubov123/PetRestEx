package ru.Petr.PetRestEx.controller;


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
import ru.Petr.PetRestEx.model.Workshop;
import ru.Petr.PetRestEx.service.SensorService;
import ru.Petr.PetRestEx.service.WorkshopService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sensors")
public class SensorMVCController {

    private final SensorService sensorService;

    private final WorkshopService workshopService;


    @Autowired
    public SensorMVCController(SensorService sensorService, WorkshopService workshopService) {
        this.sensorService = sensorService;
        this.workshopService = workshopService;
    }

    @GetMapping
    public String allSensors(Model model) {
        List<Sensor> sensors = sensorService.getAllSensors();
        model.addAttribute("sensors", sensors);
        return "sensors/list-sensors";
    }

    @GetMapping("/{id}")
    public String getOneSensor(@PathVariable("id") Long id, Model model) {
        Optional<Sensor> sensorById = Optional.ofNullable(sensorService.getSensorById(id));
        if (sensorById.isPresent()) {
            model.addAttribute("sensor", sensorById.get());
            return "sensors/sensor";
        } else {
            return "redirect:/list-sensors";
        }
    }

    @GetMapping("/new")
    public String createNewSensorForm(@ModelAttribute("sensor") Sensor sensor, Model model) {
        model.addAttribute("workshops", workshopService.getAllWorkshop());
        return "sensors/create-sensor";
    }

    @PostMapping
    public String createSensor(@ModelAttribute("sensor") Sensor sensor, @RequestParam(name = "selected") Long id,
                               BindingResult bindingResult, Model model) {
        model.addAttribute("workshops", workshopService.getAllWorkshop());
        if (bindingResult.hasErrors()) {

            return "sensors/create-sensor";
        }
        Optional<Workshop> workshopOptional = workshopService.getWorkshopById(id);
        if (workshopOptional.isPresent()) {
            sensor.setWorkshop(workshopOptional.get());
        } else {
            model.addAttribute("error", "Мастерская не найдена!");
            return "sensors/create-sensor";
        }

        sensorService.createSensor(sensor);
        return "redirect:/sensors";
    }

    @GetMapping("/{id}/edit")
    public String editSensorForm(@PathVariable("id") Long id, Model model) {
        Optional<Sensor> sensorById = Optional.ofNullable(sensorService.getSensorById(id));

        if (sensorById.isPresent()) {
            model.addAttribute("sensor", sensorById.get());
            model.addAttribute("workshops", workshopService.getAllWorkshop());
            return "sensors/edit-sensor";
        } else {
            return "redirect:/sensors";
        }
    }

    @PatchMapping("/{id}")
    public String editSensor(@ModelAttribute("sensor") Sensor sensor, @PathVariable("id") Long id,
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
