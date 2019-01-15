import javax.swing.*;
import java.awt.event.ActionListener;

public class FullForm extends JPanel{
    private JPanel rootPanel;
    private JTextField surname;
    private JTextField name;
    private JTextField patronymic;
    private JButton switchButton;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = this;
    }

    public JTextField getSurname() {
        return surname;
    }

    public JTextField getPatronymic() {
        return patronymic;
    }

    public Person getPerson(){
        return new Person(surname.getText().trim(), name.getText().trim(), patronymic.getText().trim());
    }

    public void setPerson(Person person){
        surname.setText(person.getSurname());
        name.setText(person.getName());
        patronymic.setText(person.getPatronymic());
    }

    public void addSwitchListener (ActionListener listener){
        switchButton.addActionListener(listener);
    }

    public void addRemoveListener (ActionListener listener){
        switchButton.removeActionListener(listener);
    }

    public void setFocusOnPatronymic(){
        patronymic.requestFocusInWindow();
    }
}
