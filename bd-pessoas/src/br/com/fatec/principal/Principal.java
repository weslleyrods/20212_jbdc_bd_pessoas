package br.com.fatec.principal;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.fatec.modelo.Pessoa;

public class Principal {

	public static void main(String[] args) {
		String menu = "1-Cadastrar\n2-Atualizar\n3Apagar\n4-Listar\n0-Sair";
		int op;

		do {
			op = Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch (op) {
			case 1: {
				String n = JOptionPane.showInputDialog("Nome?");
				String f = JOptionPane.showInputDialog("Fone?");
				String e = JOptionPane.showInputDialog("Email?");
				Pessoa p = new Pessoa(n, f, e);
				p.inserir();
				JOptionPane.showConfirmDialog(null, "Pessoa cadastrada");
				break;
			}
			case 2: {
				String n = JOptionPane.showInputDialog("Nome?");
				String f = JOptionPane.showInputDialog("Fone?");
				String e = JOptionPane.showInputDialog("Email?");
				int c = Integer.parseInt(JOptionPane.showInputDialog("Código?"));
				Pessoa p = new Pessoa(c, n, f, e);
				p.atualizar();
				JOptionPane.showConfirmDialog(null, "Pessoa atualizada");
				break;
			}
			case 3: {
				int c = Integer.parseInt(JOptionPane.showInputDialog("Código?"));
				Pessoa.delete(c);
				JOptionPane.showMessageDialog(null, "Cadastro deletado");
				break;
			}
			case 4: {
				Scanner scan = new Scanner(System.in);
				 int c = scan.nextInt();
				List<Pessoa> pessoas = Pessoa.selecionar(c);
				try {
					for (Pessoa pessoa : pessoas) {
						System.out.println(pessoa);
//						System.out.println(pessoa.getNome());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				scan.close();
				break;
			}
			}
		} while (op != 0);
	}

}
