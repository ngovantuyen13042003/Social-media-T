package nvt.st.T.dto.page;

import lombok.Data;
import nvt.st.T.entity.account.User;
import nvt.st.T.entity.post.Pages;

@Data
public class UserPageRequest {
    private User user;
    private Pages pages;
    private String role;
}
