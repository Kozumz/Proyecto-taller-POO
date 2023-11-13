package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

class MiniaturasCompra {
    Image Miniatura;
    JPanel MainPanel;
    JLabel Descripcion;
    int Cant;
    public static JPanel NuevaMiniatura (String path, int x, int y, int xs, int ys){
        try {
            JPanel Fondo = new JPanel();
            Fondo.setBounds(x,y,xs,ys);
            BufferedImage AnticongelantePNG = ImageIO.read(new File(path));
            // Crear un JLabel para mostrar la imagen
            JLabel AnticongelanteIMG = new JLabel(new ImageIcon(AnticongelantePNG));
            AnticongelanteIMG.setBounds(x,y,xs,ys);
            Fondo.add(AnticongelanteIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(x,y,xs,ys);
            FondoAnticongelante.setBackground(Color.decode("#E0E0E0"));
            Fondo.add(FondoAnticongelante);
            return Fondo;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JPanel NuevaMiniaturaRc (String path, int x, int y,int xRc,int yRc, int xs, int ys){
        try {
            JPanel Fondo = new JPanel();
            Fondo.setBounds(x,y,xs,ys);
            // Cargar la imagen desde un archivo
            BufferedImage GelPNG = ImageIO.read(new File(path));
            //Escalar imagen
            Image GelRC = GelPNG.getScaledInstance(xRc,yRc,Image.SCALE_SMOOTH);

            // Crear un JLabel para mostrar la imagen
            JLabel GelIMG = new JLabel(new ImageIcon(GelRC));
            GelIMG.setBounds(x,y,xs,ys);
            Fondo.add(GelIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(x,y,xs,ys);
            FondoAnticongelante.setBackground(Color.decode("#E0E0E0"));
            Fondo.add(FondoAnticongelante);
            return Fondo;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JLabel Etiqueta (String Texto, int x, int y){
        JLabel Etiqueta = new JLabel(Texto);

        return Etiqueta;
    }
}
