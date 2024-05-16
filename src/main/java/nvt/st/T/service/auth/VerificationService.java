package nvt.st.T.service.auth;

import nvt.st.T.dto.auth.RegistrationRequest;
import nvt.st.T.dto.auth.ResetPasswordRequest;
import nvt.st.T.dto.user.UserRequest;

public interface VerificationService {
    Long generateTokenVerify(UserRequest userRequest);

    void resendRegistrationToken(Long userId);

    void confirmRegistration(RegistrationRequest registration);

    void changeRegistrationEmail(Long userId, String emailUpdate);

    void forgetPassword(String email);

    void resetPassword(ResetPasswordRequest resetPasswordRequest);
}
