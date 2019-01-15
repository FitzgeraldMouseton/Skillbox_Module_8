public class Person {

    private String surname;
    private String name;
    private String patronymic;
    public static final int OK = 0, NAME_OR_SURNAME_MISSING = 1, PATRONYMIC_MISSING = 2;

    public Person(){
    }

    public Person(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    private boolean isEmpty (String value){
        return (value == null || value.trim().isEmpty());
    }

    public int getStatus(){
        if (isEmpty(name) || isEmpty(surname))
            return NAME_OR_SURNAME_MISSING;
        else if (isEmpty(patronymic))
            return PATRONYMIC_MISSING;
        else {
            return OK;
        }
    }
}
