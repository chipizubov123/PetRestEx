package ru.Petr.PetRestEx.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "workshop")
@RequiredArgsConstructor
@Getter
@Setter
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "workshop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Sensor> sensorList;

    @Override
    public String toString() {
        return "Workshop " + name;
    }
}
