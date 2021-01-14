package registro.de.notas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class RegistroDeNotas {

    public static void main(String[] args) {
        int menu, estudiantes = 0;
        String path;
        String notas[][] = new String[100][5];
        JTextArea pantalla = new JTextArea();

        path = JOptionPane.showInputDialog("Ingrese la ruta de su archivo de notas\nRecuende que las plcas inversas deben ser escritas de esta manera \\\\");
        File archivo = new File(path);
        do {
            try {
                estudiantes = 0;
                String linea;
                FileReader filer = new FileReader(archivo);
                BufferedReader buffer = new BufferedReader(filer);
                while ((linea = buffer.readLine()) != null) {
                    String vectort[] = linea.split("-");
                    for (int periodo = 0; periodo < vectort.length; periodo++) {
                        notas[estudiantes][periodo] = vectort[periodo];
                    }
                    estudiantes++;
                }
                buffer.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            menu = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de la opcion que desea\n1 - Ver record de notas\n2 - Agregar nuevo Estudiante\n3 - Modificar nota de usuario\n4 - Dar de baja a periodo"));
            switch (menu) {
                case 1:
                    if (estudiantes > 0) {
                        String pantallaTexto = "# Alumno\tAlumno\tperiodo 1\tperiodo 2\tperiodo 3\tperiodo 4\n";
                        for (int Filas = 0; Filas < estudiantes; Filas++) {
                            pantallaTexto += (Filas + 1) + "\t";
                            for (int Columnas = 0; Columnas < 5; Columnas++) {
                                pantallaTexto += notas[Filas][Columnas] + "\t";
                            }
                            pantallaTexto += "\n";
                        }
                        pantalla.setText(pantallaTexto);
                        JOptionPane.showMessageDialog(null, pantalla);
                    } else {
                        JOptionPane.showMessageDialog(null, "No esxisten record de estudiantes");
                    }
                    break;
                case 2:
                    String nombreEstudiante;
                    do {
                        nombreEstudiante = JOptionPane.showInputDialog("Ingrese el nombre del nuevo estudiante");
                    } while (nombreEstudiante.isEmpty());
                    notas[estudiantes][0] = nombreEstudiante;
                    for (int periodoN = 1; periodoN < 5; periodoN++) {
                        notas[estudiantes][periodoN] = 0.0 + "";
                    }
                    estudiantes++;
                    try {
                        FileWriter filew = new FileWriter(archivo);
                        BufferedWriter buffer = new BufferedWriter(filew);
                        buffer.write("");
                        for (int i = 0; i < estudiantes; i++) {
                            buffer.write(notas[i][0] + "-" + notas[i][1] + "-" + notas[i][2] + "-" + notas[i][3] + "-" + notas[i][4] + "-" + notas[i][5]);
                            buffer.newLine();
                        }
                        buffer.close();
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;
                case 3:

                    break;
                default:

                    break;
            }
        } while (menu >= 1 && menu <= 3);
    }

}
