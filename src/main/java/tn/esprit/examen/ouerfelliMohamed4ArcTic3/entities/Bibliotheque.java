package tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bibliotheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idBibliotheque;
    String libelle;
    String adresse;
    @OneToMany(cascade = {CascadeType.PERSIST})
    Set<Livre> livres;
}
