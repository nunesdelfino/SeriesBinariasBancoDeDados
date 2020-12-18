package visao.controle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.Serie;
import visao.TelaCadastro;
import visao.TelaLista;
import visao.TelaPrincipal;

public class VisaoControlePrincipal {
	
	private TelaPrincipal TelaPrincipal = null;
	private TelaCadastro TelaCadastro = null;
	private TelaLista TelaLista = null;
	
	private JFrame TelaExibindo = null;
	
	public VisaoControlePrincipal() {
		TelaPrincipal = new TelaPrincipal(this);
	}
	
	public void FecharTelaExibindo() {
		if(TelaExibindo != null) {
			TelaExibindo.dispose();
		}
	}
	
	public void ExibirTelaPrincipal() {
		this.FecharTelaExibindo();
		TelaExibindo = TelaPrincipal;
		TelaExibindo.setVisible(true);
	}
	
	public void ExibirTelaCadastro() {
		this.FecharTelaExibindo();
		this.TelaCadastro = new TelaCadastro(this);
		TelaExibindo = TelaCadastro;
		TelaExibindo.setVisible(true);
	}
	
	public void ExibirTelaCadastro(Serie s, int posicao) {
		this.FecharTelaExibindo();
		this.TelaCadastro = new TelaCadastro(this, s, posicao);
		TelaExibindo = TelaCadastro;
		TelaExibindo.setVisible(true);
	}
	
	public void ExibirTelaLista() {
		this.FecharTelaExibindo();
		this.TelaLista = new TelaLista(this);
		TelaExibindo = TelaLista;
		TelaExibindo.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		String mensagem = "Não foi possivel importar Modelo de interface";
		String titulo = "Modelo de interface";
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException e) {
			mensagemErro(mensagem, titulo);
		}catch (IllegalAccessException | UnsupportedLookAndFeelException e) {
			mensagemErro(mensagem, titulo);
		}
		
		VisaoControlePrincipal ctl = new VisaoControlePrincipal();
		
		ctl.ExibirTelaPrincipal();
	}
	
	public static void mensagemErro(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	

}
