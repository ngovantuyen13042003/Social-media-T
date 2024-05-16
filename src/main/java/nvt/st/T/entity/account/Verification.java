package nvt.st.T.entity.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nvt.st.T.entity.BaseEntity;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "verification")
public class Verification extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expired_at", nullable = false)
    private Date expired_at;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VerificationType type;
}
