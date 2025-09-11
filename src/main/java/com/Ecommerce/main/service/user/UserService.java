package com.Ecommerce.main.service.user;

import com.Ecommerce.main.Dto.CartDto;
import com.Ecommerce.main.Dto.CreatUser;
import com.Ecommerce.main.Dto.UserDto;
import com.Ecommerce.main.Dto.UserUpdate;
import com.Ecommerce.main.exception.UserNotFound;
import com.Ecommerce.main.model.Cart;
import com.Ecommerce.main.model.User;
import com.Ecommerce.main.repository.CartRepository;
import com.Ecommerce.main.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;


    @Override
    public User getUser(long id) {
        return  userRepository.findById(id).orElseThrow(()->new UserNotFound("user not found "));


    }

    @Override
    public UserDto createUser(CreatUser request) {
        User newuser= Optional.of(request).filter(c->!userRepository.existsByEmail(request.getEmail())).map(item->{User user= new User(request.getFirstName(),request.getLastName(),request.getEmail(),request.getPassword());return user;}).orElseThrow(()->new UserNotFound("email is already availabe "));
        /*Cart newCart=new Cart();
        newCart.setUser(newuser);
        newuser.setCart(newCart);*/
        User savedUser=userRepository.save(newuser);
        return this.changeUserDto(savedUser);

    }

    @Override
    public UserDto UpdateUser(UserUpdate user, Long userId) {
        User findUser=userRepository.findById(userId).orElseThrow(()->new UserNotFound("user noit found "));
        findUser.setFirstName(user.getFirstName());
        findUser.setLastName(user.getLastName());
        User newUser=userRepository.save(findUser);
        return this.changeUserDto(newUser);
    }

    @Override
    public void DeleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete,()->{throw new UserNotFound("user not found");});

    }
    @Override
    public UserDto changeUserDto(User user){
        return modelMapper.map(user,UserDto.class);
    }
}
