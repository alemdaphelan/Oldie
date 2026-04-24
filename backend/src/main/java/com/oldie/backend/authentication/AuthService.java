package com.oldie.backend.authentication;

import com.oldie.backend.user.entities.User;
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
    private final UserMapper userMapper;
    private final JwtService jwtService;

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
        UserResponse userResponse;
        if (user.get().getIsSetProfile() == false) {
            userResponse = UserResponse.builder()
                    .userId(user.get().getUserId())
                    .email(user.get().getEmail())
                    .build();
        } else {
            userResponse = userMapper.toUserResponse(user.get().getUserProfile());
        }
        userResponse.setToken(jwtService.generateToken(user.get().getUserId(), user.get().getEmail(),
                user.get().getRole()));
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
                .token(jwtService.generateToken(newUser.getUserId(), newUser.getEmail(), newUser.getRole()))
                .build();
    }

    UserResponse oauth(String providerName) {
        return null;
    }
}