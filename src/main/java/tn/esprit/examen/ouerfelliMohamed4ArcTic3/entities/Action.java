package tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.enums.TypeAction;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idAction;
    LocalDate date;
    @Enumerated(EnumType.STRING)
    TypeAction typeAction;
}
