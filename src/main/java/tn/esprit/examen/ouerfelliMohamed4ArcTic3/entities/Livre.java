package tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idLivre;
    String titre;
    String auteur;
    String isbn;
    boolean disponible;
    @ManyToOne
    @JsonIgnore
    Bibliotheque bibliotheque;
    @OneToMany(cascade = {CascadeType.PERSIST})
    Set<Action> actions;
}

