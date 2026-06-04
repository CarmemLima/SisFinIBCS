package SisFinIBCS.repository;

import SisFinIBCS.model.Evento;
import SisFinIBCS.model.LancamentoFinanceiro;
import SisFinIBCS.model.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*Explicação: Esses métodos seguem uma convenção do Spring Data JPA. Quando escrevemos: findByEvento
o Spring entende: buscar lançamentos pelo evento
E quando escrevemos: findByTipo ele entende: buscar lançamentos por ENTRADA ou SAIDA */
public interface LancamentoFinanceiroRepository extends JpaRepository<LancamentoFinanceiro, Long> {

    List<LancamentoFinanceiro> findByEvento(Evento evento);

    List<LancamentoFinanceiro> findByTipo(TipoLancamento tipo);
}