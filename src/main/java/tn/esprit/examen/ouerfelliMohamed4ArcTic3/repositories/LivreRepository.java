package tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Action;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Livre;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.enums.TypeAction;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    Optional<Livre> findByIsbnEquals(String isbn);
    @Query("SELECT a FROM Livre l JOIN l.actions a " +
            "WHERE a.typeAction = :typeAction AND l.titre = :titre AND l.auteur = :auteur")
    List<Action> listerActionParTypeEtLivreAuteur(
            @Param("typeAction") TypeAction typeAction,
            @Param("titre") String titre,
            @Param("auteur") String auteur);
}
