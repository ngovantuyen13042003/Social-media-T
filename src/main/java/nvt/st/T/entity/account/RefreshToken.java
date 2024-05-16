package nvt.st.T.entity.account;

import jakarta.persistence.*;
import lombok.*;
import nvt.st.T.entity.BaseEntity;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "refresh_token")
public class RefreshToken  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "token",unique = true, nullable = false)
    private String token;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(nullable = false)
    private Date expiryDate;

}
