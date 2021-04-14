package pl.nethos.rekrutacja.collectData;


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APICaller {

    public Example callAPI(String nip, String numer) throws IOException {

        URL urlForGetRequest = new URL("https://wl-api.mf.gov.pl/api/check/nip/" + nip + "/bank-account/"
                + numer);
        String readLine;
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        String jsonString = "";

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            jsonString = response.toString();
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }

        // String jsonString = "{\"result\":{\"accountAssigned\":\"NIE\",\"requestDateTime\":\"14-04-2021 12:33:08\",\"requestId\":\"gc665-8b5nbmk\"}}";



        Gson gson = new Gson();
        return gson.fromJson(jsonString, Example.class);
    }
}
