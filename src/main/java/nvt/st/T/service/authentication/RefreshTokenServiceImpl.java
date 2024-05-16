package nvt.st.T.service.authentication;

import lombok.RequiredArgsConstructor;
import nvt.st.T.entity.account.RefreshToken;
import nvt.st.T.exception.RefreshTokenException;
import nvt.st.T.exception.ResourceNotFoundException;
import nvt.st.T.repository.auth.UserRepository;
import nvt.st.T.repository.authentication.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService{
    @Value("${com.app.jwt.jwtRefreshExpiration}")
    private int jwtRefreshExpiration;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(Authentication authentication) {
        String username = authentication.getName();
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username))
        );

        long currentDate = new Date().getTime();
        Date expirationDate = new Date(currentDate + jwtRefreshExpiration);

        refreshToken.setExpiryDate(expirationDate);
        refreshToken.setToken(UUID.randomUUID().toString());

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if(refreshToken.getExpiryDate().compareTo(new Date()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException("Refresh token was expired. Please make a new signin request!");
        }
        return refreshToken;
    }

}
