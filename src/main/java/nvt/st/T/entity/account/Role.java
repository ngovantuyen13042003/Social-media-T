package nvt.st.T.entity.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collection;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    private ERole name;
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}