package br.com.fatec.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory{
    private String usuario = "root";
    private String senha = "linux";    
	private String host = "localhost";
    private String porta = "3306";
    private String db = "20212_fatec_ipi_poo_pessoas";
    public Connection obterConexao(){
        String stringDeConexao = String.format(
            "jdbc:mysql://%s:%s/%s",
            host, porta, db
        );
        try {
            return DriverManager.getConnection(
                stringDeConexao,
                usuario,
                senha
            );
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        System.out.println(connectionFactory.obterConexao());
    }
}