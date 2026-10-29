package ENSAJ.serviceProduit.repository;

import ENSAJ.serviceProduit.Model.Produit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProduitRepository extends MongoRepository<Produit, String> {
}