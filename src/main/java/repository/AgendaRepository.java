package repository;

import entity.Agenda;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgendaRepository implements PanacheRepository<Agenda> {
}
