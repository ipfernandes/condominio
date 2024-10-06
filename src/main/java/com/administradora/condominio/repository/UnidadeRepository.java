package com.administradora.condominio.repository;

import com.administradora.condominio.entity.Bloco;
import com.administradora.condominio.entity.Unidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UnidadeRepository {
    public int criarUnidade(Unidade unidade) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection conexao = null;
        int resultado = 0;
        try {
            String sql = "Insert into unidade(nome, metragem_quadrada, Bloco_identificador) values (?, ?, ?)";

            conexao = ConnectionFactory.criarConexao();
            preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, unidade.getNome());
            preparedStatement.setDouble(2, unidade.getMetragemQuarada());
            preparedStatement.setInt(3, unidade.getBloco().getIdentificador());

            resultado = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null) preparedStatement.close();
            if(conexao != null) ConnectionFactory.fecharConexao(conexao);
        }
        return resultado;
    }

    public List<Unidade> buscarUnidadesDoBloco(int identificadorBloco) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection conexao = null;

        String sql = "Select * from unidade where Bloco_identificador = ?";
        List<Unidade> lista = new ArrayList<>();

        ResultSet rset = null;

        try{
            conexao = ConnectionFactory.criarConexao();
            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, identificadorBloco);
            rset = preparedStatement.executeQuery();
            while (rset.next()){
                Unidade unidade = new Unidade();
                unidade.setIdentificador(rset.getInt("identificador"));
                unidade.setNome(rset.getString("nome"));
                unidade.setMetragemQuarada(rset.getDouble("metragem_quadrada"));
                lista.add(unidade);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rset != null) rset.close();
            if(preparedStatement != null) preparedStatement.close();
            if(conexao != null) ConnectionFactory.fecharConexao(conexao);
        }
        return lista;
    }
}