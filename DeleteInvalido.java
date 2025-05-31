import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteInvalido {

    public static void main(String[] args) {
        String urlString = "https://apichallenges.eviltester.com/sim/entities/2";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int statusCode = connection.getResponseCode();
            System.out.println("DELETE - Código de status HTTP: " + statusCode);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            statusCode >= 400 ? connection.getErrorStream() : connection.getInputStream()
                    )
            );

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
            reader.close();

            System.out.println("Resposta do servidor:");
            System.out.println(response.toString());

            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao fazer requisição DELETE: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
