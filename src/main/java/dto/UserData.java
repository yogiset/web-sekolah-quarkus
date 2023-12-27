package dto;

import java.time.Instant;

public record UserData(
        Long id,
        String name,
        String email,
        String password,
        String no_telp,
        String role,
        String status,
        Instant created
) {
}
