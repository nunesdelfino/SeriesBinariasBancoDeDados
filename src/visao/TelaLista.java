package visao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.IModelo;
import modelo.Serie;
import visao.Jtable.ModeloJTableModel;
import visao.controle.VisaoControlePrincipal;


@SuppressWarnings("serial")
public class TelaLista extends JFrame {
	
	private JPanel contentPane;
	private VisaoControlePrincipal VisaoControle;
	private JTable table;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	

	/**
	 * Create the frame.
	 */
	public TelaLista(VisaoControlePrincipal VisaoCtrl) {
		super();
		this.VisaoControle = VisaoCtrl;
		InicializarTela();
	}
	
	public void InicializarTela() {
		this.setTitle("Lista de Séries");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 690, 360);
		setMinimumSize(new Dimension(600, 360));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Lista de S\u00E9ries");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		CriarTabela();
		
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setPreferredSize(new Dimension(130, 30));
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getVisaoControle().ExibirTelaPrincipal();
			}
		});
		btnNewButton_1.setPreferredSize(new Dimension(130, 30));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Excluir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExcluirLinha();
			}
		});
		btnNewButton_2.setPreferredSize(new Dimension(130, 30));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(btnNewButton_2);
		
	}
	
	private void Editar() {
		if(table.getSelectedRow() != -1) {
			int linha = table.getSelectedRow();
			ModeloJTableModel modeloJTableModel = (ModeloJTableModel)table.getModel();
			IModelo modelo = modeloJTableModel.getModeloLinha(linha);
			
			Serie s = (Serie) modelo;
			getVisaoControle().ExibirTelaCadastro(s, table.getSelectedRow());
		} else {
			JOptionPane.showMessageDialog(null, "Voc� precisa selecionar uma linha para ser editada", "Editar Linha", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void ExcluirLinha() {
		if(table.getSelectedRow() != -1) {
			JOptionPane.showMessageDialog(null, "Exclus�o realizada com sucesso", "Excluir", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Voc� precisa selecionar uma linha para ser removida", "Remover Linha", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
//	private void PreencherTabela() {
////		Serie TabelaSerie = new Serie();
////		table.setModel(new ModeloJTableModel(Lista, TabelaSerie));
//	}

	private void CriarTabela() {
		table = new JTable();
		ModeloJTableModel DataModelo = new ModeloJTableModel(new ArrayList<IModelo>(), new Serie());
		table.setModel(DataModelo);
	}

	public VisaoControlePrincipal getVisaoControle() {
		
		return VisaoControle;
	}

}
