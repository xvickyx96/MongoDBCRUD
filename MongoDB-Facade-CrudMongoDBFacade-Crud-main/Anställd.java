import org.bson.Document;

public class Anställd extends Person {
    private String anställningsnummer;

    public Anställd(String namn, int ålder, String adress, String anställningsnummer) {
        super(namn, ålder, adress);
        this.anställningsnummer = anställningsnummer;
    }

    public String getAnställningsnummer() {
        return anställningsnummer;
    }

    @Override
    public String toString() { return getNamn()+" är "+getÅlder()+" är och bor på "+getAdress()+" med anställningsnummer "+anställningsnummer;}

    public static Anställd fromDoc(Document doc)
    {
        if (doc==null) { // Om Document-objektet är null
            return new Anställd("",0,"", "");
        }

        return new Anställd(
                doc.getString("namn"),
                doc.getInteger("ålder"),
                doc.getString("adress"),
                doc.getString("anställningsnummer")
        );
    }


    public Document toDoc(){
        return new Document("namn", getNamn())
                .append("ålder", getÅlder())
                .append("adress", getAdress())
                .append("anställningsnummer", anställningsnummer);
    }
}


