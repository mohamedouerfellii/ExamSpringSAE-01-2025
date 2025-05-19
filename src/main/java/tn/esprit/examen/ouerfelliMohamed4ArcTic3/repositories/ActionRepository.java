package tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Action;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
    List<Action> findAllByDate(LocalDate date);
}
