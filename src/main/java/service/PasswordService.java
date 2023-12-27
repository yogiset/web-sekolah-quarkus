package service;

import jakarta.enterprise.context.ApplicationScoped;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class PasswordService {
    public String hashPassword(String plainPassword) {
        // Hash the password
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        // Verify the password
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
