package co.istad.cms.controller;

import co.istad.cms.model.User;
import co.istad.cms.repository.UserRepository;
import co.istad.cms.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserController extends UserServiceImpl {
    private final UserRepository userRepository;

    public List <User> findAllUser(){

        return userRepository.select() ;
    }

}
