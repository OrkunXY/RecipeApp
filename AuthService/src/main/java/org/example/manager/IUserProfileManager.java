package org.example.manager;

import org.example.dto.request.NewCreateUserRequestDto;
import org.example.dto.request.UserProfileChangePasswordRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.example.constant.ApiUrls.DELETE_BY_ID;
import static org.example.constant.ApiUrls.FORGOT_PASSWORD;


@FeignClient(url = "http://localhost:8070/api/v1/user-profile", name = "auth-userprofile")
public interface IUserProfileManager {
    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto);

    @GetMapping("/activate-status/{authId}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId);

    @DeleteMapping(DELETE_BY_ID + "/{authId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long authId);

    @PutMapping(FORGOT_PASSWORD)
    public ResponseEntity<Boolean> forgotPassword(@RequestBody UserProfileChangePasswordRequestDto dto);
}
