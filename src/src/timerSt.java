import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class timerSt {
   public static void save(Serializable arr, String name) throws Exception {
        try (
            ObjectOutputStream oos=new ObjectOutputStream(Files.newOutputStream(Paths.get(name)))){
                oos.writeObject(arr);
            }
   }

   public static Object load (String name) throws Exception {
       try (
               ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(name)))) {
           return ois.readObject();
       } }
    }
