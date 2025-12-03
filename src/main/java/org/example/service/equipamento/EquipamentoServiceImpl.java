package org.example.service.equipamento;

import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;

import java.sql.SQLException;

public class EquipamentoServiceImpl implements EquipamentoService {

    EquipamentoRepository repository = new EquipamentoRepository();

    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {

        equipamento.setStatusOperacional("OPERACIONAL");

        repository.criarEquipamento(equipamento);

        return equipamento;
    }

    @Override
    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {

        Equipamento equipamento = repository.buscarEquipamentoPorId(id);

        if (equipamento == null) {
            throw new RuntimeException("Equipamento não encontrado!");
        }

        return equipamento;
    }
}


