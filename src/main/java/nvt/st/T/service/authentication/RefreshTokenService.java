package nvt.st.T.service.authentication;

import nvt.st.T.entity.account.RefreshToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(Authentication authentication);
    RefreshToken verifyExpiration(RefreshToken refreshToken);
}
