package dev.jl.jpaperformanceconfigurations.user;

import dev.jl.jpaperformanceconfigurations.httpclient.JsonPlaceholderClient;
import dev.jl.jpaperformanceconfigurations.utils.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final JsonPlaceholderClient jsonPlaceholderClient;

    public UserService(UserRepository userRepository, Mapper mapper, JsonPlaceholderClient jsonPlaceholderClient) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.jsonPlaceholderClient = jsonPlaceholderClient;
    }

    @Transactional
    public void importPosts(){
        if(Boolean.FALSE.equals(userRepository.existsUsers())){
            userRepository.saveAll(mapper.map(jsonPlaceholderClient.fetchUsers(), UserModel.class));
        }
    }

    @Transactional
    public UserModel getReferenceById(Long id){
        return userRepository.getReferenceById(id);
    }
}
