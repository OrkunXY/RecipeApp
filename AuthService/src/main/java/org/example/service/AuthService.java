package org.example.service;

import org.example.dto.request.*;
import org.example.dto.response.RegisterResponseDto;
import org.example.exception.AuthManagerException;
import org.example.exception.ErrorType;
import org.example.manager.IUserProfileManager;
import org.example.mapper.IAuthMapper;
import org.example.repository.IAuthRepository;
import org.example.repository.Auth;
import org.example.repository.enums.EStatus;
import org.example.utility.JwtTokenProvider;
import org.example.utility.MD5Encoding;
import org.example.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;


import static org.example.utility.CodeGenerator.generateCode;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository authRepository;
    private final IUserProfileManager userManager;
    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;


    public AuthService(JpaRepository<Auth, Long> repository,
                       IAuthRepository authRepository, IUserProfileManager userManager,
                       JwtTokenProvider jwtTokenProvider,
                       PasswordEncoder passwordEncoder) {
        super(repository);
        this.authRepository = authRepository;
        this.userManager = userManager;
        this.jwtTokenProvider = jwtTokenProvider;

        this.passwordEncoder = passwordEncoder;

    }

    public RegisterResponseDto register(RegisterRequestDto dto) {
        Auth auth = IAuthMapper.INSTANCE.fromRequestDtoToAuth(dto);
        if(dto.getPassword().equals(dto.getPassword())) {
            auth.setActivationCode(generateCode());
            auth.setPassword(passwordEncoder.encode(dto.getPassword()));
            save(auth);
        }
        else {
            throw new AuthManagerException(ErrorType.PASSWORD_ERROR);
        }


        RegisterResponseDto responseDto= IAuthMapper.INSTANCE.fromAuthToResponseDto(auth);


        return responseDto;



    }

    public String login(LoginRequestDto dto){
        Optional<Auth> auth = authRepository.findOptionalByUsername(dto.getUsername());
        if (auth.isEmpty() || !passwordEncoder.matches(dto.getPassword(), auth.get().getPassword())){
            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        } else if (!auth.get().getStatus().equals(EStatus.ACTIVE)) {
            throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
        }
        return jwtTokenProvider.createToken(auth.get().getId(), auth.get().getRole())
                .orElseThrow(() -> {throw new AuthManagerException(ErrorType.TOKEN_NOT_CREATED);
                });
    }
    public Boolean activateStatus(ActivateRequestDto dto){
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }else if (auth.get().getActivationCode().equals(dto.getActivateCode())) {
            auth.get().setStatus(EStatus.ACTIVE);
            update(auth.get());
           // userManager.activateStatus(auth.get().getId());
            return true;
        }
        throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
    }



}
