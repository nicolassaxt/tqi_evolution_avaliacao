package com.github.nicolassaxt.tqi_evolution_avaliacao.service;

import com.github.nicolassaxt.tqi_evolution_avaliacao.model.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailDetailsService implements UserDetailsService {

    private final ClienteRepository clienteRepository;


    @Override
    public UserDetails loadUserByUsername(String Email){
        return Optional.ofNullable(clienteRepository.findByEmail(Email))
                .orElseThrow(()->new UsernameNotFoundException("Email do cliente não encontrado"));
    }
}
