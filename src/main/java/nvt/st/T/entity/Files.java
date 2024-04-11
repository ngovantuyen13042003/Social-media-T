package nvt.st.T.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import nvt.st.T.entity.chats.Messages;
import nvt.st.T.entity.post.Pages;
import nvt.st.T.entity.post.Posts;

@Setter
@Getter
@Entity
public class Files extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private Messages messages;

    @ManyToOne
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private Pages pages;

    @Column(name = "url_file")
    private String urlFile;
}
