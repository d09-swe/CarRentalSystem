package service;

import java.io.*;

public class FileService {

    public static void saveObject(Object obj, String fileName) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object loadObject(String fileName) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(fileName))) {
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}
