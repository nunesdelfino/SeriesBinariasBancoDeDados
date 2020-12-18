package visao;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import exception.ConverterException;
import modelo.Serie;
import net.miginfocom.swing.MigLayout;
import utils.DateSql;

public class PainelCadastro extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel txtNome;
	private JLabel txtDataLancamento;
	private JLabel txtEncerrada;
	private JLabel txtNumTemporadas;
	private JLabel txtNumEpisodios;
	private JLabel txtGenero;
	private JTextField inpNome;
	private JTextField inpNumTemporadas;
	private JTextField inpNumEpisodios;
	private Integer episodios;
	private Integer temporadas;
	private JRadioButton btnSim;
	private JRadioButton btnNao;
	private JDateChooser inpDataLancamento;
	private JComboBox<String> cmbGenero;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public PainelCadastro() {
		super();
		inicializaTela();
	}
	
	private void inicializaTela() {
		
		setLayout(new MigLayout("", "[][grow,left]", "[][][][][][][][]"));
		
		this.txtNome = new JLabel("Nome da série:");
		this.txtNome.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		
		
		this.txtDataLancamento= new JLabel("Data de lançamento:");
		this.txtDataLancamento.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		
		
		this.txtEncerrada = new JLabel("Está encerrada?");
		this.txtEncerrada.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		
		
		this.txtNumTemporadas = new JLabel("Número de teporadas:");
		this.txtNumTemporadas.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		
		
		this.txtNumEpisodios = new JLabel("Número de episódios:");
		this.txtNumEpisodios.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		
		this.txtGenero = new JLabel("Gênero da série:");
		this.txtGenero.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		
		this.inpNome = new JTextField();
		this.inpNome.setColumns(10);
		
		this.inpDataLancamento = new JDateChooser();
		JTextFieldDateEditor textField = (JTextFieldDateEditor) this.inpDataLancamento.getDateEditor();
		textField.setEditable(false);
		
		this.btnSim = new JRadioButton("Sim");
		this.btnNao = new JRadioButton("Não");
		this.btnNao.setSelected(true);
		this.buttonGroup.add(this.btnNao);
		this.buttonGroup.add(this.btnSim);
		
		this.inpNumTemporadas = new JTextField();
		this.inpNumTemporadas.setColumns(10);

		
		this.inpNumEpisodios = new JTextField();
		this.inpNumEpisodios.setColumns(10);
		
		
		this.cmbGenero = new JComboBox<String>();
		String[] lista = new String[] {"Ação", "Comédia", "Ficção Científica", "Romance", "Suspense", "Terror"};
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>(lista);
		cmbGenero.setModel(modelo);
		
		
		add(this.txtNome, "cell 0 1,alignx left");
		add(this.inpNome, "cell 1 1,growx");
		add(this.txtDataLancamento, "cell 0 2,alignx left");
		add(this.inpDataLancamento, "cell 1 2,growx,aligny center");
		add(this.txtEncerrada, "cell 0 3,alignx left");
		add(this.btnSim, "flowx,cell 1 3,alignx left");
		add(this.btnNao, "cell 1 3");
		add(this.txtNumTemporadas, "cell 0 4,alignx left");
		add(this.inpNumTemporadas, "cell 1 4,growx");
		add(this.txtNumEpisodios, "cell 0 5,alignx left");
		add(this.inpNumEpisodios, "cell 1 5,growx");
		add(this.txtGenero, "cell 0 6,alignx left");
		add(this.cmbGenero, "cell 1 6,growx");
		
		
		
	}
	
	public Serie getDadosTela() {
		Serie serie = null;
		if(!this.inpNome.getText().equals("") &&
				this.inpDataLancamento.getDate() != null &&
				!this.inpNumEpisodios.getText().equals("") &&
				!this.inpNumTemporadas.getText().equals("")) {
			
			validaInteger();
			
			serie = new Serie();
			
			serie.setNome(this.inpNome.getText());
			serie.setTemporadas(this.temporadas);
			serie.setEpisodios(this.episodios);
			serie.setLancamento(DateSql.converteDataParaSql(this.inpDataLancamento.getDate()));
			serie.setGenero(cmbGenero.getSelectedItem().toString());
			serie.setEncerrada(this.btnSim.isSelected()?true:false);
			
			
		}
		
		return serie;
		
	}
	
	private void validaInteger() {
		try {
			this.episodios = Integer.parseInt(this.inpNumEpisodios.getText());
			this.temporadas = Integer.parseInt(this.inpNumTemporadas.getText());
		} catch (NumberFormatException e) {
			throw new ConverterException("Verifique os campos Temporadas e Episódios pois contem caracteres não numéricos", e);
		}
	}
	
}
