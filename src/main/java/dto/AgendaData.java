package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record AgendaData(
        Long id,
        String judul,
        String tanggal,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate tanggals,
        String tempat,
        String deskripsi
) {
}
