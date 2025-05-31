
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetEntidadeEspecifica {

    public static void main(String[] args) {
        // IDs de 1 a 8 para teste
        for (int id = 1; id <= 8; id++) {
            String urlString = "https://apichallenges.eviltester.com/sim/entities/" + id;

            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int statusCode = connection.getResponseCode();
                System.out.println("Entidade ID: " + id);
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
                System.out.println("========================================");

                connection.disconnect();

            } catch (IOException e) {
                System.out.println("Erro ao fazer requisição para ID " + id + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
