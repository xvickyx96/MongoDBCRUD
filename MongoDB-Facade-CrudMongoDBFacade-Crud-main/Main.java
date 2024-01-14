import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;


public class Main {
    public static void main(String[] args) {

        System.out.println("Hello MongoDB!");
        Logger.getLogger("org.mongodb.driver")
                .setLevel(Level.SEVERE);

        Menu obj = new Menu();
        obj.menyStart();

    }
}