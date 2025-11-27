package org.example.service.acaocorretiva;

import org.example.model.AcaoCorretiva;
import org.example.model.Falha;
import org.example.repository.AcaoCorretivaRepository;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;

public class AcaoCorretivaServiceImpl implements AcaoCorretivaService{

    AcaoCorretivaRepository repository = new AcaoCorretivaRepository();
    FalhaRepository repository2 = new FalhaRepository();
    EquipamentoRepository repository3 = new EquipamentoRepository();

    @Override
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {

        Falha falha = repository2.

       acao = repository.criarAcaoCorretiva(acao);

       repository2.AtualizarStatus(acao.getFalhaId());


        return acao;
    }
}
