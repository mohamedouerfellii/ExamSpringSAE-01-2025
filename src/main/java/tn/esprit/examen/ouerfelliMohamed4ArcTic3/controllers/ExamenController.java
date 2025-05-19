package tn.esprit.examen.ouerfelliMohamed4ArcTic3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Action;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Bibliotheque;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Livre;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.enums.TypeAction;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.services.IExamenService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExamenController {
    private final IExamenService service;

    @PostMapping("add-biblio")
    public Bibliotheque ajouterBibliotheque(@RequestBody Bibliotheque bibliotheque) {
        return service.ajouterBibliotheque(bibliotheque);
    }

    @PostMapping("add-book-affect-biblio/{libelle}")
    public Livre ajouterLivreEtAffecterABibliotheque(
            @RequestBody Livre livre, @PathVariable("libelle") String libelle) {
        return service.ajouterLivreEtAffecterABibliotheque(livre, libelle);
    }

    @PostMapping("add-action/{isbn}")
    public String ajouterActionEtAffecterALivre(@RequestBody Action action,
                                                @PathVariable("isbn") String isbn) {
        return service.ajouterActionEtAffecterALivre(action, isbn);
    }

    @GetMapping("get-book-names")
    public List<String> listerLivresTitresParBibliothequesIds(@RequestParam List<Long> idsBiblio) {
        return service.listerLivresTitresParBibliothequesIds(idsBiblio);
    }

    @GetMapping("get-actions/{typeAction}/{titre}/{auteur}")
    public List<Action> listerActionParTypeEtLivreAuteur(
            @PathVariable("typeAction") TypeAction typeAction,
            @PathVariable("titre") String titre,
            @PathVariable("auteur") String auteur) {
        return service.listerActionParTypeEtLivreAuteur(typeAction, titre, auteur);
    }
}
