package dto;

import java.time.Instant;

public record UpdateUserData(
        String name,
        String email,
        String oldpassword,
        String newpassword,
        String no_telp,
        String role,
        String status
) {
}
