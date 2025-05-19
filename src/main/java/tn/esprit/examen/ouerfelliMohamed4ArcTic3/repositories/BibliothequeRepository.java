package tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Bibliotheque;

import java.util.List;
import java.util.Optional;

@Repository
public interface BibliothequeRepository extends JpaRepository<Bibliotheque, Long> {
    Optional<Bibliotheque> findByLibelleEquals(String libelle);

    @Query("SELECT l.titre FROM Bibliotheque b " +
            "JOIN b.livres l " +
            "WHERE b.idBibliotheque IN :idsBiblio AND l.disponible = false")
    List<String> listerLivresTitresParBibliothequesIds(@Param("idsBiblio") List<Long> idsBiblio);

}
