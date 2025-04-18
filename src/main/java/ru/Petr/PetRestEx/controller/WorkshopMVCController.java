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
import ru.Petr.PetRestEx.model.Workshop;
import ru.Petr.PetRestEx.service.WorkshopService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/workshops")
public class WorkshopMVCController {

    private final WorkshopService workshopService;

    @Autowired
    public WorkshopMVCController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }


    @GetMapping
    public String allWorkshops(Model model) {
        List<Workshop> workshops = workshopService.getAllWorkshop();
        model.addAttribute("workshops", workshops);
        return "workshops/list-workshops";
    }

    @GetMapping("/{id}")
    public String getOneWorkshop(@PathVariable("id") Long id, Model model) {
        Optional<Workshop> workshopById = workshopService.getWorkshopById(id);
        if (workshopById.isPresent()) {
            model.addAttribute("workshop", workshopById.get());
            return "workshops/workshop";
        } else {
            return "redirect:/list-workshops";
        }
    }

    @GetMapping("/new")
    public String createNewWorkshopForm(@ModelAttribute("workshop") Workshop workshop) {

        return "workshops/create-workshop";
    }

    @PostMapping
    public String createWorkshop(@ModelAttribute("workshop") Workshop workshop,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "workshops/create-workshop";
        }

        workshopService.createWorkshop(workshop);
        return "redirect:/workshops";
    }

    @GetMapping("/{id}/edit")
    public String editWorkshopForm(@PathVariable("id") Long id, Model model) {
        Optional<Workshop> workshopById = workshopService.getWorkshopById(id);

        if (workshopById.isPresent()) {
            model.addAttribute("workshop", workshopById.get());
            return "workshops/edit-workshop";
        } else {
            return "redirect:/workshop";
        }
    }

    @PatchMapping("/{id}")
    public String editWorkshop(@ModelAttribute("workshop") Workshop workshop, @PathVariable("id") Long id,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "workshops/edit-workshop";
        }

        workshopService.updateWorkshop(id, workshop);
        return "redirect:/workshops";
    }



    @DeleteMapping("/{id}")
    public String deleteWorkshop(@PathVariable("id") Long id) {
        workshopService.deleteWorkshop(id);
        return "redirect:/workshops";
    }
}
