package com.administradora.condominio.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static final String usuario = "root";
    private static final String senha = "administrador";
    private static final String urlBanco = "jdbc:mysql://localhost:3306/condominio";

    public static Connection criarConexao() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexao = DriverManager.getConnection(urlBanco, usuario, senha);
        return conexao;
    }
    public static void fecharConexao(Connection conexao) throws Exception{
        if(conexao != null){
            conexao.close();
        }
    }
}