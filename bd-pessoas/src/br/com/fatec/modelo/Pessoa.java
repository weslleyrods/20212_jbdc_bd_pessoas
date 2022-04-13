package br.com.fatec.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.conexao.ConnectionFactory;

public class Pessoa {
	private int codigo;
	private String nome;
	private String fone;
	private String email;

	public Pessoa(String n, String f, String e) {
		this.nome = n;
		this.fone = f;
		this.email = e;

	}

	public Pessoa(int c, String n, String f, String e) {
		this(n, f, e);
		this.codigo = c;
	}

	public void inserir() {
		try {
			// 1. Especificar o comando SQL
			String sql = "INSERT INTO tb_pessoas(nome, fone, email) VALUES(?, ?, ?)";// ? placeholders
			// 2. Abrir uma conexão
			ConnectionFactory factory = new ConnectionFactory();
			Connection connection = factory.obterConexao();
			// 3 . Pré compilar o comando
			PreparedStatement ps = connection.prepareStatement(sql);
			// 4. Preencher dados faltantes
			ps.setString(1, nome);
			ps.setString(2, fone);
			ps.setString(3, email);
			// 5. Executar o comando
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar() {
		// try with resources <<!! IMPORTANTE
		try (Connection conexao = new ConnectionFactory().obterConexao()) {
			String sql = "UPDATE tb_pessoas SET nome=?, fone=?, email=? WHERE codigo=?";
			PreparedStatement ps = conexao.prepareCall(sql);
			ps.setString(1, nome);
			ps.setString(2, fone);
			ps.setString(3, email);
			ps.setInt(4, codigo);
			ps.execute();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		// Connection conexao = null;
		// try{
		// //1. Abrir uma conexão com o SGBD
		// ConnectionFactory connectionFactory = new ConnectionFactory();
		// conexao = connectionFactory.obterConexão();
		// //2. Especificar o comando SQL
		// //3. Pré compilar o comando
		// //4. Preencher os dados faltantes
		// //5. Executar o comando
		// //6 . Fechar a conexão
		// conexao.close();
		// }
		// catch (Exception e){
		// }
		// finally{
		// try{
		// conexao.close();
		// } catch (SQLException e1){
		// e1.printStackTrace();
		// }
		// }

	}

	public static void delete(int codigo) {
		ConnectionFactory factory = new ConnectionFactory();
		// 2. Especificar o comando SQL - ok
		// 3. Pré compilar o comando
		// 4. Preencher os dados faltantes
		// 5. Executar o comando
		// 6 . Fechar a conexão
		try (Connection con = factory.obterConexao();) {
			String sql = "DELETE FROM tb_pessoas WHERE codigo=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 1. Abrir uma conexão com o SGBD
	// 2. Especificar o comando SQL - ok
	// 3. Pré compilar o comando
	// 4. Preencher os dados faltantes
	// 5. Executar o comando
	// 6 . Fechar a conexão

	public static List<Pessoa> selecionar(int codigo) {
		ConnectionFactory factory = new ConnectionFactory();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
//		Pessoa[] pessoasv = new Pessoa[10];
		try (Connection con = factory.obterConexao();) {
			String sql = "SELECT * FROM tb_pessoas WHERE codigo=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
//			int i = 0;
			while (rs.next()) {
				Pessoa p = new Pessoa(rs.getInt("codigo"), rs.getString("nome"), rs.getString("fone"),
						rs.getString("email"));
				pessoas.add(p);
//				pessoasv[i] = p;
//				i++;
			}
			return pessoas;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Deu ruim");
			return null;
		}
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getFone() {
		return fone;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "codigo=" + codigo + " \nnome=" + nome + " \nfone=" + fone + " \nemail=" + email + "\n";
	}

}
