import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PutAtualizarEntidade {

    public static void main(String[] args) {
        String putUrl = "https://apichallenges.eviltester.com/sim/entities/10";
        String jsonInput = "{\"name\": \"atualizado\"}";

        try {

            URL url = new URL(putUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(jsonInput);
                outputStream.flush();
            }

            int statusCode = connection.getResponseCode();
            System.out.println("PUT - Código de status HTTP: " + statusCode);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line).append("\n");
            }
            reader.close();
            System.out.println("Resposta do PUT:");
            System.out.println(responseBody.toString());

            connection.disconnect();

            System.out.println("\n--- Verificando atualização com GET ---");
            URL getUrl = new URL(putUrl);
            HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();
            getConnection.setRequestMethod("GET");

            int getStatus = getConnection.getResponseCode();
            System.out.println("GET - Código de status HTTP: " + getStatus);

            BufferedReader getReader = new BufferedReader(
                    new InputStreamReader(getConnection.getInputStream())
            );
            StringBuilder getResponse = new StringBuilder();
            while ((line = getReader.readLine()) != null) {
                getResponse.append(line).append("\n");
            }
            getReader.close();

            System.out.println("Resposta do GET:");
            System.out.println(getResponse.toString());

            getConnection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao fazer requisição PUT: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
