package nvt.st.T.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private String accessToken;
    private String refreshToken;
    private Date createdDate;
}
