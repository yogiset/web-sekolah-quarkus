package entity;

import io.quarkus.arc.All;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agenda {
    @Id
    @SequenceGenerator(name = "agendaSeq", sequenceName = "agenda_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agendaSeq")
    private Long id;
    private String judul;
    private String tanggal;
    private LocalDate tanggals;
    private String tempat;
    private String deskripsi;

}
