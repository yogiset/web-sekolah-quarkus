package repository;

import entity.Berita;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BeritaRepository implements PanacheRepository<Berita> {
}
