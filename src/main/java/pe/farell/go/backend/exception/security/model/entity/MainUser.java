package pe.farell.go.backend.exception.security.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainUser implements UserDetails {

    private String username;
    private String password;
    private String config;
    private Integer status;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getConfig() {
        return config;
    }

    public Integer getStatus() {
        return status;
    }

    public MainUser(String username, String password, String config, Integer status, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.config = config;
        this.status = status;
        this.authorities = authorities;
    }

    public static MainUser build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(rol-> new SimpleGrantedAuthority(
                rol.getRolName().name()
        )).collect(Collectors.toList());
        return new MainUser(user.getUsername(), user.getPassword(), user.getConfig(), user.getStatus(), authorities);
    }
}
