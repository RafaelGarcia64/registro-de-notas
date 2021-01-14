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
        int cualAlumno,
                confirmacion,
                cualPeriodo;

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

            menu = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de la opcion que desea\n1 - Ver record de notas\n2 - Agregar nuevo Estudiante\n"
                    + "3 - Modificar nota de usuario\n4 - Dar de baja a estudiante\nOtro numero para salir"));
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
                            buffer.write(notas[i][0] + "-" + notas[i][1] + "-" + notas[i][2] + "-" + notas[i][3] + "-" + notas[i][4]);
                            buffer.newLine();
                        }
                        buffer.close();
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;
                case 3:
                    double nuevaNota;
                    do {
                        cualAlumno = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de estudiante que quiere cambiar la nota"));
                    } while (cualAlumno > estudiantes || cualAlumno <= 0);
                    do {
                        cualPeriodo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero del periodo que quiere cambiar la nota"));
                    } while (cualPeriodo > 4 || cualPeriodo <= 0);
                    if ((Double.parseDouble(notas[cualAlumno - 1][cualPeriodo])) == 10.0) {
                        JOptionPane.showMessageDialog(null, "No se puede cambiar la nota por que el estudiante:\n" + notas[(cualAlumno - 1)][0] + " \ntiene 10.0 de calificacion");
                    } else {
                        do {
                            nuevaNota = Double.parseDouble(JOptionPane.showInputDialog("ingrese la nueva nota para el estudiante \n" + notas[(cualAlumno - 1)][0] + "\n"
                                    + "Recuerde que no puede disminuir su nota actual la cual es de: "
                                    + notas[cualAlumno - 1][cualPeriodo]));
                        } while (nuevaNota > 10.0 || nuevaNota < (Double.parseDouble(notas[cualAlumno - 1][cualPeriodo])));
                        confirmacion = Integer.parseInt(JOptionPane.showInputDialog("Seguro que desea cambiar la nota a: " + notas[(cualAlumno - 1)][0] + "\n1 - Confirmar\n"
                                + "Otro numero para rechazar"));
                        if (confirmacion == 1) {
                            notas[cualAlumno - 1][cualPeriodo] = nuevaNota + "";
                            try {
                                FileWriter filew = new FileWriter(archivo);
                                BufferedWriter buffer = new BufferedWriter(filew);
                                buffer.write("");
                                for (int i = 0; i < estudiantes; i++) {
                                    buffer.write(notas[i][0] + "-" + notas[i][1] + "-" + notas[i][2] + "-" + notas[i][3] + "-" + notas[i][4]);
                                    buffer.newLine();
                                }
                                buffer.close();
                            } catch (IOException ex) {
                                System.out.println("Error: " + ex.getMessage());
                            }
                        }
                    }

                    break;
                case 4:
                    do {
                        cualAlumno = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de estudiante que quiere dar de baja"));
                    } while (cualAlumno > estudiantes || cualAlumno <= 0);
                    confirmacion = Integer.parseInt(JOptionPane.showInputDialog("Seguro que desea dar de baja a: " + notas[(cualAlumno - 1)][0] + "\n1 - Confirmar\n"
                            + "Otro numero para rechazar"));
                    if (confirmacion == 1) {
                        for (int periodoN = 1; periodoN < 5; periodoN++) {
                            notas[(cualAlumno - 1)][periodoN] = 0.0 + "";
                        }
                        try {
                            FileWriter filew = new FileWriter(archivo);
                            BufferedWriter buffer = new BufferedWriter(filew);
                            buffer.write("");
                            for (int i = 0; i < estudiantes; i++) {
                                buffer.write(notas[i][0] + "-" + notas[i][1] + "-" + notas[i][2] + "-" + notas[i][3] + "-" + notas[i][4]);
                                buffer.newLine();
                            }
                            buffer.close();
                        } catch (IOException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                    }
                    break;
            }
        } while (menu >= 1 && menu <= 4);
    }

}
