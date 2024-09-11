package cep;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/8541853_home_icon.png")));
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar CEP - Versao 1.0");
		lblNewLabel.setBounds(27, 54, 242, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("@Autor JLimaCoder");
		lblNewLabel_1.setBounds(27, 79, 271, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("WEB SERVICE:");
		lblNewLabel_2.setBounds(27, 104, 86, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblWebService = new JLabel("republicavirtual.com.br");
		lblWebService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://www.republicavirtual.com.br/");
			}
		});
		lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblWebService.setBounds(117, 104, 193, 14);
		lblWebService.setForeground(SystemColor.textHighlight);
		lblWebService.setBackground(new Color(255, 255, 255));
		getContentPane().add(lblWebService);
		
		JButton btnGitHub = new JButton("");
		btnGitHub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://github.com/JLimaCoder");
			}
		});
		btnGitHub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnGitHub.setToolTipText("Projeto");
		btnGitHub.setBorder(null);
		btnGitHub.setBounds(27, 189, 48, 48);
		btnGitHub.setForeground(SystemColor.control);
		btnGitHub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGitHub.setBackground(SystemColor.control);
		btnGitHub.setIcon(new ImageIcon(Sobre.class.getResource("/img/4202858_github_icon (1).png")));
		getContentPane().add(btnGitHub);

	} // fim do construtor
	
	private void link (String site) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI (site);
			desktop.browse(uri);
		}catch (Exception e) {
			System.out.println(e);
			
		}
	}
	
	
}
