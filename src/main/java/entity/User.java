package entity;

import io.quarkus.arc.All;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "userr")
public class User {
    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userSeq")
    private Long id;
    private String name;
    private String email;
    private String password;
    private String no_telp;
    private String role;
    private String status;
    private Instant created;
}
