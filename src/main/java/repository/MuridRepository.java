package repository;

import entity.Murid;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MuridRepository implements PanacheRepository<Murid> {
}
