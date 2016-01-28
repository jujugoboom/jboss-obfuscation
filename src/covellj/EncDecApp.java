package covellj;

import java.awt.*;
import java.awt.event.*;

public class EncDecApp extends Frame implements ActionListener, WindowListener, ItemListener{
    private TextField input;
    private static TextField salt;
    private static CheckboxGroup encdec;
    private TextArea result;
    private static Button execute;
    private static CheckboxGroup method;
    private static Checkbox encrypt;
    private static Checkbox decrypt;
    public EncDecApp() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        Label label1 = new Label("Enter String to Process");
        add(label1);
        input = new TextField(15);
        add(input);
        Label label2 = new Label("Enter salt to use");
        add(label2);
        salt = new TextField("jaas is the way");
        add(salt);
        method = new CheckboxGroup();
        Checkbox blowfish = new Checkbox("Blowfish", true, method);
        Checkbox sha256 = new Checkbox("Sha-256", false, method);
        blowfish.addItemListener(this);
        sha256.addItemListener(this);
        add(blowfish);
        add(sha256);
        encdec = new CheckboxGroup();
        encrypt = new Checkbox("Encrypt", true, encdec);
        decrypt = new Checkbox("Decrypt", false, encdec);
        encrypt.addItemListener(this);
        decrypt.addItemListener(this);
        add(encrypt);
        add(decrypt);
        execute = new Button(encdec.getSelectedCheckbox().getLabel());
        add(execute);
        execute.addActionListener(this);
        setTitle("Encrypt/Decrypt");
        result = new TextArea(1, 75);
        add(result);
        addWindowListener(this);
        setSize(550, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        EncDecApp app = new EncDecApp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selection = encdec.getSelectedCheckbox().getLabel();
        if(method.getSelectedCheckbox().getLabel().equalsIgnoreCase("blowfish")){
            if(selection.equalsIgnoreCase("encrypt")){
                Encrypt encrypt = new Encrypt();
                try {
                    String encrypted = encrypt.blowfish(input.getText(), salt.getText());
                    result.setText(encrypted);
                }
                catch (Exception exc){
                    exc.printStackTrace();
                    result.setText(exc.getMessage());
                }
            }
            if(selection.equalsIgnoreCase("decrypt")){
                Decrypt decrypt = new Decrypt();
                try{
                    String decrypted = decrypt.blowfish(input.getText(), salt.getText());
                    result.setText(decrypted);
                }
                catch(Exception exc){
                    exc.printStackTrace();
                    result.setText(exc.getMessage());
                }
            }
        }
        if(method.getSelectedCheckbox().getLabel().equalsIgnoreCase("sha-256")){
            Encrypt encrypt = new Encrypt();
            try{
                String encrypted = encrypt.sha256(input.getText());
                result.setText(encrypted);
            }
            catch(Exception exc){
                exc.printStackTrace();
                result.setText(exc.getMessage());
            }
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        String changed = e.getItem().toString();
        if(changed.equalsIgnoreCase("encrypt") || changed.equalsIgnoreCase("decrypt")){
            execute.setLabel(changed);
        }
        if(changed.equalsIgnoreCase("sha-256")){
            salt.setText("");
            salt.setEditable(false);
            encrypt.setState(true);
            decrypt.setEnabled(false);
            execute.setLabel(encdec.getSelectedCheckbox().getLabel());
        }
        if(changed.equalsIgnoreCase("blowfish")){
            salt.setText("jaas is the way");
            salt.setEditable(true);
            decrypt.setEnabled(true);
            execute.setLabel(encdec.getSelectedCheckbox().getLabel());
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);  // Terminate the program
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
