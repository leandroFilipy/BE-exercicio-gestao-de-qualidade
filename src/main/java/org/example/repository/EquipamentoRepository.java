package org.example.repository;

import org.example.database.Conexao;
import org.example.model.Equipamento;

import java.sql.*;

public class EquipamentoRepository {



    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {

        String query = """
                INSERT INTO Equipamento
                 (nome, numeroDeSerie, areaSetor, statusOperacional)
                 VALUES (?,?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroDeSerie());
            stmt.setString(3, equipamento.getAreaSetor());
            stmt.setString(4, equipamento.getStatusOperacional());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){

                equipamento.setId(rs.getLong(1));
            }

        }

        return equipamento;
    }

    public Equipamento buscarEquipamento(long id) throws SQLException{

        String query= """
                SELECT nome, numeroDeSerie, areaSetor, statusOperacional
                FROM Equipamento 
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){


            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            Equipamento equipamento;

            if(rs.next()){
                return equipamento = new Equipamento(
                        id,
                        rs.getString("nome"),
                        rs.getString("numeroDeSerie"),
                        rs.getString("areaSetor"),
                        rs.getString("statusOperacional"));
            }
        }
        return null;
    }

    public void atualizarStatus(long id) throws SQLException{

        String query = "UPDATE Equipamento SET statusOperacional = 'EM_MANUTENCAO' WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, id);
            stmt.executeUpdate();


        }
    }

    public void atualizarStatus2 (long id) throws SQLException{

        String query = """
                UPDATE Equipamento SET statusOperacional = 'OPERACIONAL'
                WHERE id in (SELECT falhaId )
                """;

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }



}
