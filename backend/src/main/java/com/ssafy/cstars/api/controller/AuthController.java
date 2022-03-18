package com.ssafy.cstars.api.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.ssafy.cstars.api.request.LogOutRequest;
import com.ssafy.cstars.api.request.LoginRequest;
import com.ssafy.cstars.api.request.SignupRequest;
import com.ssafy.cstars.api.request.TokenRefreshRequest;
import com.ssafy.cstars.api.response.JwtResponse;
import com.ssafy.cstars.api.response.MessageResponse;
import com.ssafy.cstars.api.response.TokenRefreshResponse;
import com.ssafy.cstars.domain.entity.BrandAdmin;
import com.ssafy.cstars.domain.entity.ERole;
import com.ssafy.cstars.domain.entity.RefreshToken;
import com.ssafy.cstars.domain.entity.RefreshTokenBrandAdmin;
import com.ssafy.cstars.domain.entity.RefreshTokenStoreAdmin;
import com.ssafy.cstars.domain.entity.Role;
import com.ssafy.cstars.domain.entity.StoreAdmin;
import com.ssafy.cstars.domain.entity.User;
import com.ssafy.cstars.domain.repository.AdminRepository;
import com.ssafy.cstars.domain.repository.BrandAdminRepository;
import com.ssafy.cstars.domain.repository.RefreshTokenBrandAdminRepository;
import com.ssafy.cstars.domain.repository.RefreshTokenRepository;
import com.ssafy.cstars.domain.repository.RefreshTokenStoreAdminRepository;
import com.ssafy.cstars.domain.repository.RoleRepository;
import com.ssafy.cstars.domain.repository.StoreAdminRepository;
import com.ssafy.cstars.domain.repository.UserRepository;
import com.ssafy.cstars.exception.TokenRefreshException;
import com.ssafy.cstars.security.jwt.JwtUtils;
import com.ssafy.cstars.security.services.BrandAdminDetailsImpl;
import com.ssafy.cstars.security.services.RefreshTokenService;
import com.ssafy.cstars.security.services.StoreAdminDetailsImpl;
import com.ssafy.cstars.security.services.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  StoreAdminRepository storeAdminRepository;

  @Autowired
  BrandAdminRepository brandAdminRepository;

  @Autowired
  AdminRepository adminRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  @Autowired
  RefreshTokenRepository refreshTokenRepository;

  @Autowired
  RefreshTokenStoreAdminRepository refreshTokenStoreAdminRepository;

  @Autowired
  RefreshTokenBrandAdminRepository refreshTokenBrandAdminRepository;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    System.out.println(loginRequest.getEmail()+ "    " + loginRequest.getPassword());


    Authentication authentication = null;
    JwtResponse jwtResponse = null;
    if(adminRepository.existsByEmail(loginRequest.getEmail())){
      System.out.println("check!!");
      jwtResponse = new JwtResponse(loginRequest.getEmail(), "ROLE_SITE_ADMIN");
      System.out.println(jwtResponse);
    }else{
      authentication = authenticationManager
      .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
      System.out.println("check authentic");
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    

    if(userRepository.existsByEmail(loginRequest.getEmail())){

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

      String jwt = jwtUtils.generateJwtToken(userDetails);
  
      List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
          .collect(Collectors.toList());

      RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getEmail());

      jwtResponse = new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), userDetails.getEth(), userDetails.getTel(), userDetails.getAddress(), userDetails.getRole());

    }else if(storeAdminRepository.existsByEmail(loginRequest.getEmail())){

      StoreAdminDetailsImpl storeDetails = (StoreAdminDetailsImpl) authentication.getPrincipal();

      String jwt = jwtUtils.generateJwtTokenStoreAdmin(storeDetails);

      List<String> roles = storeDetails.getAuthorities().stream().map(item -> item.getAuthority())
          .collect(Collectors.toList());

      RefreshTokenStoreAdmin refreshToken = refreshTokenService.createStoreAdminRefreshToken(storeDetails.getEmail());

      jwtResponse = new JwtResponse(jwt, refreshToken.getToken(), storeDetails.getId(), storeDetails.getEmail(), storeDetails.getStore(), storeDetails.getWallet(), storeDetails.getRole());

    }else if(brandAdminRepository.existsByEmail(loginRequest.getEmail())){

      BrandAdminDetailsImpl brandDetails = (BrandAdminDetailsImpl) authentication.getPrincipal();

      String jwt = jwtUtils.generateJwtTokenBrandAdmin(brandDetails);

      List<String> roles = brandDetails.getAuthorities().stream().map(item -> item.getAuthority())
          .collect(Collectors.toList());

      RefreshTokenBrandAdmin refreshToken = refreshTokenService.createBrandAdminRefreshToken(brandDetails.getEmail());

      jwtResponse = new JwtResponse(jwt, refreshToken.getToken(), brandDetails.getId(), brandDetails.getName(), brandDetails.getWallet(), brandDetails.getRole(), roles);

    }
