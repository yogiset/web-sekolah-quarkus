package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.time.LocalDate;

public record MuridData(
        Long id,
        String nama,
        String namawali,
        String no_telp,
        String nis,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate tgl_lahir,
        Integer umur,
        String alamat,
        @Column(columnDefinition="text")
        String foto

) {
}
