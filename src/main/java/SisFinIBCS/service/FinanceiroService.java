package SisFinIBCS.service;

import SisFinIBCS.model.Evento;
import SisFinIBCS.model.LancamentoFinanceiro;
import SisFinIBCS.model.TipoLancamento;
import SisFinIBCS.repository.EventoRepository;
import SisFinIBCS.repository.LancamentoFinanceiroRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/*@Service: indica que a classe contém regras de negócio.
private final: protege as dependências.
construtor: faz injeção de dependência.
List<Evento>: coleção de objetos.
BigDecimal: ideal para valores financeiros.
stream(): percorre lançamentos para somar valores.
reduce(): acumula os valores.
POO: classes separadas por responsabilidade. */ 

@Service
public class FinanceiroService {

    private final EventoRepository eventoRepository;
    private final LancamentoFinanceiroRepository lancamentoRepository;

    public FinanceiroService(EventoRepository eventoRepository,
                             LancamentoFinanceiroRepository lancamentoRepository) {
        this.eventoRepository = eventoRepository;
        this.lancamentoRepository = lancamentoRepository;
    }

    public Evento salvarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public LancamentoFinanceiro salvarLancamento(LancamentoFinanceiro lancamento) {
        return lancamentoRepository.save(lancamento);
    }

    public List<LancamentoFinanceiro> listarLancamentos() {
        return lancamentoRepository.findAll();
    }

    public BigDecimal calcularTotalEntradas() {
        return lancamentoRepository.findByTipo(TipoLancamento.ENTRADA)
                .stream()
                .map(LancamentoFinanceiro::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularTotalSaidas() {
        return lancamentoRepository.findByTipo(TipoLancamento.SAIDA)
                .stream()
                .map(LancamentoFinanceiro::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularSaldo() {
        return calcularTotalEntradas().subtract(calcularTotalSaidas());
    }
}
