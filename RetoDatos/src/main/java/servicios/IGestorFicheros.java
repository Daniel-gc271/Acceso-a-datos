package servicios;

import modelo.Registro;
import java.util.List;

public interface IGestorFicheros {
    List<Registro> leer(String rutaArchivo) throws Exception;
    void escribir(String rutaArchivo, Registro nuevoRegistro) throws Exception;
}