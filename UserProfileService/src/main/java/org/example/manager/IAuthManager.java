package org.example.manager;

import org.example.dto.request.UserProfileUpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8090/api/v1/auth", name = "userprofile-auth",decode404 = true)
public interface IAuthManager {
    @PutMapping("/update-username-email")
    public ResponseEntity<Boolean> updateUsernameOrEmail(@RequestBody UserProfileUpdateRequestDto dto);
}
