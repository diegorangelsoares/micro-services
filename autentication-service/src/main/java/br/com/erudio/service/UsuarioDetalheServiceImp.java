package br.com.erudio.service;

import br.com.erudio.data.DetalheUsuarioData;
import br.com.erudio.model.Usuario;
import br.com.erudio.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioDetalheServiceImp implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetalheServiceImp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
        if (usuario.isEmpty()) throw new UsernameNotFoundException("Usuário ["+username+"] não encontrato!");
        return new DetalheUsuarioData(usuario);
    }
}
