import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class OptionsVerificarMetodos {

    public static void main(String[] args) {
        String urlString = "https://apichallenges.eviltester.com/sim/entities";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("OPTIONS");

            int statusCode = connection.getResponseCode();
            System.out.println("OPTIONS - Código de status HTTP: " + statusCode);

            Map<String, List<String>> headers = connection.getHeaderFields();
            List<String> allowHeader = headers.get("Allow");

            if (allowHeader != null) {
                System.out.println("Métodos permitidos (Allow):");
                allowHeader.forEach(System.out::println);
            } else {
                System.out.println("Cabeçalho 'Allow' não encontrado.");
            }

            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao fazer requisição OPTIONS: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
