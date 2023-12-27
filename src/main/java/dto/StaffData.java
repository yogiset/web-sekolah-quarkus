package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.time.LocalDate;

public record StaffData(
        Long id,
        String nama,
        String nip,
        String jabatan,
        String alamat,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate tgl_lahir,
        Integer umur,
        @Column(columnDefinition="text")
        String foto

) {
}
