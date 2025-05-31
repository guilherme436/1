
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetEntidadesSimples {

    public static void main(String[] args) {
        String urlString = "https://apichallenges.eviltester.com/sim/entities";

        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
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
