import org.json.JSONObject;
import java.io.*;

import java.security.MessageDigest;

public class HashGen{
    private static void writeToFile(String sb)throws IOException{

        try(FileWriter writer=new FileWriter("output.txt")){
            writer.write(sb);
        }
    }
    public static void main(String[] args){
        try {
            String jsonContent = read();
            // Parse JSON
            JSONObject jsonObj = new JSONObject(jsonContent);

            // Extract student detail
            String firstName = jsonObj.getJSONObject("student").getString("first_name")
                    .toLowerCase();
            String roll = jsonObj.getJSONObject("student").getString("rollNo")
                    .toLowerCase();

            String combined = firstName + roll;
            // Generate MD5 hash
            String md5Hash = calculateMD5(combined);
            // outpuT file
            writeToFile(md5Hash);
            // cOnfirmation
            System.out.print("Hash generated! Yayyy!");
        } catch (Exception e) {

            System.out.print("Error: "+e.getMessage());
        }
    }

    // Method to read file sb
    private static String read() throws IOException{
        try (FileReader reader=new FileReader("input.json");
             BufferedReader br =new BufferedReader(reader)) {
            String l;

            StringBuilder sb = new StringBuilder();

            while((l = br.readLine())!=null) sb.append(l);
            return sb.toString();
        }
    }

    // Generate MD5 hash
    private static String calculateMD5(String inp) throws Exception{
        // Message digest created
        MessageDigest md=MessageDigest.getInstance("MD5");
        // Input to bytes
        byte[] hashBytes = md.digest(inp.getBytes());

        // bYTES to readable string
        StringBuilder hex = new StringBuilder();


        for(byte b: hashBytes)
            hex.append(String.format("%02x",b));
        return hex.toString();
    }

}
