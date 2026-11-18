package servicios;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import modelo.Registro;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class GestorCSV implements IGestorFicheros {

    @Override
    public List<Registro> leer(String rutaArchivo) throws Exception {
        List<Registro> registros = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
            while ((linea = reader.readNext()) != null) {
                if (linea.length >= 6) {
                    registros.add(new Registro(linea[0], linea[1], linea[2], linea[3], linea[4], linea[5]));
                }
            }
        }
        return registros;
    }

    @Override
    public void escribir(String rutaArchivo, Registro reg) throws Exception {
        try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivo, true))) {
            String[] datos = {
                reg.getDato1(), reg.getDato2(), reg.getDato3(),
                reg.getDato4(), reg.getDato5(), reg.getDato6()
            };
            writer.writeNext(datos);
        }
    }
}