package com.Ecommerce.main.service.user;

import com.Ecommerce.main.Dto.CreatUser;
import com.Ecommerce.main.Dto.UserUpdate;
import com.Ecommerce.main.model.User;

public interface UserInterface {

    User getUser(long id);
    User createUser(CreatUser user);
    User UpdateUser(UserUpdate user,Long userId);
    void  DeleteUser(Long userId);



}
