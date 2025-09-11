package com.Ecommerce.main.controler;
import com.Ecommerce.main.Dto.CreatUser;
import com.Ecommerce.main.Dto.UserDto;
import com.Ecommerce.main.Dto.UserUpdate;
import com.Ecommerce.main.exception.UserNotFound;
import com.Ecommerce.main.model.User;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.service.cart.CartService;
import com.Ecommerce.main.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/user/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CartService cartService;

    @GetMapping("getById/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Integer id){
        try {
            User user=userService.getUser(id);
            UserDto userDto=userService.changeUserDto(user);
            return ResponseEntity.ok(new ApiResponse("get sucessfullly",userDto));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));

        }

    }

    @PostMapping("creatUser")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreatUser user){
        try {
            UserDto createUser=userService.createUser(user);
            return ResponseEntity.ok(new ApiResponse("create sucessfullly",createUser));
        } catch (UserNotFound e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse> UpdateUser(@PathVariable("id") Long id, @RequestBody UserUpdate request){
        try {
            UserDto user=userService.UpdateUser(request,id);
            return ResponseEntity.ok(new ApiResponse("updated successfullly",user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Long id){
        try {
            userService.DeleteUser(id);
            return ResponseEntity.ok(new ApiResponse("deleted successfullly",null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }



}
