import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GetComParametros {

    public static void main(String[] args) {
        try {
            String baseUrl = "https://apichallenges.eviltester.com/sim/entities";
            String categoria = URLEncoder.encode("teste", StandardCharsets.UTF_8);
            String limite = URLEncoder.encode("5", StandardCharsets.UTF_8);
            String fullUrl = baseUrl + "?categoria=" + categoria + "&limite=" + limite;

            URL url = new URL(fullUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            System.out.println("URL Final Montada: " + fullUrl);
            System.out.println("Código de status HTTP: " + statusCode);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line).append("\n");
            }

            reader.close();
            System.out.println("Corpo da resposta:");
            System.out.println(responseBody.toString());

            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao fazer requisição: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
