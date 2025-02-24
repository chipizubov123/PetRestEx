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
import ru.Petr.PetRestEx.model.Inspector;
import ru.Petr.PetRestEx.model.Sensor;
import ru.Petr.PetRestEx.service.InspectorService;
import ru.Petr.PetRestEx.service.SensorService;

@RestController
@RequestMapping("/api/inspectors")
public class InspectorController {
    @Autowired
    private InspectorService inspectorService;

    @PostMapping
    public Inspector createInspector(@RequestBody Inspector inspector) {
        return inspectorService.createInspector(inspector);
    }

    @GetMapping("/{id}")
    public Inspector getInspectorById(@PathVariable Long id) {
        return inspectorService.getInspectorById(id);
    }

    @PutMapping("/{id}")
    public Inspector updateInspector(@PathVariable Long id, @RequestBody Inspector inspector) {
        return inspectorService.updateInspector(id, inspector);
    }

    @DeleteMapping("/{id}")
    public void deleteInspector(@PathVariable Long id) {
        inspectorService.deleteInspector(id);
    }


}
