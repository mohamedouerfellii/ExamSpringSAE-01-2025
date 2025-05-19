package tn.esprit.examen.ouerfelliMohamed4ArcTic3.services;

import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Action;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Bibliotheque;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Livre;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.enums.TypeAction;

import java.util.List;

public interface IExamenService {
    Bibliotheque ajouterBibliotheque(Bibliotheque bibliotheque);
    Livre ajouterLivreEtAffecterABibliotheque(Livre livre, String libelle);
    String ajouterActionEtAffecterALivre(Action action, String isbn);
    void listerActionParDate();
    List<String> listerLivresTitresParBibliothequesIds(List<Long> idsBiblio);
    List<Action> listerActionParTypeEtLivreAuteur(TypeAction typeAction, String titre, String auteur);
}
