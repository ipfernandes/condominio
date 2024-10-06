package com.administradora.condominio.repository;

import com.administradora.condominio.entity.Condominio;
import com.administradora.condominio.entity.TipoCondominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class CondominioRepository {
    public boolean criarCondominio(Condominio condominio) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection conexao = null;
        boolean resultado = false;
        try {
            String sql = "Insert into condominio(nome, cnpj, tipo, criadoEm) values (?, ?, ?, ?)";

            conexao = ConnectionFactory.criarConexao();
            preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, condominio.getNome());
            preparedStatement.setString(2, condominio.getCnpj());
            preparedStatement.setString(3, condominio.getTipo().toString());
            preparedStatement.setObject(4, OffsetDateTime.now(ZoneOffset.UTC));

            resultado = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null) preparedStatement.close();
            if(conexao != null) ConnectionFactory.fecharConexao(conexao);
        }
        return resultado;
    }
    public Condominio buscarPorCnpj(String cnpj) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection conexao = null;

        String sql = "Select * from condominio where cnpj = ?";
        Condominio condominio = new Condominio();
        ResultSet rset = null;

        try{
            conexao = ConnectionFactory.criarConexao();
            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cnpj);
            rset = preparedStatement.executeQuery();
            while (rset.next()){
                condominio.setIdentificador(rset.getInt("identificador"));
                condominio.setNome(rset.getString("nome"));
                condominio.setCnpj(rset.getString("cnpj"));
                condominio.setTipo(TipoCondominio.defineTipo(rset.getString("tipo")));
                condominio.setDataCriacao(rset.getTimestamp("criadoEm").toInstant());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rset != null) rset.close();
            if(preparedStatement != null) preparedStatement.close();
            if(conexao != null) ConnectionFactory.fecharConexao(conexao);
        }
        return condominio;
    }

    public void deletarPorCnpj(String cnpj) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection conexao = null;

        String sql = "Delete from condominio where cnpj = ?";
        boolean resultado = false;

        try{
            conexao = ConnectionFactory.criarConexao();
            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cnpj);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(preparedStatement != null) preparedStatement.close();
            if(conexao != null) ConnectionFactory.fecharConexao(conexao);
        }
    }

    public boolean alterarCondominio(Condominio condominio) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection conexao = null;
        boolean resultado = false;
        try {
            String sql = "Update condominio set nome = ?, tipo = ?, criadoEm = ? Where identificador = ?";

            conexao = ConnectionFactory.criarConexao();
            preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, condominio.getNome());
            preparedStatement.setString(2, condominio.getTipo().toString());
            preparedStatement.setObject(3, OffsetDateTime.now(ZoneOffset.UTC));
            preparedStatement.setInt(4, condominio.getIdentificador());

            resultado = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null) preparedStatement.close();
            if(conexao != null) ConnectionFactory.fecharConexao(conexao);
        }
        return resultado;
    }

    public Condominio buscarPorIdentificador(int identificador) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection conexao = null;

        String sql = "Select * from condominio where identificador = ?";
        Condominio condominio = new Condominio();
        ResultSet rset = null;

        try{
            conexao = ConnectionFactory.criarConexao();
            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, identificador);
            rset = preparedStatement.executeQuery();
            while (rset.next()){
                condominio.setIdentificador(rset.getInt("identificador"));
                condominio.setNome(rset.getString("nome"));
                condominio.setCnpj(rset.getString("cnpj"));
                condominio.setTipo(TipoCondominio.defineTipo(rset.getString("tipo")));
                condominio.setDataCriacao(rset.getTimestamp("criadoEm").toInstant());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rset != null) rset.close();
            if(preparedStatement != null) preparedStatement.close();
            if(conexao != null) ConnectionFactory.fecharConexao(conexao);
        }
        return condominio;
    }
}