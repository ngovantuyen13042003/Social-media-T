package nvt.st.T.dto.auth;

import lombok.Data;

@Data
public class RegistrationRequest {
    private Long userId;
    private String token;
}
