package org.alura.views;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class prueba {

    public static void main(String[] args) {

        // Carga la imagen desde un archivo en el sistema de archivos
        File imagenArchivo = new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ProjHotelAlura\\src\\main\\java\\imagenes\\aH-40px.png");
        Image imagen = null;
        try {
            imagen = ImageIO.read(imagenArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crea un ImageIcon a partir de la imagen y lo muestra en un JLabel
        ImageIcon imagenIcono = new ImageIcon(imagen);
        JLabel imagenLabel = new JLabel(imagenIcono);

        // Crea una ventana JFrame y agrega el JLabel a ella
        JFrame ventana = new JFrame();
        ventana.getContentPane().add(imagenLabel, BorderLayout.CENTER);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(imagenIcono.getIconWidth(), imagenIcono.getIconHeight());
        ventana.setVisible(true);
    }
}
