/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class OpenALPR {
    
    static String secret_key = "sk_48a30dc6ad3d28c634e1c05e";
    
    public String rutaImagen = "C:\\Users\\Daniel\\Documents\\NetBeansProjects\\JR\\capturaImagen.jpg";
    
    
    public String placa = "";
    public String tipoAuto = "";
    public String marcaAuto = "";
    public String colorAuto = "";
    public String anioAuto = "";
    
    
    
    public boolean realizarConsulta()
    {
        try
        {

            // Read image file to byte array
            Path path = Paths.get(rutaImagen);
            byte[] data = Files.readAllBytes(path);

            // Encode file bytes to base64
            byte[] encoded = Base64.getEncoder().encode(data);

            // Setup the HTTPS connection to api.openalpr.com
            URL url = new URL("https://api.openalpr.com/v2/recognize_bytes?recognize_vehicle=1&country=us&secret_key=" + secret_key);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setFixedLengthStreamingMode(encoded.length);
            http.setDoOutput(true);

            // Send our Base64 content over the stream
            try(OutputStream os = http.getOutputStream()) {
                os.write(encoded);
            }

            int status_code = http.getResponseCode();
            if (status_code == 200)
            {
                // Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(
                                        http.getInputStream()));
                
                String json_content = "";
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    json_content += inputLine;
                    
                in.close();

                try{
                    JSONObject jsonObject = new JSONObject(json_content);
                    
                    System.out.println(json_content);
                    
                    this.placa = jsonObject.getJSONArray("results").getJSONObject(0).getJSONArray("candidates").getJSONObject(0).getString("plate"); // Gets first item in array, which is the data (because Java is dumb)
                    this.tipoAuto = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("vehicle").getJSONArray("body_type").getJSONObject(0).getString("name");
                    this.marcaAuto = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("vehicle").getJSONArray("make").getJSONObject(0).getString("name");
                    this.colorAuto = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("vehicle").getJSONArray("color").getJSONObject(0).getString("name");
                    this.anioAuto = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("vehicle").getJSONArray("year").getJSONObject(0).getString("name");
                    
            
                    System.out.println(placa);
                    System.out.println(tipoAuto);
                    System.out.println(marcaAuto);
                    System.out.println(colorAuto);
                    System.out.println(anioAuto);
                    
                    return true;
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
                  
            }
            else
            {
                System.out.println("Got non-200 response: " + status_code);
                return false;
            }


        }
        catch (MalformedURLException e)
        {
            System.out.println("Bad URL");
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
            System.out.println("Failed to open connection");
        }
        
        return false;

    }
}
