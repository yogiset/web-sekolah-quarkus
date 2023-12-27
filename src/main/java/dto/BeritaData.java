package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.time.Instant;
import java.time.LocalDate;

public record BeritaData(
        Long id,
        String judulberita,
        String tanggal,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate tanggalberita,
        @Column(columnDefinition="text")
        String image,
        String deskripsi,
        String kategori,
        Instant created

) {
}
