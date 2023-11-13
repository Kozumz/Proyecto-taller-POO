package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ZoomDeImagen {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Zoom de Imagen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel imageLabel = new JLabel();
            ImageIcon icon = new ImageIcon("src/img/Gel.jpg");  // Reemplaza con la ruta de tu imagen
            imageLabel.setIcon(icon);

            imageLabel.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    // Obtén la posición del ratón y ajusta el tamaño de la imagen
                    int zoomFactor = 2;  // Ajusta este valor según sea necesario
                    int x = e.getX();
                    int y = e.getY();
                    int newX = x - (int) (icon.getIconWidth() / 2.0 / zoomFactor);
                    int newY = y - (int) (icon.getIconHeight() / 2.0 / zoomFactor);

                    // Crea una imagen escalada y actualiza el icono en el JLabel
                    Image scaledImage = icon.getImage().getScaledInstance(
                            icon.getIconWidth() * zoomFactor,
                            icon.getIconHeight() * zoomFactor,
                            Image.SCALE_DEFAULT);
                    imageLabel.setIcon(new ImageIcon(scaledImage));

                    // Establece la posición del JLabel para centrar la lupa en el cursor
                    imageLabel.setBounds(newX, newY, scaledImage.getWidth(null), scaledImage.getHeight(null));
                }
            });

            frame.getContentPane().add(imageLabel);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
