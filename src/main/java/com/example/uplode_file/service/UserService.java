package com.example.uplode_file.service;

import com.example.uplode_file.dto.UserDto;
import com.example.uplode_file.entity.AttachEntity;
import com.example.uplode_file.entity.UserEntity;
import com.example.uplode_file.reposetory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${attach.download.url}")
    private String attachDownloadUrl;

    private final UserRepository userRepository;

    public UserDto getUserId(Long id) {
        UserEntity user =
                userRepository.findByUserId(id).orElseThrow(() -> {
                    throw new RuntimeException(id + " not found");
                });
        UserDto userDto = new UserDto();

        List<AttachEntity> attach = user.getAttach();
        List<String> urlList = new ArrayList<>();
        for(AttachEntity att: attach){
            urlList.add(attachDownloadUrl + att.getId() + "." + att.getType());
        }
        userDto.setUrl(urlList);
        userDto.setLastname(user.getLastname());
        userDto.setFirstname(user.getFirstname());
        userDto.setMiddleName(user.getMiddleName());
        return userDto;
    }

    public UserDto add(UserDto userDto) {
        UserEntity user = new UserEntity();

//        List<String> attachId = userDto.getAttachId();
        List<AttachEntity> attachEntities = new ArrayList<>();
//        for(String id:attachId){
//            AttachEntity attach = new AttachEntity();
//            attach.setId(id);
//            attachEntities.add(attach);
//        }
        user.setAttach(attachEntities);

//        user.setAttachId(user.getAttachId());
        user.setLastname(user.getLastname());
        user.setFirstname(user.getFirstname());
        user.setMiddleName(user.getMiddleName());

        userRepository.save(user);
        return userDto;
    }

    public void delete(Long id) {
        userRepository.delete(userRepository.findByUserId(id).orElseThrow(
                () -> {
                    throw new RuntimeException(id + " not found");
                })
        );
    }
}
