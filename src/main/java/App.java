import javax.swing.*;
import view.MiTabla1;
import view.MiTabla2;
import view.MiTabla3;
import java.awt.BorderLayout;


/***************     Solución Reto 5, Ciclo 2 --- Fabian Andrés Aponte Coronado,Grupo 60 ,Agosto 2021   ***************/

/*Aplicación donde a través de la biblioteca swing y del instanciamiento de las clases de vista se despliega la ventana 
donde se da respuesta a los requirimientos del reto 05 */

public class App {
   
    public static void main( String[] args ){ 
                
        JFrame frame = new JFrame("Solución Reto 5, Ciclo 2 --- Fabian Andrés Aponte Coronado,Grupo 60");
        
        MiTabla1 tabla1 =new MiTabla1();
        MiTabla2 tabla2 =new MiTabla2();
        MiTabla3 tabla3 =new MiTabla3();      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(tabla1, BorderLayout.WEST);
        frame.getContentPane().add(tabla2, BorderLayout.EAST);
        frame.getContentPane().add(tabla3, BorderLayout.SOUTH);     
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
