package nvt.st.T.entity.post;

import jakarta.persistence.*;
import lombok.Data;
import nvt.st.T.entity.BaseEntity;
import nvt.st.T.entity.account.ERole;
import nvt.st.T.entity.account.User;

@Entity
@Data
@Table(name = "user_page")
public class User_Page extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ERole role;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private Pages pages;

}
