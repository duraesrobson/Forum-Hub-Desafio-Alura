package desafio.alura.forum_hub.infra.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import desafio.alura.forum_hub.domain.usuario.Usuario;

public class UsuarioSecurity implements UserDetails {

    private final Usuario usuario;

    public UsuarioSecurity(Usuario usuario) {
        this.usuario = usuario;
    }

    // verifica se tem algum perfil e autoriza, se não tiver cria um padrão.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (usuario.getPerfis() == null || usuario.getPerfis().isEmpty()) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return usuario.getPerfis().stream()
                .map(perfil -> new SimpleGrantedAuthority("ROLE_" + perfil.getNome().toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
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

}
