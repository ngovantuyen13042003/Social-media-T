package nvt.st.T.dto.file;

import lombok.Data;
import nvt.st.T.entity.chats.Messages;
import nvt.st.T.entity.post.Pages;
import nvt.st.T.entity.post.Posts;

@Data
public class FileRequest {
    private String urlFile;
    private Pages pages;
    private Messages messages;
    private Posts posts;
}
