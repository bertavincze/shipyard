package com.shipyard;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger implements Serializable {

    private FileHandler fileHandler = new FileHandler();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd. HH:mm");

    public Logger() {
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String tsFormatted = sdf.format(ts);
        String header = "";
        header += "[" + "NEW LOG - " + tsFormatted + "]";
        if (fileHandler.readLine("log.txt", fileHandler.countLines("log.txt") - 1) != null) {
            fileHandler.append("log.txt", "\n" + header + "\n");
        } else {
            fileHandler.append("log.txt", header + "\n");
        }
    }

    public void logMessage(String message) {
        fileHandler.append("log.txt", message);

    }

    public void log(String message) {
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String tsFormatted = sdf.format(ts);
        String text = "";
        text += tsFormatted + " " + message;
        fileHandler.append("log.txt", text);

    }

    public void save(Docks docks) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("serialization.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(docks);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in serialization.ser");
            log("Serialized data is saved in serialization.ser");
        } catch (IOException e) {
            log("Could not save serialized data due to " + e.getMessage());
            System.out.println("Could not save serialized data due to " + e.getMessage());
        }
    }

    public Docks load() {
        Docks docks = null;
        try {
            FileInputStream fileIn = new FileInputStream("serialization.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            docks = (Docks)in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            log("Could not load serialized data due to IOException: " + e.getMessage());
            System.out.println("Could not load serialized data due to IOException: " + e.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("Requested class not found: " + c.getMessage());
            log("Requested class not found: " + c.getMessage());

        }
        return docks;
    }
}
