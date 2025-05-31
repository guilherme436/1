import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostCriarEntidade {

    public static void main(String[] args) {
        String urlString = "https://apichallenges.eviltester.com/sim/entities";
        String jsonInput = "{\"name\": \"aluno\"}";

        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(jsonInput);
                outputStream.flush();
            }

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
            System.out.println("Erro ao fazer requisição POST: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