//    else if(userRepository.existsByEmail(loginRequest.getEmail())){
//      System.out.println("check!!");
//      jwtResponse = new JwtResponse(loginRequest.getEmail(), "ROLE_SITE_ADMIN");
//      System.out.println(jwtResponse);
//    }

    return ResponseEntity.ok(jwtResponse);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    //user, store_admin, brand_admin의 이메일 존재 check ( 추후 admin도 추가 해줘야 할듯 )
    if (userRepository.existsByEmail(signUpRequest.getEmail()) || storeAdminRepository.existsByEmail(signUpRequest.getEmail()) || brandAdminRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }
    System.out.println(signUpRequest);
    Set<String> strRoles = signUpRequest.getRoles();
    Iterator<String> iter = strRoles.iterator();
    while(iter.hasNext()){
      signUpRequest.setRole((String)iter.next());
    }
    System.out.println(strRoles);
    System.out.println(signUpRequest);
    Set<Role> roles = new HashSet<>();
    strRoles.forEach(role ->{
      switch(role){
        // Create new user's account
        case "ROLE_USER": 
                User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getTel(), signUpRequest.getAddress(), signUpRequest.getRole());
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
                user.setRoles(roles);
                userRepository.save(user);
                break;
        // Create new store_admin's account
        case "ROLE_STORE_ADMIN": 
                StoreAdmin storeAdmin = new StoreAdmin(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getStore(), signUpRequest.getWallet(),signUpRequest.getRole());
                Role StoreAdminRole = roleRepository.findByName(ERole.ROLE_STORE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(StoreAdminRole);
                storeAdminRepository.save(storeAdmin);
                break;
        // Create new brand_admin's account
        case "ROLE_BRAND_ADMIN":
                BrandAdmin brandAdmin = new BrandAdmin(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getName(), signUpRequest.getWallet(), signUpRequest.getRole());
                Role BrandAdminRole = roleRepository.findByName(ERole.ROLE_BRAND_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(BrandAdminRole);
                brandAdminRepository.save(brandAdmin);
                break;
      }
    });

    return ResponseEntity.ok(new MessageResponse("registered successfully!"));
  }

  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();
    
    ResponseEntity result = null;

    if(refreshTokenRepository.existsByToken(requestRefreshToken)){
      result = refreshTokenService.findByToken(requestRefreshToken)
      .map(refreshTokenService::verifyExpiration)
      .map(RefreshToken::getUser)
      .map(user -> {
        String token = jwtUtils.generateTokenFromEmail(user.getEmail());
        return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
      })
      .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
          "Refresh token is not in database!"));

    }else if(refreshTokenStoreAdminRepository.existsByToken(requestRefreshToken)){
      result = refreshTokenService.findByTokenStoreAdmin(requestRefreshToken)
      .map(refreshTokenService::verifyExpirationStoreAdmin)
      .map(RefreshTokenStoreAdmin::getStoreAdmin)
      .map(storeAdmin -> {
        String token = jwtUtils.generateTokenFromEmail(storeAdmin.getEmail());
        return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
      })
      .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
          "Refresh token is not in database!"));
    }else if(refreshTokenBrandAdminRepository.existsByToken(requestRefreshToken)){
      result = refreshTokenService.findByTokenBrandAdmin(requestRefreshToken)
      .map(refreshTokenService::verifyExpirationBrandAdmin)
      .map(RefreshTokenBrandAdmin::getBrandAdmin)
      .map(brandAdmin -> {
        String token = jwtUtils.generateTokenFromEmail(brandAdmin.getEmail());
        return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
      })
      .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
          "Refresh token is not in database!"));
    }
    return result;
  }
  
  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
    refreshTokenService.deleteByEmail(logOutRequest.getEmail());
    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
  }

}
