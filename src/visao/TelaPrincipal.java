package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import visao.controle.VisaoControlePrincipal;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private VisaoControlePrincipal VisaoControle;
	ImageIcon fundo = new ImageIcon(TelaPrincipal.class.getResource("/image/series.png"));


	/**
	 * Create the frame.
	 */
	public TelaPrincipal(VisaoControlePrincipal VisaoCtrl) {
		super();
		this.VisaoControle = VisaoCtrl;
		InicializarTela();
		
	}
	
	private void InicializarTela() {
		this.setTitle("Séries Binárias");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 600, 360);
		setMinimumSize(new Dimension(600, 360));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(fundo);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton BtnCadastrar = new JButton("Cadastrar séries");
		BtnCadastrar.setPreferredSize(new Dimension(150, 30));
		
		BtnCadastrar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getVisaoControle().ExibirTelaCadastro();
			}
		});
		
		panel.add(BtnCadastrar);
		
		JButton BtnListar = new JButton("Listar séries");
		BtnListar.setPreferredSize(new Dimension(150, 30));
		
		BtnListar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getVisaoControle().ExibirTelaLista();
			}
		});
		
		panel.add(BtnListar);
		
	}
	
	public VisaoControlePrincipal getVisaoControle() {
		return VisaoControle;
	}

}
