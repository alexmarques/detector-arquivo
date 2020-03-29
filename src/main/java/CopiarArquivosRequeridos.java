import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopiarArquivosRequeridos {

    private static final Tika tika = new Tika();
    private static final String COPIAR_ARQUIVOS_PARA = "/home/alex/Área de Trabalho/copia_backup";
    private static int encontrados = 0;
    private static int copiados = 0;
    private static int analisados = 0;
    private static int paraSeremCopiados = 0;
    private static List<String> arquivosEncontrados = new ArrayList<>();
    private static List<String> arquivosParaCopiar = new ArrayList<>();
    private static File arquivosjaCopiados = new File("arquivos_copiados.txt");

    public static void main(String[] args) throws IOException {

        File file = new File("/home/alex/Área de Trabalho/backup");

        System.out.println("Contando quantidade total de arquivos.");
        contarArquivos(file.listFiles());
        System.out.println("\nContagem total de arquivos finalizada.");

        System.out.println("Contando quantidade de arquivos.");
        quantidadeDeArquivos();
        System.out.println("\nContagem de arquivos finalizada. Quantidade de arquivos encontrados.");

        System.out.println("Copiando arquivos com media type requerido.");
        copiarMediaTypesRequeridos();
        System.out.println("\nCopia de arquivos com media types requeridos finalizada.");

        System.out.println("Gravando arquivo com media types requeridos em txt.");
        gravarArquivosCopiadosEmArquivo();
        System.out.println("Gravação de arquivo com media types requeridos finalizada.");

    }

    private static void contarArquivos(File[] files) {
        if(files == null || files.length == 0) {
            return;
        }
        Arrays.asList(files).parallelStream()
            .forEach(file -> {
                if(file.isDirectory()) {
                    contarArquivos(file.listFiles());
                } else {
                    arquivosEncontrados.add(file.getAbsolutePath());
                    System.out.print("\rTotal de arquivos: " + ++encontrados + ".");
                }
            });
    }

    private static void quantidadeDeArquivos() throws IOException {
        arquivosEncontrados.stream()
            .forEach(path -> {
                if(path != null || !"".equals(path.trim())) {
                    File file = new File(path);
                    String mediaType = tika.detect(path);
                    try {
                        String content = tika.parseToString(file);
                        if (content.contains("Saida") || content.contains("Entrada") || content.contains("Saída")) {
                            if (MediaTypes.isMediaTypeRequerido(mediaType)) {
                                arquivosParaCopiar.add(path);
                                paraSeremCopiados++;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TikaException e) {
                        e.printStackTrace();
                    }
                    System.out.print("\rArquivos encontrados: " + paraSeremCopiados + ". Analisados: " + ++analisados + " de " + encontrados + ".");
                }
            });
    }

    private static void copiarMediaTypesRequeridos() throws IOException {
        arquivosParaCopiar.stream()
            .forEach(path -> {
                File from = new File(path);
                File to = new File(COPIAR_ARQUIVOS_PARA + "/" + from.getName());
                System.out.print("\rCopiando arquivo " + ++copiados + " de " + paraSeremCopiados);
                try {
                    FileUtils.copyFile(from, to);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }

    private static void gravarArquivosCopiadosEmArquivo() {
        arquivosParaCopiar.stream()
            .forEach(path -> {
                try {
                    FileUtils.write(arquivosjaCopiados, path, "UTF-8", true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }
}
