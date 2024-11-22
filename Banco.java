import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class Banco {
    Connection conexao;
    String url = "jdbc:sqlite:bancoAULA14.db";
    public Banco(){
        // Conectando ao banco de dados
        try {
            conexao = DriverManager.getConnection(url);
            if (conexao != null) {
                System.out.println("Conectado ao SQLite.");
                // Criando uma tabela de exemplo
                String sql = "CREATE TABLE IF NOT EXISTS PESSOA (\n"
                        + " CPF   TEXT PRIMARY KEY,\n"
                        + " NOME  TEXT NOT NULL,\n"
                        + " IDADE INTEGER,\n"
                        + " SALARIO REAL\n"
                        + ");";                
                try (Statement comando = conexao.createStatement()) {
                    comando.execute(sql);
                    System.out.println("Tabela 'pessoa' criada ou já existe.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    ///////// Inserindo dados na tabela ////////////////
    public void inserePessoaBanco(Pessoa p){
        System.out.println("Inserindo:");
        System.out.println(p.sqlINSERT());
        try{
            Statement comando = conexao.createStatement();
            comando.executeUpdate(p.sqlINSERT());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
    }
    /////////////////////////////////////////////////////
    //// ALterando dados na tabela /////
    public void updatePessoaBanco(Pessoa p){
        System.out.println("Alterando:");
        System.out.println(p.sqlUPDATE());
        try{
            Statement comando = conexao.createStatement();
            comando.executeUpdate(p.sqlUPDATE());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
    }
     //// Apagando dados da tabela ///////////////////////
    public void deletePessoaBanco(Pessoa p){
        System.out.println("Removendo:");
        System.out.println(p.sqlDELETE());
        try{
            Statement comando = conexao.createStatement();
            comando.executeUpdate(p.sqlDELETE());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
    }
    //// Obder dados de UMA pessoa da tabela ///////////////////////
    public void queryPessoaBanco(Pessoa p, ArrayList<Pessoa> listaResposta){
        System.out.println("Consultando:");
        System.out.println(p.sqlQUERYCPF());
        try{
            Statement comando = conexao.createStatement();
            ResultSet resultado = comando.executeQuery(p.sqlQUERYCPF());
            if (resultado.next()) {
                String nome    = resultado.getString("NOME");
                String cpf     = resultado.getString("CPF");
                int idade      = resultado.getInt("IDADE");
                double salario = resultado.getDouble("SALARIO");
                p = new Pessoa(nome,cpf,idade,salario);
                listaResposta.add(p);                
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }   
    }
    public void queryPessoasBanco(ArrayList<Pessoa> listaResposta){
        System.out.println("Consultando:");
        Pessoa p = new Pessoa();
        System.out.println(p.sqlQUERYALL());
        try{
            Statement comando = conexao.createStatement();
            ResultSet resultado = comando.executeQuery(p.sqlQUERYALL());
            while (resultado.next()) {
                String nome    = resultado.getString("NOME");
                String cpf     = resultado.getString("CPF");
                int idade      = resultado.getInt("IDADE");
                double salario = resultado.getDouble("SALARIO");
                p = new Pessoa(nome,cpf,idade,salario);
                listaResposta.add(p);                
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
    }
}
