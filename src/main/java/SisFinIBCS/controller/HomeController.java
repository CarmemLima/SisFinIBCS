package SisFinIBCS.controller;

import SisFinIBCS.model.CategoriaEvento;
import SisFinIBCS.model.Evento;
import SisFinIBCS.model.LancamentoFinanceiro;
import SisFinIBCS.model.TipoLancamento;
import SisFinIBCS.service.FinanceiroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



/*O Que Esse Controller Faz
Quando alguém acessa: http://localhost:8080/ o método index() é executado.
Ele envia para a página:
totalEntradas
totalSaidas
saldo
eventos
lancamentos
E depois retorna:
return "index";
Isso significa que o Spring vai procurar um arquivo chamado: src/main/resources/templates/index.html */

@Controller
public class HomeController {

    private final FinanceiroService financeiroService;

    public HomeController(FinanceiroService financeiroService) {
        this.financeiroService = financeiroService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("totalEntradas", financeiroService.calcularTotalEntradas());
        model.addAttribute("totalSaidas", financeiroService.calcularTotalSaidas());
        model.addAttribute("saldo", financeiroService.calcularSaldo());
        model.addAttribute("eventos", financeiroService.listarEventos());
        model.addAttribute("lancamentos", financeiroService.listarLancamentos());

        model.addAttribute("novoEvento", new Evento());
        model.addAttribute("categorias", CategoriaEvento.values());

        model.addAttribute("novoLancamento", new LancamentoFinanceiro());
        model.addAttribute("tiposLancamento", TipoLancamento.values());

        return "index";
    }

    @PostMapping("/eventos")
    public String salvarEvento(Evento evento) {
        financeiroService.salvarEvento(evento);
        return "redirect:/";
    }

    @PostMapping("/lancamentos")
    public String salvarLancamento(LancamentoFinanceiro lancamento) {
        financeiroService.salvarLancamento(lancamento);
        return "redirect:/";
    }
}