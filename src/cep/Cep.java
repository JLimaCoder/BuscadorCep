package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

public class Cep extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCep;
    private JTextField txtEndereco;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JLabel lblStatus;
    private JComboBox<String> cboUf; // Declaração como variável de instância

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cep frame = new Cep();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Cep() {
        setTitle("Buscar CEP");
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/9035826_earth_sharp_icon.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnSobre = new JButton("");
        btnSobre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sobre sobre = new Sobre();
                sobre.setVisible(true);
            }
        });
        btnSobre.setToolTipText("Sobre");
        btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/9110768_question_circle_icon.png")));
        btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSobre.setBorder(null);
        btnSobre.setBackground(SystemColor.control);
        btnSobre.setBounds(365, 202, 48, 48);
        contentPane.add(btnSobre);

        
        JLabel lblNewLabel = new JLabel("CEP");
        lblNewLabel.setBounds(3, 30, 46, 14);
        contentPane.add(lblNewLabel);
        
        txtCep = new JTextField();
        txtCep.setBounds(30, 27, 100, 20);
        contentPane.add(txtCep);
        txtCep.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Endereço");
        lblNewLabel_1.setBounds(3, 74, 54, 14);
        contentPane.add(lblNewLabel_1);
        
        txtEndereco = new JTextField();
        txtEndereco.setBounds(64, 71, 322, 20);
        contentPane.add(txtEndereco);
        txtEndereco.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Bairro");
        lblNewLabel_2.setBounds(10, 99, 39, 14);
        contentPane.add(lblNewLabel_2);
        
        txtBairro = new JTextField();
        txtBairro.setBounds(64, 96, 322, 20);
        contentPane.add(txtBairro);
        txtBairro.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Cidade");
        lblNewLabel_3.setBounds(10, 124, 46, 14);
        contentPane.add(lblNewLabel_3);
        
        txtCidade = new JTextField();
        txtCidade.setBounds(65, 121, 182, 20);
        contentPane.add(txtCidade);
        txtCidade.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("UF");
        lblNewLabel_4.setBounds(296, 124, 23, 14);
        contentPane.add(lblNewLabel_4);
        
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(10, 165, 89, 23);
        contentPane.add(btnLimpar);
        
        cboUf = new JComboBox<>(); // Inicialização do JComboBox
        cboUf.setModel(new DefaultComboBoxModel<>(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
        cboUf.setBounds(320, 120, 46, 22);
        contentPane.add(cboUf);
        
        JButton btnCep = new JButton("Buscar");
        btnCep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtCep.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "INFORME O CEP");
                    txtCep.requestFocus();
                } else {
                    buscarCep(); // Chama o método de buscar o CEP
                }
            }
        });

        btnCep.setBounds(164, 26, 89, 23);
        contentPane.add(btnCep);
        
        // Botão Limpar
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpar(); // Chama o método limpar
            }
        });
        
        // Validação do txtCep
        RestrictedTextField validar = new RestrictedTextField(txtCep);
        validar.setOnlyNums(true);
        validar.setLimit(8);
        
        lblStatus = new JLabel("");
        lblStatus.setIcon(new ImageIcon(Cep.class.getResource("/img/103183_checkround_check_yes_ok_accept_icon.png")));
        lblStatus.setBounds(134, 27, 20, 20);
        contentPane.add(lblStatus);
    }

    private void buscarCep() {
        String logradouro = "";
        String tipoLogradouro = "";
        String resultado = null;
        String cep = txtCep.getText();
        try {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
            SAXReader xml = new SAXReader();
            Document documento = xml.read(url);
            Element root = documento.getRootElement();
            for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
                Element element = it.next();
                if (element.getQualifiedName().equals("cidade")) {
                    txtCidade.setText(element.getText());
                }
                    
                if (element.getQualifiedName().equals("uf")) {
                    cboUf.setSelectedItem(element.getText());
                }
                
                if (element.getQualifiedName().equals("bairro")) {
                    txtBairro.setText(element.getText());
                }
                if (element.getQualifiedName().equals("tipo_logradouro")) {
                    tipoLogradouro = element.getText();
                }
                
                if (element.getQualifiedName().equals("logradouro")) {
                    logradouro = element.getText();
                }
                
                if (element.getQualifiedName().equals("resultado")) {
                    resultado = element.getText();
                    if (resultado.equals("1")) {
                        lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/103183_checkround_check_yes_ok_accept_icon.png")));
                    } else {
                        JOptionPane.showMessageDialog(null, "CEP NÃO ENCONTRADO");
                        lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/7787528_good_ok_check_mark_list_icon (1).png")));
                    }
                }
            }
            // Define o campo endereço com o tipo de logradouro e o logradouro
            txtEndereco.setText(tipoLogradouro + " " + logradouro);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void limpar() {
        // Limpa todos os campos
        txtCep.setText(null);
        txtEndereco.setText(null);
        txtBairro.setText(null);
        txtCidade.setText(null);
        cboUf.setSelectedItem(""); // Limpa a seleção do ComboBox
        txtCep.requestFocus(); // Define o foco no campo de CEP
        
        // Remove o ícone de status, garantindo que ele desapareça
        lblStatus.setIcon(null); // Isso limpa o ícone do label
    }
}
