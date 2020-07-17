package com.example.demo.ServiceInterface;

import com.example.demo.Entity.UserEntity;

import java.util.List;

public interface UserServiceInterface {
    /**
     * Trả về 1 user theo username nhập vào (dùng để test)
     *
     * */
    UserEntity getUser(String username);

    UserEntity checkUsername(String username);

    /**
     * Update 1 user thành công trả về true
     * */
    boolean updateAUser(UserEntity user);

    /**
     * Trả về danh sách các username có trong db (dùng để test)
     * */
    List<String> getAllUsername();

    /**
     * Kiểm tra username đó có phải admin không,true nếu là admin
     * */
    boolean isAdmin(String username);

    /**
     * Trả về fullname của username nhập vào
     * */
    String getFullname(String username);

    /**
     * Trả về 1 UserEntity nếu username và password đúng
     * */
    UserEntity login(String username, String password);

    /**
     * Add mới 1 user,trả về true nếu thành công
     * */
    boolean addUser(UserEntity user);
}
