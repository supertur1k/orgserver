package com.medicine.orgserver.services;

import com.medicine.orgserver.dto.*;
import com.medicine.orgserver.entities.FirstAidKit;
import com.medicine.orgserver.entities.FirstAidKitUser;
import com.medicine.orgserver.entities.Notification;
import com.medicine.orgserver.entities.User;
import com.medicine.orgserver.exceptions.AppError;
import com.medicine.orgserver.repositories.FirstAidKitRepository;
import com.medicine.orgserver.repositories.FirstAidKitUserRepository;
import com.medicine.orgserver.repositories.NotificationRepository;
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

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor (onConstructor_={@Lazy})
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RolesService rolesService;
    private final PasswordEncoder passwordEncoder;
    private final FirstAidKitRepository firstAidKitRepository;
    private final FirstAidKitUserRepository firstAidKitUserRepository;
    private final NotificationService notificationService;

    private final NotificationRepository notificationRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public ResponseEntity<?> getFirstAidKitsByUsername(String username) {
        if (this.findByUsername(username).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(this.findByUsername(username).get().getFirstAidKits());
    }

    @Transactional
    public ResponseEntity<?> createFirstAidKitForUser(FirstAidKitDTO firstAidKitDTO) {
        if (this.findByUsername(firstAidKitDTO.getUsername()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }
        User user = this.findByUsername(firstAidKitDTO.getUsername()).get();

        FirstAidKit firstAidKit = new FirstAidKit();
        firstAidKit.setName_of_the_first_aid_kit(firstAidKitDTO.getName_of_the_first_aid_kit());
        if (!firstAidKitDTO.getDescription().isBlank()) firstAidKit.setDescription(firstAidKitDTO.getDescription());
        firstAidKitRepository.save(firstAidKit);

        Collection<FirstAidKit> firstAidKits = user.getFirstAidKits();
        firstAidKits.add(firstAidKit);
        user.setFirstAidKits(firstAidKits);
        userRepository.save(user);

        return ResponseEntity.ok(user.getFirstAidKits());
    }

    @Transactional
    public ResponseEntity<?> removeFirstAidFromForUser(FirstAidKitIdUsernameDTO firstAidKitIdUsernameDTO) {
        if (this.findByUsername(firstAidKitIdUsernameDTO.getUsername()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }
        if (firstAidKitRepository.findById(firstAidKitIdUsernameDTO.getFirst_aid_kit_id()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
        }
        User user = this.findByUsername(firstAidKitIdUsernameDTO.getUsername()).get();
        Collection<FirstAidKit> firstAidKits = user.getFirstAidKits();
        Optional<FirstAidKit> userFirstAidKit = firstAidKits.stream().filter(firstAidKit ->
                Objects.equals(firstAidKit.getId(), firstAidKitIdUsernameDTO.getFirst_aid_kit_id())).findFirst();
        if (userFirstAidKit.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не найдена у пользователя"), HttpStatus.BAD_REQUEST);
        }
        firstAidKits.remove(userFirstAidKit.get());
        user.setFirstAidKits(firstAidKits);
        userRepository.save(user);


        FirstAidKit firstAidKit = firstAidKitRepository.findById(firstAidKitIdUsernameDTO.getFirst_aid_kit_id()).get();

        if (firstAidKitUserRepository.findByFirstAidKitId(firstAidKit).isEmpty()) {
            firstAidKitRepository.deleteById(firstAidKitIdUsernameDTO.getFirst_aid_kit_id());
        }
        return ResponseEntity.ok(user.getFirstAidKits());
    }

    @Transactional
    public ResponseEntity<?> getUsersOfFaks(ListFaksDto ids) {
        for (Long id : ids.getFirst_aid_kit_ids()) {
            if (firstAidKitRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                        "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
            }
        }

        List<FirstAidKit> firstAidKits = new ArrayList<>();
        for (Long id : ids.getFirst_aid_kit_ids()) {
            firstAidKits.add(firstAidKitRepository.findById(id).get());
        }

        List<FirstAidKitUser> data = new ArrayList<>();
        for (FirstAidKit firstAidKit : firstAidKits) {
            data.addAll(firstAidKitUserRepository.findByFirstAidKitId(firstAidKit));
        }

        Collection<Long> userIds = new ArrayList<>();
        data.forEach(x->userIds.add(x.getUser_id().getId()));

        return ResponseEntity.ok(userIds);
    }

    @Transactional
    public ResponseEntity<?> getNotificationsOfAllUsersOfFaks(ListFaksDto ids) {
        for (Long id : ids.getFirst_aid_kit_ids()) {
            if (firstAidKitRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                        "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
            }
        }

        List<FirstAidKit> firstAidKits = new ArrayList<>();
        for (Long id : ids.getFirst_aid_kit_ids()) {
            firstAidKits.add(firstAidKitRepository.findById(id).get());
        }

        List<FirstAidKitUser> data = new ArrayList<>();
        for (FirstAidKit firstAidKit : firstAidKits) {
            data.addAll(firstAidKitUserRepository.findByFirstAidKitId(firstAidKit));
        }

        List<Long> userIds = new ArrayList<>();
        data.forEach(x->userIds.add(x.getUser_id().getId()));

        Collection<Notification> notifications = new ArrayList<>();
        data.forEach(x -> {
            notifications.addAll(notificationRepository.findByUsername(x.getUser_id().getUsername()));
        });

        return ResponseEntity.ok(notifications.stream().filter(x -> x.getReceived() && (x.getComment() == null || !x.getComment().startsWith("system_user_invite_from"))).collect(Collectors.toList()));
    }

    @Transactional
    public ResponseEntity<?> addExistingFirstAidKitToUser(FirstAidKitIdUsernameDTO firstAidKitIdUsernameDTO) {
        if (this.findByUsername(firstAidKitIdUsernameDTO.getUsername()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }
        if (firstAidKitRepository.findById(firstAidKitIdUsernameDTO.getFirst_aid_kit_id()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
        }
        User user = this.findByUsername(firstAidKitIdUsernameDTO.getUsername()).get();
        FirstAidKit firstAidKit = firstAidKitRepository.findById(firstAidKitIdUsernameDTO.getFirst_aid_kit_id()).get();

        FirstAidKitUser firstAidKitUser = new FirstAidKitUser();
        firstAidKitUser.setUser_id(user);
        firstAidKitUser.setFirst_aid_kit_id(firstAidKit);
        firstAidKitUserRepository.save(firstAidKitUser);

        return ResponseEntity.ok(user.getFirstAidKits());
    }

    @Transactional
    public ResponseEntity<?> notificationForAddingExistingFirstAidKitToUser(FirstAidKitIdUsernameDTO2Users firstAidKitIdUsernameDTO2Users) {
        if (this.findByUsername(firstAidKitIdUsernameDTO2Users.getUsername()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }
        if (firstAidKitRepository.findById(firstAidKitIdUsernameDTO2Users.getFirst_aid_kit_id()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Аптечка с переданным id не существует"), HttpStatus.BAD_REQUEST);
        }

        return notificationService.createNotificationForUser(firstAidKitIdUsernameDTO2Users);
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

        return userRepository.save(user);
    }

    public Long getIdByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return user.getId();
    }
}
