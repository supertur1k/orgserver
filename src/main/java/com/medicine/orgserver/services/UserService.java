package com.medicine.orgserver.services;

import com.medicine.orgserver.dto.FirstAndKitDTO;
import com.medicine.orgserver.dto.RegUserDto;
import com.medicine.orgserver.dto.FirstAndKitIdUsernameDTO;
import com.medicine.orgserver.entities.FirstAndKit;
import com.medicine.orgserver.entities.FirstAndKitUser;
import com.medicine.orgserver.entities.User;
import com.medicine.orgserver.exceptions.AppError;
import com.medicine.orgserver.repositories.FirstAndKitRepository;
import com.medicine.orgserver.repositories.FirstAndKitUserRepository;
import com.medicine.orgserver.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor (onConstructor_={@Lazy})
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RolesService rolesService;
    private final PasswordEncoder passwordEncoder;
    private final FirstAndKitRepository firstAndKitRepository;
    private final FirstAndKitUserRepository firstAndKitUserRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public ResponseEntity<?> getFirstAndKitsByUsername(String username) {
        if (this.findByUsername(username).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(this.findByUsername(username).get().getFirstAndKits());
    }

    @Transactional
    public ResponseEntity<?> createFirstAndKitForUser(FirstAndKitDTO firstAndKitDTO) {
        if (this.findByUsername(firstAndKitDTO.getUsername()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }
        User user = this.findByUsername(firstAndKitDTO.getUsername()).get();

        FirstAndKit firstAndKit = new FirstAndKit();
        firstAndKit.setName_of_the_first_and_kit(firstAndKitDTO.getName_of_the_first_and_kit());
        if (!firstAndKitDTO.getDescription().isBlank()) firstAndKit.setDescription(firstAndKitDTO.getDescription());
        firstAndKitRepository.save(firstAndKit);

        Collection<FirstAndKit> firstAndKits = user.getFirstAndKits();
        firstAndKits.add(firstAndKit);
        user.setFirstAndKits(firstAndKits);
        userRepository.save(user);

        return ResponseEntity.ok(user.getFirstAndKits());
    }

    @Transactional
    public ResponseEntity<?> removeFirstAndFromForUser(FirstAndKitIdUsernameDTO firstAndKitIdUsernameDTO) {
        if (this.findByUsername(firstAndKitIdUsernameDTO.getUsername()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }
        if (firstAndKitRepository.findById(firstAndKitIdUsernameDTO.getFirst_and_kit_id()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
        }
        User user = this.findByUsername(firstAndKitIdUsernameDTO.getUsername()).get();
        Collection<FirstAndKit> firstAndKits = user.getFirstAndKits();
        Optional<FirstAndKit> userFirstAndKit = firstAndKits.stream().filter(firstAndKit ->
                Objects.equals(firstAndKit.getId(), firstAndKitIdUsernameDTO.getFirst_and_kit_id())).findFirst();
        if (userFirstAndKit.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не найдена у пользователя"), HttpStatus.BAD_REQUEST);
        }
        firstAndKits.remove(userFirstAndKit.get());
        user.setFirstAndKits(firstAndKits);
        userRepository.save(user);


        FirstAndKit firstAndKit = firstAndKitRepository.findById(firstAndKitIdUsernameDTO.getFirst_and_kit_id()).get();

        if (firstAndKitUserRepository.findByFirstAndKitId(firstAndKit).isEmpty()) {
            firstAndKitRepository.deleteById(firstAndKitIdUsernameDTO.getFirst_and_kit_id());
        }
        return ResponseEntity.ok(user.getFirstAndKits());
    }

    @Transactional
    public ResponseEntity<?> addExistingFirstAidKitToUser(FirstAndKitIdUsernameDTO firstAndKitIdUsernameDTO) {
        if (this.findByUsername(firstAndKitIdUsernameDTO.getUsername()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }
        if (firstAndKitRepository.findById(firstAndKitIdUsernameDTO.getFirst_and_kit_id()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
        }
        User user = this.findByUsername(firstAndKitIdUsernameDTO.getUsername()).get();
        FirstAndKit firstAndKit = firstAndKitRepository.findById(firstAndKitIdUsernameDTO.getFirst_and_kit_id()).get();

        FirstAndKitUser firstAndKitUser = new FirstAndKitUser();
        firstAndKitUser.setUser_id(user);
        firstAndKitUser.setFirst_and_kit_id(firstAndKit);
        firstAndKitUserRepository.save(firstAndKitUser);

        return ResponseEntity.ok(user.getFirstAndKits());
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public User createNewUser(RegUserDto registrationUserDto) {
        User user = new User();
        user.setUsername(registrationUserDto.getUsername());
        user.setEmail(registrationUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRoles(List.of(rolesService.getDefaultUserRole()));

        //user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        return userRepository.save(user);
    }
}
