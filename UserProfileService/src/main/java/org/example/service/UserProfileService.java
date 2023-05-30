package org.example.service;

import jdk.jshell.spi.ExecutionControl;
import org.example.dto.request.GetMyProfileRequestDto;
import org.example.dto.request.NewCreateUserRequestDto;
import org.example.dto.request.UserProfileUpdateRequestDto;
import org.example.dto.response.GetMyProfileResponseDto;
import org.example.exception.ErrorType;
import org.example.exception.UserProfileManagerException;
import org.example.manager.IAuthManager;
import org.example.mapper.IUserProfileMapper;
import org.example.repository.IUserProfileRepository;
import org.example.repository.entity.UserProfileEntity;
import org.example.repository.enums.EStatus;
import org.example.utility.JwtTokenProvider;
import org.example.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserProfileService extends ServiceManager <UserProfileEntity, String> {
    private IUserProfileRepository userProfileRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final IAuthManager authManager;;

    public UserProfileService(MongoRepository<UserProfileEntity, String> repository,
                              IUserProfileRepository userProfileRepository,
                              JwtTokenProvider jwtTokenProvider,
                              IAuthManager authManager) {
        super(userProfileRepository);
        this.userProfileRepository=userProfileRepository;
        this.jwtTokenProvider=jwtTokenProvider;
        this.authManager=authManager;

    }


    public List<UserProfileEntity> findAll() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userProfileRepository.findAll();
    }

    public GetMyProfileResponseDto getMyProfile(GetMyProfileRequestDto dto) {
        Optional<Long> authid = jwtTokenProvider.getIdFromToken(dto.getToken());
        if(authid.isEmpty())
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        Optional<UserProfileEntity> userProfile = userProfileRepository.findOptionalByAuthId(authid.get());
        if(userProfile.isEmpty())
            throw new UserProfileManagerException(ErrorType.ERROR_NOT_FOUND_USERNAME);
        return GetMyProfileResponseDto.builder()
                .avatar(userProfile.get().getAvatar())
                .name(userProfile.get().getName()+" "+userProfile.get().getSurname())
                .username(userProfile.get().getUsername())
                .build();
    }


    public UserProfileEntity update(UserProfileUpdateRequestDto dto){

        Optional<Long> authId = jwtTokenProvider.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new UserProfileManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfileEntity> userProfile = userProfileRepository.findOptionalByAuthId(authId.get());
        if (userProfile.isEmpty()){
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        }

        UserProfileUpdateRequestDto updateEmailOrUsernameRequestDto = IUserProfileMapper.INSTANCE.toUpdateUsernameEmail(dto);
        updateEmailOrUsernameRequestDto.setAuthId(authId.get());
        authManager.updateUsernameOrEmail(dto);
        update(IUserProfileMapper.INSTANCE.updateFromDtoToUserProfile(dto, userProfile.get()));

        return userProfile.get();
    }
    public Boolean delete(Long authId){
        Optional<UserProfileEntity> userProfile = userProfileRepository.findOptionalByAuthId(authId);
        if (userProfile.isEmpty()) {
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(EStatus.DELETED);
        update(userProfile.get());
        return true;
    }

    public Boolean createUser(NewCreateUserRequestDto dto){
        try {
            save(IUserProfileMapper.INSTANCE.fromDtoToUserProfile(dto));
            //cacheManager.getCache("findAll").put(dto, findAll());   denenecek!!!
            return true;
        }catch (Exception e){
            throw new RuntimeException("Beklenmeyen bir hata oluştu.");
        }
    }

}
