package com.Ecommerce.main.service.user;

import com.Ecommerce.main.Dto.CreatUser;
import com.Ecommerce.main.Dto.UserUpdate;
import com.Ecommerce.main.exception.UserNotFound;
import com.Ecommerce.main.model.User;
import com.Ecommerce.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {
    private final UserRepository userRepository;


    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFound("user not found "));
    }

    @Override
    public User createUser(CreatUser user) {
        return null;
    }

    @Override
    public User UpdateUser(UserUpdate user, Long userId) {
        User findUser=userRepository.findById(userId).orElseThrow(()->new UserNotFound("user noit found "));
        findUser.setFirstName(user.getFirstName());
        findUser.setLastName(user.getLastName());
        return userRepository.save(findUser);
    }

    @Override
    public void DeleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(c->userRepository.deleteById(userId),()->{throw new UserNotFound("user not found");});

    }
}
