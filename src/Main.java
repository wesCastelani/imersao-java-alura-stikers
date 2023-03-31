import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Criar uma conexão HTTP e buscar os top 250 filmes

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";

        var http = new ClienteHttp();
        var body = http.buscaDados(url);



        //Capturar somente os dados que interessam (Titulo, poster e rank)
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        List<Conteudo> conteudos = extrator.extraiConteudos(body);

        // Exibir e manipular os dados
        var geradora = new CriadorDeStikers();
        for (int i = 0; i < 3; i++){

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            var nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.criar(inputStream, nomeArquivo);

        }
    }
}