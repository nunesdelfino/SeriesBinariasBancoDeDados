package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import controle.ControleSerie;
import exception.ConverterException;
import modelo.Serie;
import net.miginfocom.swing.MigLayout;
import utils.DateSql;
import visao.controle.VisaoControlePrincipal;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnSalvar;
	private JButton btnListar;
	private JDateChooser inpDataLancamento;
	private VisaoControlePrincipal VisaoControle;
	private JLabel tituloPrincipal;
	private JButton btnCancelar;
	private JPanel panel_2;
	private ControleSerie ctr = new ControleSerie();
	private PainelCadastro painelCad = new PainelCadastro();

	
	String tituloJanela = "Cadastro de Séries";
	
	Integer posicao;
	private JComboBox<String> cmbGenero;
	
	/**
	 * @wbp.parser.constructor
	 */
	public TelaCadastro(VisaoControlePrincipal VisaoCtrl) {
		super();
		this.VisaoControle = VisaoCtrl;
		InicializarTela();
	}
	
	public TelaCadastro(VisaoControlePrincipal VisaoCtrl, Serie s, int posicao) {
		super();
		this.VisaoControle = VisaoCtrl;
		this.tituloJanela = "Ediar Série";
		InicializarTela();
//		DefineDados(s);
		this.posicao = posicao;
		
	}

	/**
	 * Create the frame.
	 */
	public void InicializarTela() {
		setTitle(tituloJanela);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 445, 360);
		setMinimumSize(new Dimension(600, 360));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tituloPrincipal = new JLabel("Cadastro de s\u00E9ries");
		tituloPrincipal.setFont(new Font("Perpetua Titling MT", Font.PLAIN, 18));
		tituloPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(tituloPrincipal, BorderLayout.NORTH);
		
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		painelCad = new PainelCadastro();
		panel.add(painelCad);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				SalvarDados();
				try {
					Serie s = painelCad.getDadosTela();
					if(s != null) {
						System.out.println(s);
					} else {
						System.out.println("Vazio");
					}
				} catch (ConverterException exceptionConverter) {
					JOptionPane.showMessageDialog(null, exceptionConverter.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				
			}
		});
		btnSalvar.setPreferredSize(new Dimension(130, 30));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_2.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getVisaoControle().ExibirTelaPrincipal();
			}
		});
		btnCancelar.setPreferredSize(new Dimension(130, 30));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_2.add(btnCancelar);
		
		btnListar = new JButton("Visualizar lista");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getVisaoControle().ExibirTelaLista();
			}
		});
		btnListar.setPreferredSize(new Dimension(130, 30));
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_2.add(btnListar);
		
	}
	
	public VisaoControlePrincipal getVisaoControle() {
		return VisaoControle;
	}
	
	// TODO
//	public void DefineDados(Serie s) {
//		
//		this.inpNome.setText(s.getNome());
//		this.inpNumTemporadas.setText(Integer.toString(s.getTemporadas()));
//		this.inpNumEpisodios.setText(Integer.toString(s.getEpisodios()));
//		if(s.isEncerrada()) {
//			this.btnSim.setSelected(true);
//		} else {
//			this.btnNao.setSelected(true);
//		}
//		this.inpDataLancamento.setDate(s.getLancamento());
//		this.btnSalvar.setText("Editar");
//		
//	}
	
	public void editar() {
//		valoresSalvar();
		getVisaoControle().ExibirTelaLista();
		JOptionPane.showMessageDialog(null, "Edição realizada com sucesso", "Editar", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void salvar() {
		Boolean valida = null;
//		valida = valoresSalvar();
		if(valida == true) {
			JOptionPane.showMessageDialog(null, "Série salva com sucesso", "Salvar", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// TODO
//	public boolean valoresSalvar() {
//		if(!this.inpNome.getText().equals("") &&
//				this.inpDataLancamento.getDate() != null &&
//				!this.inpNumEpisodios.getText().equals("") &&
//				!this.inpNumTemporadas.getText().equals("")) {
//
//			Integer Episodios;
//			Integer Temporadas;
//			Boolean Encerrada;
//			
//			Episodios = Integer.parseUnsignedInt(this.inpNumEpisodios.getText());
//			Temporadas = Integer.parseUnsignedInt(this.inpNumTemporadas.getText());
//			
//			if(this.btnSim.isSelected()) {
//				Encerrada = true;
//			} else {
//				Encerrada = false;
//			}
//			
//			Serie s = new Serie();
//			s.setNome(this.inpNome.getText());
//			s.setEpisodios(Episodios);
//			s.setTemporadas(Temporadas);
//			s.setEncerrada(Encerrada);
//			s.setLancamento(DateSql.converteData(this.inpDataLancamento.getDate()));
//			
//			switch(this.cmbGenero.getSelectedItem().toString()) {
//				case "Ação":
//					s.setGenero("A");
//					break;
//				case "Romance":
//					s.setGenero("R");
//					break;
//				case "Suspense":
//					s.setGenero("S");
//					break;
//				case "Terror":
//					s.setGenero("T");
//					break;
//				case "Comédia":
//					s.setGenero("C");
//					break;
//				default:
//					s.setGenero("A");
//			}
//			
//			s = ctr.incluirSerie(s);
//			
//			LimparTela();
//			
//			return true;
//			
//		} else {
//			String Mensagem = "Todos os campos devem estar preenchidos!";
//			JOptionPane.showMessageDialog(null, Mensagem, null, JOptionPane.INFORMATION_MESSAGE);
//			return false;
//		}
//	}
	
	public void SalvarDados() {
		if(this.btnSalvar.getText().equals("Salvar")) {
			salvar();
		} else if(this.btnSalvar.getText().equals("Editar")) {
			editar();
		}
	}

	// TODO
//	private void LimparTela() {
//		this.inpNome.setText("");
//		this.inpNumTemporadas.setText("");
//		this.inpNumEpisodios.setText("");
//		this.btnSim.setSelected(false);
//		this.btnNao.setSelected(false);
//		this.inpDataLancamento.setDate(null);
//	}

}