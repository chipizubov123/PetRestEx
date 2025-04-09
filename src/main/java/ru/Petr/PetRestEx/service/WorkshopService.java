package ru.Petr.PetRestEx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.Petr.PetRestEx.model.Workshop;
import ru.Petr.PetRestEx.repository.WorkshopRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopService {

    private final WorkshopRepository workshopRepository;


    @Autowired
    public WorkshopService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;

    }

    public Workshop createWorkshop(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    public Optional<Workshop> getWorkshopById(Long id) {
        return workshopRepository.findById(id);
    }

    public Optional<Workshop> updateWorkshop(Long id, Workshop updatedWorkshop) {
        return workshopRepository.findById(id)
                .map(existingWorkshop -> {
                    existingWorkshop.setName(updatedWorkshop.getName());
                    return workshopRepository.save(existingWorkshop);
                });
    }

    public List<Workshop> getAllWorkshop() {
        return (List<Workshop>) workshopRepository.findAll();
    }

    public void deleteWorkshop(Long id) {
        workshopRepository.deleteById(id);
    }
}
