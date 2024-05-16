package nvt.st.T.service.auth;

import lombok.AllArgsConstructor;
import nvt.st.T.constants.AppConstants;
import nvt.st.T.dto.auth.RegistrationRequest;
import nvt.st.T.dto.auth.ResetPasswordRequest;
import nvt.st.T.dto.user.UserRequest;
import nvt.st.T.entity.account.User;
import nvt.st.T.exception.ExpiredTokenException;
import nvt.st.T.exception.VerificationException;
import nvt.st.T.repository.auth.UserRepository;
import nvt.st.T.service.email.EmailSenderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Map;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = VerificationException.class, noRollbackFor = ExpiredTokenException.class)
public class VerificationServiceImpl implements VerificationService{
    private UserRepository userRepository;
    private EmailSenderService emailSenderService;

    @Override
    public Long generateTokenVerify(UserRequest userRequest) {
        return null;
    }

    @Override
    public void resendRegistrationToken(Long userId) {

    }

    @Override
    public void confirmRegistration(RegistrationRequest registration) {

    }

    @Override
    public void changeRegistrationEmail(Long userId, String emailUpdate) {

    }

    @Override
    public void forgetPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->  new RuntimeException("Email doesn't exist"));

        if(user.getEnabled()) {
            String token = RandomStringUtils.random(10);
            user.setResetPasswordToken(token);
            userRepository.save(user);

            String link = MessageFormat.format("{0}/change-password?token={1}&email={2}", AppConstants.FRONTEND_HOST, token, email);
            emailSenderService.sendForgetPasswordToken(user.getEmail(), Map.of("link", link));
        }else  {
            throw new VerificationException("Account is not enabled!");
        }
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {

    }
}
