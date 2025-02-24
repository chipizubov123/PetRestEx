package ru.Petr.PetRestEx.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Petr.PetRestEx.model.Inspector;
import ru.Petr.PetRestEx.model.Sensor;
import ru.Petr.PetRestEx.repository.InspectorRepository;

@Service
public class InspectorService {

    @Autowired
    private InspectorRepository inspectorRepository;


    public Inspector createInspector(Inspector inspector) {
        return inspectorRepository.save(inspector);
    }

    public Inspector getInspectorById(Long id) {
        return inspectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspector not found"));
    }

    public Inspector updateInspector(Long id, Inspector updatedInspector) {
        Inspector existingInspector = inspectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspector not found"));

        existingInspector.setUsername(updatedInspector.getUsername());
        existingInspector.setEmail(updatedInspector.getEmail());
        existingInspector.setPassword(updatedInspector.getPassword());

        return inspectorRepository.save(existingInspector);
    }

    public void deleteInspector(Long id) {
        inspectorRepository.deleteById(id);
    }


}
