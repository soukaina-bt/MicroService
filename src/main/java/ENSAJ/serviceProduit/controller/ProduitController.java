package ENSAJ.serviceProduit.controller;



import ENSAJ.serviceProduit.dto.ProduitRequest;
import ENSAJ.serviceProduit.dto.ProduitResponse;
import ENSAJ.serviceProduit.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produit")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduit(@RequestBody ProduitRequest produitRequest) {
        this.produitService.createProduit(produitRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProduitResponse> getAllProduit() {
        return produitService.getAllProduit();
    }

    // ----- Travail à faire -----
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProduitResponse updateProduit(@PathVariable String id,
                                         @RequestBody ProduitRequest produitRequest) {
        return produitService.updateProduit(id, produitRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduit(@PathVariable String id) {
        produitService.deleteProduit(id);
    }
}
