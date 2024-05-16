package nvt.st.T.entity.post;

import jakarta.persistence.*;
import lombok.Data;
import nvt.st.T.entity.BaseEntity;
import nvt.st.T.entity.EPrivacy;
import nvt.st.T.entity.Files;
import nvt.st.T.entity.Likes;

import java.util.List;

@Data
@Entity
public class Pages extends BaseEntity {
    @Column(name = "page_name")
    private String name;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private EPrivacy privacy;

    @OneToMany(mappedBy = "pages")
    private List<Files> files;

    @OneToMany(mappedBy = "pages")
    private List<Posts> posts;

    @OneToMany(mappedBy = "pages")
    private List<Likes> likes;

    @OneToMany(mappedBy = "pages")
    private List<User_Page> userPages;
}
