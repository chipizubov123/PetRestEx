package ru.Petr.PetRestEx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Petr.PetRestEx.model.Workshop;
import ru.Petr.PetRestEx.repository.WorkshopRepository;

import java.util.List;

@Service
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    public Workshop createWorkshop(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    public Workshop getWorkshopById(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workshop not found"));
    }

    public Workshop updateWorkshop(Long id, Workshop updatedWorkshop) {
        Workshop existingWorkshop = workshopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workshop not found"));

        existingWorkshop.setName(updatedWorkshop.getName());

        return workshopRepository.save(existingWorkshop);
    }

    public List<Workshop> getAllWorkshop() {
        List <Workshop> workshops = (List<Workshop>) workshopRepository.findAll();
        workshops.forEach(w -> w.getSensorList().size());
        return workshops;
    }

    public void deleteWorkshop(Long id) {
        workshopRepository.deleteById(id);
    }

}
