package nvt.st.T.dto.auth;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String token;
    private String email;
    private String password;
}
