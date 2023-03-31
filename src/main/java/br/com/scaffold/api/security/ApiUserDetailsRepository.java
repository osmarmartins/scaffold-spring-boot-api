package br.com.scaffold.api.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scaffold.api.models.Usuario;

@Repository
public interface ApiUserDetailsRepository extends JpaRepository<Usuario, Integer> {

	Usuario findOneByLogin(String login);

}
