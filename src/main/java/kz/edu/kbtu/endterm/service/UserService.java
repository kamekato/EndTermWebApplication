package kz.edu.kbtu.endterm.service;

import kz.edu.kbtu.endterm.dto.request.UserDtoRequest;
import kz.edu.kbtu.endterm.model.User;
import kz.edu.kbtu.endterm.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                         .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public User create(UserDtoRequest request) {
        return repository.save(User.builder()
                                   .username(request.getUsername())
                                   .password(request.getPassword())
                                   .build());
    }
}
