package view;

import controller.ControladorRequerimientosReto5;
import model.vo.LideresProyectosEmblematicos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.*;


/*Clase MiTabla2 donde defino un metodo contructor en el cual a través de la biblioteca swing se define un objeto tabla2 que se pobla a través de una matriz informacion2 
que se pobla a través del método obtenerMatriz2 donde se recorre el arraylist rankingProyectosEmblematicos entregado por el controlador, si se pobla de manera exitosa 
a través de un campo de texto definido en esta consulta se indica que la consulta fue exitosa, 
si no se captura el error que entrege el controlador y en el campo de texto se muestra dicho error */


public class MiTabla2 extends JPanel {

    public static final ControladorRequerimientosReto5 controlador = new ControladorRequerimientosReto5();
    private JTextField tf02=new JTextField();
    
    public void setText(JTextField tf02){
        this.tf02 = tf02;   
    }

    public MiTabla2() {
        setLayout(new BorderLayout());
        JLabel l2= new JLabel("2. Consulta de ranking de proyectos emblemáticos");
        String titulos2[] = { "Id_Lider", "Id_Proyecto", "Id_Tipo" };
        String informacion2[][] = obtenerMatriz2();
        JTable tabla2 = new JTable(informacion2, titulos2);
        JScrollPane panel2 = new JScrollPane(tabla2);
        
        add(l2,BorderLayout.NORTH);
        add(tf02,BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
    }

    private String[][] obtenerMatriz2() {

        try {
            ArrayList<LideresProyectosEmblematicos> rankingProyectosEmblematicos = controlador.consultarLideresProyectosEmblematicos();
            String matrizInfo2[][] = new String[rankingProyectosEmblematicos.size()][3];
            for (int i = 0; i < rankingProyectosEmblematicos.size(); i++) {
                matrizInfo2[i][0] = rankingProyectosEmblematicos.get(i).getIdLider() + "";
                matrizInfo2[i][1] = rankingProyectosEmblematicos.get(i).getIdProyecto() + "";
                matrizInfo2[i][2] = rankingProyectosEmblematicos.get(i).getIdTipo() + "";
            }
            tf02.setText("Consulta No.2 exitosa");
            return matrizInfo2;
        } catch (SQLException e) {
            tf02.setText("Ha ocurrido un error!" + e.getMessage());
            return null;
        }
    }
}
