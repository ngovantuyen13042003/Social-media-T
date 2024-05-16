package nvt.st.T.controller.auth;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import nvt.st.T.dto.auth.*;
import nvt.st.T.dto.user.UserResponse;
import nvt.st.T.service.auth.AuthService;
import nvt.st.T.service.auth.VerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    private VerificationService verificationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticationUser(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.authenticateAndGenerateToken(loginRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        String jwt = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(new AuthResponse("Refresh token", jwt, refreshToken, new Date()));
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody SignupRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<ObjectNode> forgotPassword(@RequestParam String email) {
        verificationService.forgetPassword(email);
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectNode(JsonNodeFactory.instance));
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getInfo(Authentication authentication) {
        UserResponse userResponse = authService.getInfo(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

}
