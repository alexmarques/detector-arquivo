import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Detector {

    //private static int quantidadeDeArquivosEncontrados = 1403806;
    private static int quantidadeDeArquivosEncontrados = 0;
    private static int quantidadeDeArquivosExaminados = 0;
    private static Tika tika = new Tika();
    private static Set<String> mediaTypesEncontrados = new HashSet<String>();


    public static void main(String[] args) throws IOException {
        File file = new File("/home/alex/backup");
        quantidadeDeArquivos(file.listFiles());
        System.out.println("Quantidade de arquivos encontrados: " + quantidadeDeArquivosEncontrados);
        detectarMediaTypes(file.listFiles());
        printMediaTypesEncontrados();
    }

    private static void quantidadeDeArquivos(File[] files) {
        for(File file : files) {
            if(file.isDirectory()) {
                quantidadeDeArquivos(file.listFiles());
            } else {
                quantidadeDeArquivosEncontrados++;
            }
        }
    }

    private static void detectarMediaTypes(File[] files) throws IOException {
        for(File file : files) {
            if(file.isDirectory()) {
                detectarMediaTypes(file.listFiles());
            } else {
                quantidadeDeArquivosExaminados++;
                System.out.print("\rProcessando arquivo " + quantidadeDeArquivosExaminados +  " de " + quantidadeDeArquivosEncontrados);
                String mediaType = tika.detect(file);
                mediaTypesEncontrados.add(mediaType);
            }
        }
    }

    private static void printMediaTypesEncontrados() {
        for(String mediaType : mediaTypesEncontrados) {
            System.out.println(mediaType);
        }
    }
}
