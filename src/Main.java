import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Criar uma conexão HTTP e buscar os top 250 filmes

        String url = "url da api do imbd, estava off quando eu criei isso rs";
        URI endereco = URI.create(url);

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        var body = response.body();
        System.out.println(body);

        //Capturar somente os dados que interessam (Titulo, poster e rank)
        var jsonParser = new JsonParser();
        List<Map<String, String>> lsitaDeFilmes = jsonParser.parse(body);

        // Exibir e manipular os dados

        for (Map<String, String> filme: lsitaDeFilmes) {
            System.out.println(filme.get("title"));
        }
    }
}