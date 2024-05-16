package nvt.st.T.entity.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    private ERole name;
//    @Column(name = "description",nullable = true)
//    private String description;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new ArrayList<>();
}