package by.sergo.identityservice.service;

import by.sergo.identityservice.domain.dto.UserCredentialCreateRequestDto;
import by.sergo.identityservice.mapper.user.UserCreateMapper;
import by.sergo.identityservice.repository.UserCredentialsRepository;
import by.sergo.identityservice.service.exception.ExceptionMessageUtil;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserCredentialsRepository userCredentialsRepository;
    private final UserCreateMapper userCreateMapper;
    private final JwtService jwtService;


    @Transactional
    public String saveUser(UserCredentialCreateRequestDto credential) {
        checkEmailIsUnique(credential.getEmail());
        checkUsernameIsUnique(credential.getUsername());

        var userCredential = userCreateMapper.mapToEntity(credential);
        userCredentialsRepository.saveAndFlush(userCredential);

        return "User added to the system";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


    private void checkUsernameIsUnique(String username) {
        if (userCredentialsRepository.existsByUsername(username)) {
            throw new BadRequestException(ExceptionMessageUtil.getAlreadyExistsMessage("User", "username",  username));
        }
    }

    private void checkEmailIsUnique(String email) {
        if (userCredentialsRepository.existsByEmail(email)) {
            throw new BadRequestException(ExceptionMessageUtil.getAlreadyExistsMessage("User", "email", email));
        }
    }
}
