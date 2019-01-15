import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame {

    private FullForm fullForm = new FullForm();
    private SimpleForm simpleForm = new SimpleForm();

    {
        setContentPane(fullForm);
        setMinimumSize(new Dimension(400, 200));
        fullForm.addSwitchListener(e -> switchToSimple());
        simpleForm.addSwitchListener(e -> switchToFull());

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK),
                "switchForms"
        );

        getRootPane().getActionMap().put("switchForms", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchForms();
            }
        });
    }

    void switchToSimple(){
        Person person = fullForm.getPerson();
        if (canSwitch(person)) {
            setContentPane(simpleForm);
            revalidate();
            repaint();
            simpleForm.setPerson(person);
            simpleForm.setFocusOnFio();
        }
    }

    void switchToFull(){
        Person person = simpleForm.getPerson();
        if (canSwitch(person)){
            setContentPane(fullForm);
            revalidate();
            repaint();
            fullForm.setPerson(person);
            fullForm.setFocusOnPatronymic();
        }
    }

    void switchForms(){
        if (getContentPane() == fullForm)
            switchToSimple();
        else
            switchToFull();
    }

    public boolean canSwitch(Person person){
        if (person == null){
            JOptionPane.showOptionDialog(this,
                    "Данные введены неверно",
                    "Ошибка",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null, null, null);
            return false;
        } else if (person.getStatus() == Person.OK) {
            return true;
        } else if (person.getStatus() == Person.NAME_OR_SURNAME_MISSING){
            JOptionPane.showOptionDialog(this,
                    "Введите имя и фамилию",
                    "Warning",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null, null, null);
            return false;
        } else if (person.getStatus() == Person.PATRONYMIC_MISSING){
            int option = JOptionPane.showConfirmDialog(this,
                    "Продолжить без ввода отчества?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION){
                return true;
            } else {
                fullForm.getPatronymic().requestFocusInWindow();
            }
        }
        return false;
    }
}
