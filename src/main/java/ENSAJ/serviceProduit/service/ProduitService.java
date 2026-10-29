package ENSAJ.serviceProduit.service;

import ENSAJ.serviceProduit.dto.ProduitRequest;
import ENSAJ.serviceProduit.dto.ProduitResponse;
import ENSAJ.serviceProduit.Model.Produit;
import ENSAJ.serviceProduit.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
// crée automatiquement une instance de Logger de la bibliothèque SLF4J
// (Simple Logging Facade for Java) dans votre classe. Cela signifie que
// vous n'avez pas besoin de déclarer et d'initialiser manuellement un Logger.
@Slf4j
public class ProduitService {

    private final ProduitRepository produitRepository;

    public void createProduit(ProduitRequest produitRequest) {
        Produit produit = Produit.builder()
                .libelle(produitRequest.getLibelle())
                .prix(produitRequest.getPrix())
                .description(produitRequest.getDescription())
                .build();
        produitRepository.save(produit);
        log.info("le Produit {} est enregistré", produit.getId());
    }

    public List<ProduitResponse> getAllProduit() {
        List<Produit> produits = this.produitRepository.findAll();
        // on peut appeler la méthode par sa référence et ajouter ::
        // toList pour mapper dans le type List
        return produits.stream().map(this::mapToProduitResponse).toList();
    }

    // ----- Travail à faire : modification -----
    public ProduitResponse updateProduit(String id, ProduitRequest produitRequest) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produit introuvable : " + id));

        produit.setLibelle(produitRequest.getLibelle());
        produit.setDescription(produitRequest.getDescription());
        produit.setPrix(produitRequest.getPrix());

        produitRepository.save(produit);
        log.info("le Produit {} est modifié", id);
        return mapToProduitResponse(produit);
    }

    // ----- Travail à faire : suppression -----
    public void deleteProduit(String id) {
        if (!produitRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Produit introuvable : " + id);
        }
        produitRepository.deleteById(id);
        log.info("le Produit {} est supprimé", id);
    }

    private ProduitResponse mapToProduitResponse(Produit produit) {
        return ProduitResponse.builder()
                .id(produit.getId())
                .libelle(produit.getLibelle())
                .prix(produit.getPrix())
                .description(produit.getDescription())
                .build();
    }
}