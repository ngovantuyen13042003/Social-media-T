package nvt.st.T.entity.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import nvt.st.T.entity.BaseEntity;
import nvt.st.T.entity.EPrivacy;

@Data
@Entity
public class Pages extends BaseEntity {
    @Column(name = "page_name")
    private String name;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private EPrivacy privacy;
}
