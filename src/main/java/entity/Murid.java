package entity;

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
public class Murid {
    @Id
    @SequenceGenerator(name = "muridSeq", sequenceName = "murid_sequence", allocationSize = 1,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "muridSeq")
    private Long id;
    private String nama;
    private String namawali;
    private String no_telp;
    private String nis;
    private LocalDate tgl_lahir;
    private Integer umur;
    private String alamat;
    @Column(columnDefinition="text")
    private String foto;

    public Integer getUmur() {
        return Period.between(this.tgl_lahir,LocalDate.now()).getYears();
    }

}
