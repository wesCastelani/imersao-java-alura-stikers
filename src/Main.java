import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Criar uma conexão HTTP e buscar os top 250 filmes

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
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
        var geradora = new CriadorDeStikers();
        for (Map<String, String> filme: lsitaDeFilmes) {

            var urlImage = filme.get("image");
            var titulo = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            var nomeArquivo = titulo + ".png";


            geradora.criar(inputStream, nomeArquivo);

        }
    }
}