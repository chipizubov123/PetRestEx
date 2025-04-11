package ru.Petr.PetRestEx.controller;

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
import ru.Petr.PetRestEx.model.Workshop;
import ru.Petr.PetRestEx.service.WorkshopService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/workshops")
public class WorkshopRestController {

    private final WorkshopService workshopService;

    @Autowired
    public WorkshopRestController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @GetMapping
    public ResponseEntity<List<Workshop>> getAllWorkshops() {
        List<Workshop> workshops = workshopService.getAllWorkshop();
        return ResponseEntity.ok(workshops);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Workshop> getWorkshopById(@PathVariable("id") Long id) {
        Optional<Workshop> workshopById = workshopService.getWorkshopById(id);
        return workshopById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Workshop> createWorkshop( @RequestBody Workshop workshop) {
        Workshop createdWorkshop = workshopService.createWorkshop(workshop);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkshop);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Workshop> updateWorkshop(@PathVariable("id") Long id, @RequestBody Workshop workshop) {

        Optional<Workshop> updatedWorkshop = workshopService.updateWorkshop(id, workshop);

        return updatedWorkshop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkshop(@PathVariable("id") Long id) {
        workshopService.deleteWorkshop(id);
        return ResponseEntity.noContent().build();
    }
}
