package shop.jsf.controller;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import static shop.jsf.util.JsonReader.readJsonFromUrl;

@ManagedBean
@SessionScoped
public class CardController implements Serializable {

    public boolean makePayment(String number, String pin, BigDecimal total) {
        try {
            String jsonString = readJsonFromUrl(
                    "http://api-pawzar.rhcloud.com/rest/authorize?number="
                            + number + "&pin=" + pin);
            // System.out.println(jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
            double balance = jsonObject.getDouble("amount");

            if (balance >= total.doubleValue()) {
                URL url = new URL("http://api-pawzar.rhcloud.com/rest/accounts");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type", "application/json");

                jsonObject.put("amount", balance - total.doubleValue());
                String input = jsonObject.toString();

                OutputStream os = conn.getOutputStream();
                os.write(input.getBytes());
                os.flush();

                conn.getResponseCode();
                conn.disconnect();
                return true;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
