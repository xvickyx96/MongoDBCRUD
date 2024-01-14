import org.bson.Document;
public class Person {
    private String namn;
    private int ålder;
    private String adress;

    public Person(String namn, int ålder, String adress) {
        this.namn = namn;
        this.ålder = ålder;
        this.adress = adress;
    }

    public String getNamn() {
        return namn;
    }

    public int getÅlder() {
        return ålder;
    }

    public String getAdress() {
        return adress;
    }

    @Override
    public String toString() {
        return namn + " är " + ålder + " år och bor på " + adress;
    }
}