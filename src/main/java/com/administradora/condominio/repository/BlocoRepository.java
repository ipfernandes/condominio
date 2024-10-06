package com.administradora.condominio.repository;

import com.administradora.condominio.entity.Bloco;
import com.administradora.condominio.entity.Condominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BlocoRepository {
    public boolean criarBloco(Bloco bloco) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection conexao = null;
        boolean resultado = false;
        try {
            String sql = "Insert into bloco(nome, condominio_identificador) values (?, ?)";

            conexao = ConnectionFactory.criarConexao();
            preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, bloco.getNome());
            preparedStatement.setInt(2, bloco.getCondominio().getIdentificador());

            resultado = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null) preparedStatement.close();
            if(conexao != null) ConnectionFactory.fecharConexao(conexao);
        }
        return resultado;
    }
    public List<Bloco> buscarBloco(String nome) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection conexao = null;

        String sql = "Select * from bloco where nome like ?";
        List<Bloco> lista = new ArrayList<>();

        ResultSet rset = null;

        try{
            conexao = ConnectionFactory.criarConexao();
            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nome + "%");
            rset = preparedStatement.executeQuery();
            while (rset.next()){
                Bloco bloco = new Bloco();
                Condominio condominio = new Condominio();
                bloco.setIdentificador(rset.getInt("identificador"));
                bloco.setNome(rset.getString("nome"));
                condominio.setIdentificador(rset.getInt("condominio_identificador"));
                bloco.setCondominio(condominio);
                lista.add(bloco);
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

    public Bloco buscarBlocodoCondominio(String nomeBloco, int identificador_condominio) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection conexao = null;
        String sql = "SELECT b.identificador, b.nome FROM condominio as c " +
                "INNER JOIN bloco as b " +
                "ON c.identificador = b.condominio_identificador " +
                "WHERE c.identificador = ? " +
                "AND b.nome = ?";

        Bloco bloco = new Bloco();

        ResultSet rset = null;

        try{
            conexao = ConnectionFactory.criarConexao();
            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, identificador_condominio);
            preparedStatement.setString(2, nomeBloco);

            rset = preparedStatement.executeQuery();
            while (rset.next()){
                bloco.setIdentificador(rset.getInt(1));
                bloco.setNome(rset.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rset != null) rset.close();
            if(preparedStatement != null) preparedStatement.close();
            if(conexao != null) ConnectionFactory.fecharConexao(conexao);
        }
        return bloco;
    }

    public List<Bloco> buscarTodosBlocosDoCondominio(int identificador_condominio) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection conexao = null;
        String sql = "SELECT * from bloco where condominio_identificador = ?";

        List<Bloco> blocos = new ArrayList<>();

        ResultSet rset = null;

        try{
            conexao = ConnectionFactory.criarConexao();
            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, identificador_condominio);

            rset = preparedStatement.executeQuery();
            while (rset.next()){
                Bloco bloco = new Bloco();
                bloco.setIdentificador(rset.getInt("identificador"));
                bloco.setNome(rset.getString("nome"));
                blocos.add(bloco);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rset != null) rset.close();
            if(preparedStatement != null) preparedStatement.close();
            if(conexao != null) ConnectionFactory.fecharConexao(conexao);
        }
        return blocos;
    }
}