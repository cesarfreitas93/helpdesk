package pe.farell.go.backend.exception.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.farell.go.backend.constant.EnumResponse;
import pe.farell.go.backend.exception.ValidationException;
import pe.farell.go.backend.exception.security.enums.RolName;
import pe.farell.go.backend.exception.security.jwt.JwtProvider;
import pe.farell.go.backend.exception.security.model.dto.JwtDto;
import pe.farell.go.backend.exception.security.model.dto.LoginUser;
import pe.farell.go.backend.exception.security.model.dto.NewUser;
import pe.farell.go.backend.exception.security.model.entity.Rol;
import pe.farell.go.backend.exception.security.model.entity.User;
import pe.farell.go.backend.exception.security.service.RolService;
import pe.farell.go.backend.exception.security.service.UserService;
import pe.farell.go.backend.model.dto.response.Response;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/create")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(EnumResponse.C000.getCode(), "Ingrese todos los campos de manera correcta.");
        }

        if (userService.existByUsername(newUser.getUsername())) {
            throw new ValidationException(EnumResponse.C000.getCode(), "Nombre de usuario no válido, ingrese otro.");
        }

        User user = new User(newUser.getUsername(), passwordEncoder.encode(newUser.getPassword()), "{}", 1);

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_TECHNICAL).get());
        if (newUser.getRoles().contains("supervisor")) {
            roles.add(rolService.getByRolName(RolName.ROLE_SUPERVISOR).get());
        }

        if (newUser.getRoles().contains("administrador")) {
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        }

        if (newUser.getRoles().contains("master")) {
            roles.add(rolService.getByRolName(RolName.ROLE_MASTER).get());
        }

        user.setRoles(roles);

        userService.save(user);
        Response<String> response = new Response<>();
        response.setData("Usuario creado con éxito");
        response.setCode(EnumResponse.C001.getCode());
        response.setMsg(EnumResponse.C001.getMsg());
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(EnumResponse.C000.getCode(), "Ingrese todos los campos de manera correcta.");
        }
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

}
