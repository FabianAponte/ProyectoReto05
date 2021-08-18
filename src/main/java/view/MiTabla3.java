package view;

import controller.ControladorRequerimientosReto5;
import model.vo.MaterialRankeadoImportado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.*;

/*Clase MiTabla3 donde defino un metodo contructor en el cual a través de la biblioteca swing se define un objeto tabla3 que se pobla a través de una matriz informacion3 
que se pobla a través del método obtenerMatriz3 donde se recorre el arraylist rankingMaterialesImportados entregado por el controlador, si se pobla de manera exitosa 
a través de un campo de texto definido en esta consulta se indica que la consulta fue exitosa, 
si no se captura el error que entrege el controlador y en el campo de texto se muestra dicho error */

public class MiTabla3 extends JPanel {

    public static final ControladorRequerimientosReto5 controlador = new ControladorRequerimientosReto5();
    private JTextField tf03=new JTextField();
    public void setText(JTextField tf03){
        this.tf03 = tf03;   
    }


    public MiTabla3() {
        setLayout(new BorderLayout());
        JLabel l3= new JLabel("3. Consulta de ranking de materiales importados");
        String titulos3[] = { "Identificación, descripción y precio de producto importado"};
        String informacion3[][] = obtenerMatriz3();
        JTable tabla3 = new JTable(informacion3, titulos3);
        JScrollPane panel3 = new JScrollPane(tabla3);
        add(l3,BorderLayout.NORTH);
        add(tf03,BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);
    }

    private String[][] obtenerMatriz3() {

        try {
            ArrayList<MaterialRankeadoImportado> rankingMaterialesImportados = controlador.consultarMaterialesRankeadosImportados();
            String matrizInfo3[][] = new String[rankingMaterialesImportados.size()][1];
            for (int i = 0; i < rankingMaterialesImportados.size(); i++) {

                matrizInfo3[i][0] = "El producto de ID "+rankingMaterialesImportados.get(i).getIdMaterial() + " de descripción: " +
                rankingMaterialesImportados.get(i).getNombreMaterial() + " de tipo importado(a), tiene un precio de "+
                rankingMaterialesImportados.get(i).getPrecioUnidad();                                 
            }
            tf03.setText("Consulta No. 3 exitosa");
            return matrizInfo3;
        } catch (SQLException e) {
            tf03.setText("Ha ocurrido un error!" + e.getMessage());
            return null;
        }
    }
}



