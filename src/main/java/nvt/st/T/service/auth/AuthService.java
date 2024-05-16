package nvt.st.T.service.auth;

import nvt.st.T.dto.auth.LoginRequest;
import nvt.st.T.dto.auth.SignupRequest;
import nvt.st.T.dto.auth.AuthResponse;
import nvt.st.T.dto.user.UserResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {
    AuthResponse authenticateAndGenerateToken(LoginRequest loginRequest);
    AuthResponse register(SignupRequest request);

    String refreshToken(String token);

    UserResponse getInfo(Authentication authentication);
}
