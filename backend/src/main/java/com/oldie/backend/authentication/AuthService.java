package com.oldie.backend.authentication;

import com.oldie.backend.user.entities.User;
import com.oldie.backend.user.entities.UserProfile;
import com.oldie.backend.authentication.dto.RegisterRequest;
import com.oldie.backend.authentication.dto.UserMapper;
import com.oldie.backend.authentication.dto.LoginRequest;
import com.oldie.backend.user.UserRepository;
import com.oldie.backend.authentication.dto.UserResponse;
import com.oldie.backend.core.exception.AppException;
import com.oldie.backend.core.exception.ErrorCode;
import com.oldie.backend.locations.City;
import com.oldie.backend.locations.District;
import com.oldie.backend.locations.Ward;
import com.oldie.backend.locations.CityRepository;
import com.oldie.backend.locations.DistrictRepository;
import com.oldie.backend.locations.WardRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.*;

@Service
@RequiredArgsConstructor
class AuthService {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper UserMapper;

    UserResponse login(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }
        UserProfile userProfile = user.get().getUserProfile();
        UserResponse userResponse = UserMapper.toUserResponse(userProfile);
        return userResponse;
    }

    UserResponse register(RegisterRequest request) {
        String email = request.getEmail();
        String phone = request.getPhoneNumber();
        String passwordHash = passwordEncoder.encode(request.getPassword());
        City city = cityRepository.findByCityGsoId(request.getCityGsoId());
        District district = districtRepository.findByDistrictGsoId(request.getDistrictGsoId());
        Ward ward = wardRepository.findByWardGsoId(request.getWardGsoId());

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTS);
        }
        User newUser = User.builder()
                .email(email)
                .password(passwordHash)
                .phoneNumber(phone)
                .city(city)
                .district(district)
                .ward(ward)
                .build();
        userRepository.save(newUser);
        return UserResponse.builder()
                .userId(newUser.getUserId())
                .email(newUser.getEmail())
                .isProfileSetUp(false)
                .build();
    }

    UserResponse setProfile(UserResponse request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        User user = userOpt.get();
        UserProfile userProfile = user.getUserProfile();
        if (userProfile == null) {
            userProfile = new UserProfile();
            userProfile.setUser(user);
        }
        userProfile.setFirstName(request.getFirstName());
        userProfile.setLastName(request.getLastName());
        userProfile.setAvatarUrl(request.getAvatarUrl());
        userProfile.setBio(request.getBio());
        userProfile.setReputationScore(Integer.parseInt(request.getReputationScore()));
        userProfile.setTotalReviews(Integer.parseInt(request.getTotalReviews()));
        userProfile.setSuccessTrades(Integer.parseInt(request.getSuccessTrades()));
        userProfile.setAddressDetail(request.getAddressDetail());
        userProfile.setFollowerCount(request.getFollowerCount());
        userProfile.setFollowingCount(request.getFollowingCount());
        userRepository.save(user);

        UserResponse response = UserMapper.toUserResponse(userProfile);
        response.setIsProfileSetUp(true);
        return response;
    }
}