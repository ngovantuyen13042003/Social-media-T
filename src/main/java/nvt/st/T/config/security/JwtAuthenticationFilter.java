package nvt.st.T.config.security;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nvt.st.T.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailService userDetailService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // get token from request of user
    public String getTokenFromRequest(HttpServletRequest request) {
        // Authorization: Bearer + token
        String bearerToken = request.getHeader(SecurityConstants.AUTHORIZATION);
        // start index 7:  due to  Bearer 6 ký tự + 1 ký tự khoảng trắng
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(SecurityConstants.BEARER)){
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        // get username
        String username = jwtTokenProvider.extractUsername(token);
        // check user authenticated and validate
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null && jwtTokenProvider.validateJwtToken(token)) {
            // load UserDetails
            UserDetails userDetails = userDetailService.loadUserByUsername(username);


            if (jwtTokenProvider.isValid(token, userDetails)) {
                // use info of UserDetails to create UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, // Principal: details info abount user (UserDetails)
                        null,        // Credentials: password or token
                        userDetails.getAuthorities() // Authorities: Roles of user
                );

                // set details of request from user
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // update SecurityContextHolder abount object new create Authentication
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }



}
