package com.Ecommerce.main.security.user;

import com.Ecommerce.main.exception.UserNotFound;
import com.Ecommerce.main.model.User;
import com.Ecommerce.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ShopUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=Optional.ofNullable(userRepository.findByEmail(username)).orElseThrow(()-> new UserNotFound("user not fond "));
        return ShopUserDetails.buildUserDetails(user);
    }
}
