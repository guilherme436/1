import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteEntidade {

    public static void main(String[] args) {
        String urlString = "https://apichallenges.eviltester.com/sim/entities/9";

        try {
            URL url = new URL(urlString);
            HttpURLConnection deleteConnection = (HttpURLConnection) url.openConnection();
            deleteConnection.setRequestMethod("DELETE");

            int deleteStatus = deleteConnection.getResponseCode();
            System.out.println("DELETE - Código de status HTTP: " + deleteStatus);

            if (deleteStatus == 200 || deleteStatus == 204) {
                System.out.println("Entidade 9 deletada com sucesso.");
            } else {
                System.out.println("Erro ao deletar entidade.");
            }

            deleteConnection.disconnect();

            System.out.println("\n--- Verificando com GET se a entidade foi deletada ---");
            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
            getConnection.setRequestMethod("GET");

            int getStatus = getConnection.getResponseCode();
            System.out.println("GET - Código de status HTTP: " + getStatus);

            if (getStatus == 404) {
                System.out.println("Confirmação: Entidade 9 não existe mais (404 Not Found).");
            } else {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(getConnection.getInputStream())
                );
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line).append("\n");
                }
                reader.close();

                System.out.println("Resposta do GET:");
                System.out.println(response.toString());
            }

            getConnection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao fazer requisição: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
