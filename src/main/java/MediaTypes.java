import java.util.ArrayList;
import java.util.List;

public class MediaTypes {

    private static final List<String> mediaTypesRequeridos = new ArrayList<String>();

    static {
        mediaTypesRequeridos.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        mediaTypesRequeridos.add("application/vnd.ms-excel");
        mediaTypesRequeridos.add("application/excel");
        mediaTypesRequeridos.add("application/vnd.oasis.opendocument.formula");
        mediaTypesRequeridos.add("application/vnd.oasis.opendocument.spreadsheet");
    }

    public static boolean isMediaTypeRequerido(String mediaType) {
        return mediaTypesRequeridos.contains(mediaType);
    }

}
