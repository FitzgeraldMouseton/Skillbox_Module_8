import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;

public class SimpleForm extends JPanel{
    private JPanel rootPanel;
    private JButton switchButton;
    private JTextField fio;
    private JProgressBar progressBar;

    {
        progressBar.setMaximum(100);
        fio.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                showFioLength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                showFioLength();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
    }

    public JTextField getFio() {
        return fio;
    }

    public void setPerson (Person person){
        fio.setText(person.getSurname().trim() + " " + person.getName().trim() + " " + person.getPatronymic().trim());
    }

    public Person getPerson(){
        String[] initials = fio.getText().trim().split("\\s+");
        switch (initials.length) {
            case 0:
                return new Person("", "", "");
            case 1:
                return new Person(initials[0], "","");
            case 2:
                return new Person(initials[0], initials[1], "");
            case 3:
                return new Person(initials[0], initials[1], initials[2]);
        }
        return null;
    }

    public void addSwitchListener (ActionListener listener){
        switchButton.addActionListener(listener);
    }

    public void removeSwitchListener (ActionListener listener){
        switchButton.removeActionListener(listener);
    }

    private void showFioLength(){
        progressBar.setValue(fio.getText().length());
    }

    public void setFocusOnFio(){
        fio.requestFocusInWindow();
    }
}
