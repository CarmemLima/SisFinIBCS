package SisFinIBCS.repository;

import SisFinIBCS.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

/*Explicação: JpaRepository<Evento, Long> significa:
Evento = classe que será salva no banco
Long = tipo do ID da classe Evento */
public interface EventoRepository extends JpaRepository<Evento, Long> {
}