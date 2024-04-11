package nvt.st.T.entity.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nvt.st.T.entity.BaseEntity;
import nvt.st.T.entity.EPrivacy;
import nvt.st.T.entity.account.User;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posts extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private EPrivacy privacy;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Pages pages;

}
