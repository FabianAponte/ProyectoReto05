package view;

import controller.ControladorRequerimientosReto5;
import model.vo.LideresMayorSalario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.*;

/*Clase MiTabla1 donde defino un metodo contructor en el cual a través de la biblioteca swing se define un  objeto tabla1 que se pobla a través de una matriz informacion1 
la cual se pobla a través del método obtenerMatriz1 donde se recorre el arraylist rankingLideresMayorSalario entregado por el controlador, si se pobla de manera exitosa 
a través de un campo de texto definido en esta consulta se indica que la consulta fue exitosa, 
si no se captura el error que entrege el controlador y en el campo de texto se muestra dicho error */

public class MiTabla1 extends JPanel {

    public static final ControladorRequerimientosReto5 controlador = new ControladorRequerimientosReto5();
    private JTextField tf01=new JTextField(); 

    public void setText(JTextField tf01){
        this.tf01 = tf01;   
    }

    public MiTabla1() {
        setLayout(new BorderLayout());
        JLabel l1= new JLabel("1. Consulta de lideres con mayor salario");
        String titulos1[] = { "Id_Lider", "Nombre", "Primer_Apellido" };
        String informacion1[][] = obtenerMatriz1();
        JTable tabla1 = new JTable(informacion1, titulos1);
        JScrollPane panel1 = new JScrollPane(tabla1);
 
        add(l1,BorderLayout.NORTH);
        add(tf01,BorderLayout.CENTER);
        add(panel1, BorderLayout.SOUTH);
    }

    private String[][] obtenerMatriz1() {

        try {
            ArrayList<LideresMayorSalario> rankingLideresMayorSalario = controlador.consultarLideresMayorSalarios();
            String matrizInfo1[][] = new String[rankingLideresMayorSalario.size()][3];
            for (int i = 0; i < rankingLideresMayorSalario.size(); i++) {
                matrizInfo1[i][0] = rankingLideresMayorSalario.get(i).getIdLider() + "";
                matrizInfo1[i][1] = rankingLideresMayorSalario.get(i).getNombre() + "";
                matrizInfo1[i][2] = rankingLideresMayorSalario.get(i).getPrimerApellido() + "";
            }
            tf01.setText("Consulta No.1 exitosa");
            return matrizInfo1;
        } catch (SQLException e) {
            tf01.setText("Ha ocurrido un error!" + e.getMessage());
            return null;
        }
    }
}
