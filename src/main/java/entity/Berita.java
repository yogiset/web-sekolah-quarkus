package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Berita {
    @Id
    @SequenceGenerator(name = "beritaSeq",sequenceName = "berita_sequence",allocationSize = 1,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "beritaSeq")
    private Long id;
    private String judulberita;
    private String tanggal;
    private LocalDate tanggalberita;
    @Column(columnDefinition="text")
    private String image;
    private String deskripsi;
    private String kategori;
    private Instant created;
}
