package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.*;
import org.example.dto.response.RegisterResponseDto;
import org.example.repository.Auth;
import org.example.repository.enums.ERole;
import org.example.service.AuthService;
import org.example.utility.JwtTokenProvider;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.example.constant.ApiUrls.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider tokenProvider;


    @PostMapping(REGISTER) //register2
    public ResponseEntity<RegisterResponseDto> registerResponseDtoResponseEntity(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto) {
        return ResponseEntity.ok(authService.activateStatus(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
/*
    @PutMapping(PASSWORD_CHANGE)
    public ResponseEntity<Boolean> passwordChange(@RequestBody FromUserProfilePasswordChangeDto dto){
        return ResponseEntity.ok(authService.passwordChange(dto));
    }

    @PostMapping(FORGOT_PASSWORD)
    public ResponseEntity<Boolean> forgotPassword(String email, String username){
        return ResponseEntity.ok(authService.forgotPassword(email, username));
    }



    }

    @GetMapping(FIND_ALL)
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    public ResponseEntity<List<Auth>> findAll(){
        return ResponseEntity.ok(authService.findAll());
    }*/

   /* @DeleteMapping(DELETE_BY_ID)
    public ResponseEntity<Boolean> delete(String token){
        return ResponseEntity.ok(authService.delete(token));
    }*/

    /*@PutMapping("/update-username-email")
    public ResponseEntity<Boolean> update(@RequestBody UpdateEmailOrUsernameRequestDto dto){
        return ResponseEntity.ok(authService.update(dto));
    }

    @GetMapping("/create-token-with-id")
    public ResponseEntity<String> createToken(Long id){
        return ResponseEntity.ok(tokenProvider.createToken(id).get());
    }

    @GetMapping("/create-token-with-role")
    public ResponseEntity<String> createToken(Long id, ERole role){
        return ResponseEntity.ok(tokenProvider.createToken(id, role).get());
    }

    @GetMapping("/get-id-from-token")
    public ResponseEntity<Long> getIdFromToken(String token){
        return ResponseEntity.ok(tokenProvider.getIdFromToken(token).get());
    }

    @GetMapping("/get-role-from-token")
    public ResponseEntity<String> getRoleFromToken(String token){
        return ResponseEntity.ok(tokenProvider.getRoleFromToken(token).get());
    }*/

   /* @GetMapping("/find-by-role/{role}")
    public ResponseEntity<List<Long>> findByRole(@PathVariable String role){
        return ResponseEntity.ok(authService.findByRole(role));*/

}

