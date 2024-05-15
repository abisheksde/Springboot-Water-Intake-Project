package com.example.dwi.Service;


import com.example.dwi.Dto.UserDto;
import com.example.dwi.Model.User;


public interface UserService {


    User save (UserDto userDto);
}