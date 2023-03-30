import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CriadorDeStikers {

        public void criar(InputStream inputStream, String nome) throws IOException {

                //Efetuar a leitura da imagem
                //var inputStream = new FileInputStream(new File("Entrada/TopMovies_1.jpg"));

                //var inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg");

                var imagemOriginal = ImageIO.read(inputStream);

                //Cria uma nova imagem com o efeito de transparencia
                int alutra = imagemOriginal.getHeight();
                int largura = imagemOriginal.getWidth();

                int novaAltura = alutra + 200;

                var novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

                //Copiar a imagem original para nova imagem
                var graphics = (Graphics2D) novaImagem.getGraphics();

                graphics.drawImage(imagemOriginal, 0, 0, null);

                //Configurar fonte
                Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
                graphics.setColor(Color.CYAN);
                graphics.setFont(fonte);

                //Escrever um texto na nova imagem
                graphics.drawString("Brabo", 0, novaAltura-100);

                //escrever a nova imagem no arquivo
                ImageIO.write(novaImagem, "png", new File("Saida/" + nome));
        }

}
