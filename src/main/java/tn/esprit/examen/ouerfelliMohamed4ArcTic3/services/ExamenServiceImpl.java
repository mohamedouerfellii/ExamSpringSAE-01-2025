package tn.esprit.examen.ouerfelliMohamed4ArcTic3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Action;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Bibliotheque;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Livre;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.enums.TypeAction;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories.ActionRepository;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories.BibliothequeRepository;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories.LivreRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamenServiceImpl implements IExamenService {

    private final BibliothequeRepository bibliothequeRepository;
    private final LivreRepository livreRepository;
    private final ActionRepository actionRepository;

    @Override
    public Bibliotheque ajouterBibliotheque(Bibliotheque bibliotheque) {
        return bibliothequeRepository.save(bibliotheque);
    }

    @Override
    public Livre ajouterLivreEtAffecterABibliotheque(Livre livre, String libelle) {
        Bibliotheque biblio = bibliothequeRepository.findByLibelleEquals(libelle).orElseThrow(
                () -> new IllegalArgumentException("Biblio not found")
        );
        livre = livreRepository.save(livre);
        biblio.getLivres().add(livre);
        bibliothequeRepository.save(biblio);
        return livre;
    }

    @Override
    public String ajouterActionEtAffecterALivre(Action action, String isbn) {
        Livre livre = livreRepository.findByIsbnEquals(isbn).orElseThrow(
                () -> new IllegalArgumentException("Book not found")
        );
        if (livre.isDisponible()) {
            if(action.getTypeAction().equals(TypeAction.EMPRUNT)) {
                livre.setDisponible(false);
                action.setDate(LocalDate.now());
                livre.getActions().add(actionRepository.save(action));
                livreRepository.save(livre);
                return "EMPRUNT de livre " + isbn + " approuvé avec succès";
            } else
                return "Le livre " + isbn + " n est pas emprunté";
        } else {
            if(action.getTypeAction().equals(TypeAction.EMPRUNT))
                return "Le livre " + isbn + " est déja emprunté";
            else {
                livre.setDisponible(true);
                action.setDate(LocalDate.now());
                livre.getActions().add(actionRepository.save(action));
                livreRepository.save(livre);
                return "RETOUR de livre " + isbn + " approuvé avec succès";
            }
        }
    }

    @Scheduled(fixedDelay = 30000)
    @Override
    @Transactional
    public void listerActionParDate() {
        List<Action> actions = actionRepository.findAllByDate(LocalDate.now());
        log.info("les actions d aujourd hui sont :");
        for (Action action : actions) {
            log.info("action de type " + action.getTypeAction() + " planifiée le " + action.getDate());
        }
    }

    @Override
    public List<String> listerLivresTitresParBibliothequesIds(List<Long> idsBiblio) {
        return bibliothequeRepository.listerLivresTitresParBibliothequesIds(idsBiblio);
    }

    @Override
    public List<Action> listerActionParTypeEtLivreAuteur(TypeAction typeAction, String titre, String auteur) {
        return livreRepository.listerActionParTypeEtLivreAuteur(typeAction, titre, auteur);
    }
}
