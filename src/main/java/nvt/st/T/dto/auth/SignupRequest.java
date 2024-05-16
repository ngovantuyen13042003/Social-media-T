package nvt.st.T.dto.auth;

import lombok.Data;
import nvt.st.T.entity.account.Role;

import java.util.Collection;
import java.util.List;

@Data
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private Collection<String> roles;
}
