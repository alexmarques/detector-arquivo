import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CopiarArquivosPorTamanho {

    private static final String desktop = "/home/alex/Área de Trabalho";
    private static final String pasta = "arquivos_pequenos";
    private static final String origem = "/home/alex/Área de Trabalho/copia_backup";
    private static final long tamanhoEsperado = 10000l;

    public static void main(String[] args) throws IOException {
        File file = new File(origem);
        for(File f : file.listFiles()) {
            long length = f.length();
            if(length > 20000l && length <= 25000l) {
                FileUtils.copyFile(f, new File(desktop + "/" + pasta + "/" + f.getName()));
            }
        }
    }
}
