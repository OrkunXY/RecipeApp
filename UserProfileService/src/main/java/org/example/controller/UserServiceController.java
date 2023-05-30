package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.GetMyProfileRequestDto;
import org.example.dto.request.NewCreateUserRequestDto;
import org.example.dto.request.UserProfileUpdateRequestDto;
import org.example.dto.response.GetMyProfileResponseDto;
import org.example.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.example.constant.ApiUrls.*;

@RestController
@RequestMapping("/api/v1/userprofile")
@RequiredArgsConstructor
public class UserServiceController {
    private final UserProfileService userProfileService;

    @PostMapping("/getmyprofile")
    public ResponseEntity<GetMyProfileResponseDto> getMyProfile(@RequestBody @Valid GetMyProfileRequestDto dto){
        return ResponseEntity.ok(userProfileService.getMyProfile(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid UserProfileUpdateRequestDto dto){
        userProfileService.update(dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(DELETE_BY_ID + "/{authId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long authId){
        return ResponseEntity.ok(userProfileService.delete(authId));
    }
    @PostMapping("/create")
        public ResponseEntity<Boolean> createUser (@RequestBody NewCreateUserRequestDto dto){
        return ResponseEntity.ok(userProfileService.createUser(dto));
    }


}
