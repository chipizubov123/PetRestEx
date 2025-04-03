package ru.Petr.PetRestEx.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "sensor")
@RequiredArgsConstructor
@Getter
@Setter
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workshop_id", nullable = false)
    @JsonBackReference
    private Workshop workshop;


    private String metric;


    private double value;


    @Override
    public String toString() {
        return "Sensor{" +
               "id=" + id +
               ", workshop=" + workshop.getName() +
               ", metric='" + metric + '\'' +
               ", value=" + value +
               '}';
    }
}


