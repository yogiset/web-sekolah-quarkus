package entity;

import io.quarkus.arc.All;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    @Id
    @SequenceGenerator(name = "staffSeq", sequenceName = "staff_sequence", allocationSize = 1,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "staffSeq")
    private Long id;
    private String nama;
    private String nip;
    private String jabatan;
    private String alamat;
    private LocalDate tgl_lahir;
    private Integer umur;
    @Column(columnDefinition="text")
    private String foto;

    public Integer getUmur() {
        return Period.between(this.tgl_lahir,LocalDate.now()).getYears();
    }
}
