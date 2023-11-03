package src;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import  javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.image.BufferedImage;
import java.io.File;

public class  Pestaña {
    public JPanel panel;
    Font Impact = new Font("Impact", Font.PLAIN,22);
    Border BordeLinea = BorderFactory.createMatteBorder(5,3,5,3,Color.decode("#023047"));
    //Constructor para la pestaña
    Pestaña() {
        panel = new JPanel(null);
        panel.setBackground(Color.decode("#ffb703"));

        JButton AgregarProducto = new JButton("+");
        AgregarProducto.setFont(Impact);
        AgregarProducto.setBounds(471,546,96,55 );

        try {
            // Cargar la imagen desde un archivo
            BufferedImage image = ImageIO.read(new File("src/img/Anticongelante.png"));

            // Crear un JLabel para mostrar la imagen
            JLabel anticongelanteImg = new JLabel(new ImageIcon(image));
            anticongelanteImg.setBounds(247,186,320,340);
            anticongelanteImg.setBackground(Color.red);
            anticongelanteImg.setBorder(BordeLinea);
            panel.add(anticongelanteImg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        panel.add(AgregarProducto);
    }

}
