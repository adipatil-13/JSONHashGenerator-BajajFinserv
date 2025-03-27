import org.json.JSONObject;
import java.io.*;

import java.security.MessageDigest;

public class HashGen {

    public static void main(String[] args) {
        try {
            String jsonContent = read();
            // Parse JSON
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Extract student detail
            String firstName = jsonObject.getJSONObject("student")
                    .getString("first_name")
                    .toLowerCase();
            String roll = jsonObject.getJSONObject("student")
                    .getString("rollNo")
                    .toLowerCase();

            String combined = firstName + roll;

            // Generate MD5 hash
            String md5Hash = calculateMD5(combined);
            // output file
            writeToFile(md5Hash);
            // Confirmation
            System.out.println("Hash generated");

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to read file sb
    private static String read() throws IOException {
        try (FileReader reader=new FileReader("input.json");
             BufferedReader bufferedReader=new BufferedReader(reader)) {


            StringBuilder sb = new StringBuilder();
            String l;
            while((l = bufferedReader.readLine())!=null) {
                sb.append(l);
            }
            return sb.toString();
        }
    }

    // Generate MD5 hash
    private static String calculateMD5(String input) throws Exception {
        // Message digest created
        MessageDigest md=MessageDigest.getInstance("MD5");
        // Input to bytes
        byte[] hashBytes = md.digest(input.getBytes());

        // bYTES to readable string
        StringBuilder hexHash = new StringBuilder();
        for(byte b:hashBytes) {
            // Format byte as 2-digit 
            hexHash.append(String.format("%02x",b));
        }

        return hexHash.toString();
    }

    private static void writeToFile(String sb) throws IOException {

        try(FileWriter writer=new FileWriter("output.txt")) {
            writer.write(sb);
        }
    }
}