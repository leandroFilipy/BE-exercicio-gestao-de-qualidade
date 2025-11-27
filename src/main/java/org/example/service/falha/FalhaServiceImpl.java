package org.example.service.falha;

import org.example.model.Falha;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;
import java.util.List;

public class FalhaServiceImpl implements FalhaService{

    FalhaRepository repository = new FalhaRepository();
    EquipamentoRepository repository2 = new EquipamentoRepository();


    @Override
    public Falha registrarNovaFalha(Falha falha) throws SQLException {

        falha.setStatus("ABERTA");
        falha = repository.criarFalha(falha);

        if(repository2.buscarEquipamento(falha.getEquipamentoId()) == null){
            throw new IllegalArgumentException("Equipamento não encontrado!");
        }

        if (falha.getCriticidade().equals("CRITICA")){



            repository2.atualizarStatus(falha.getEquipamentoId());
        }

        return falha;
    }

    @Override
    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {


        return repository.listarFalhas();
    }
}
