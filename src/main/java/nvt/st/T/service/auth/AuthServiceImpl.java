package nvt.st.T.service.auth;

import nvt.st.T.config.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import nvt.st.T.constants.AppConstants;
import nvt.st.T.dto.auth.LoginRequest;
import nvt.st.T.dto.auth.SignupRequest;
import nvt.st.T.dto.auth.AuthResponse;
import nvt.st.T.dto.user.UserResponse;
import nvt.st.T.entity.account.*;
import nvt.st.T.exception.RefreshTokenException;
import nvt.st.T.exception.VerificationException;
import nvt.st.T.mapper.authentication.UserMapper;
import nvt.st.T.repository.auth.UserRepository;
import nvt.st.T.repository.auth.VerificationRepository;
import nvt.st.T.service.authentication.RefreshTokenService;
import nvt.st.T.service.email.EmailSenderService;
import nvt.st.T.service.role.RoleService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
    private AuthenticationManager authenticationManager;
    private RefreshTokenService refreshTokenService;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private UserRepository userRepository;
    private VerificationRepository verificationRepository;
    private EmailSenderService emailSenderService;
    private UserMapper userMapper;
    @Override
    public AuthResponse authenticateAndGenerateToken(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenProvider.generateToken(authentication.getName());
        String refreshToken = refreshTokenService.createRefreshToken(authentication).getToken();

        return new AuthResponse("Login success!", accessToken, refreshToken, new Date());
    }

    @Override
    public AuthResponse register(SignupRequest request) {

        // (1) check username exists in database
        if(userRepository.existsUserByUsername(request.getUsername())) {
            throw new VerificationException("Username is existing!");
        }

        // (2) check email exists in database
        if(userRepository.existsUserByEmail(request.getEmail())) {
            throw new VerificationException("Email is existing!");
        }

        // (3) Create user entity with status 2 (non-verified) and set role (role default: user)
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Collection<String> strRoles = request.getRoles();
        Collection<Role> listRoles = new ArrayList<>();

        Collection<User> listUsers = new ArrayList<>() ;

        listUsers.add(user);
        if(listRoles == null) {
            // set role default : user
            Role userRole = roleService.findByName(ERole.USER).orElseThrow(() -> new RuntimeException("Error: Role if not found!"));
            listRoles.add(userRole);
        }else {
            strRoles.forEach(role->{
                switch (role){
                    case "admin":
                        Role adminRole = roleService.findByName(ERole.ADMIN)
                                .orElseThrow(()-> new RuntimeException("Error: Role is not found!"));
                        listRoles.add(adminRole);
                        break;
                    case "moderator":
                        Role mdRole = roleService.findByName(ERole.MODERATOR)
                                .orElseThrow(()->new RuntimeException("Error: Role is not found!"));
                        listRoles.add(mdRole);
                        break;
                    case "user":
                        Role userRole = roleService.findByName(ERole.USER)
                                .orElseThrow(()->new RuntimeException("Error: Role is not found!"));
                        listRoles.add(userRole);
                        break;
                }
            });
        }
        user.setRoles(listRoles);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user = userRepository.save(user);

        // (4) Create new verification entity and set user, token
        Verification verification = new Verification();
        String token  = generateVerificationToken();
        verification.setToken(token);
        verification.setUser(user);
        verification.setType(VerificationType.REGISTRATION);

        Date expirationDate = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
        verification.setExpired_at(expirationDate);

        verificationRepository.save(verification);


        // (5) Send email
        Map<String, Object> attribites = Map.of(
            "token", token,
            "link", MessageFormat.format("{0}/signup?userId={1}", AppConstants.FRONTEND_HOST, user.getId())
        );

        emailSenderService.sendVerificationToken(user.getEmail(), attribites);


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenProvider.generateToken(authentication.getName());
        String refreshToken = "Đăng ký tài khoản đéo cần refresh token";

        return new AuthResponse("register success!", accessToken, refreshToken, new Date());
    }

    @Override
    public String refreshToken(String token) {
        String jwt = refreshTokenService.findByToken(token)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(User::getUsername)
                .map(jwtTokenProvider::generateToken)
                .orElseThrow(() -> new RefreshTokenException("Refresh token was expired. Please make a new signin request!"));
        return jwt;
    }

    @Override
    public UserResponse getInfo(Authentication authentication) {
        String username = authentication.getName();

        UserResponse userResponse = userRepository.findByUsername(username)
                .map(userMapper::entityToResponse)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return userResponse;
    }


    private String generateVerificationToken() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }


}

















