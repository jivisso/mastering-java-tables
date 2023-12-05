/*
 * Visso Sistemas
 * Desarrollo 2023
 */
package ar.jivisso.javacourses.tables.common;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import javax.swing.JDialog;

/**
 *
 * @author jivisso
 */
public class CustomJDialog extends JDialog {
    
    
    @Override
    public void setVisible(boolean visible){
        if (visible){
            screenAlign();
        }
        super.setVisible(visible);
    }      
    
    protected void screenAlign(){
        // Para soportar configuraciones con multiples pantallas, se alinea al centro de la
        // pantalla en la que esta el objeto, no sobre el screenSize total del Toolkit, porque este
        // suma todas las pantallas.
        double sc_width;
        double sc_height;
        
        int leftOffset = 0;
        int topOffset = 0;

        if (getGraphicsConfiguration() != null){
            // Multiples pantallas. Se genera un GraphicsConfiguration, del cual
            // puedo obtener el dispositivo actual.
            GraphicsDevice actualDevice = getGraphicsConfiguration().getDevice();
            DisplayMode display = actualDevice.getDisplayMode();
            sc_width = display.getWidth();
            sc_height = display.getHeight();            
        }
        else {
            // Una sola pantalla
            sc_width = getToolkit().getScreenSize().getWidth();
            sc_height = getToolkit().getScreenSize().getHeight();
        }        
        
        int defaultMenuBarSize = 12;
        
        double w = this.getPreferredSize().getWidth();
        double h = this.getPreferredSize().getHeight();

        int centeredX = (int)((sc_width/2)-(w/2));
        int centeredY = (int)((sc_height/2)-(h/2));
        // Si estamos en Windows, la barra de inicio me saca algunos pixeles, asi
        // que la subo un poco. En Linux la barra estaria arriba, asi que la bajo.
        if (System.getProperty("os.name").toLowerCase().contains("windows")){
            centeredY -= defaultMenuBarSize;
        }                
        else if (System.getProperty("os.name").toLowerCase().contains("linux")){
            centeredY += defaultMenuBarSize;
        }
        // Acomodo la ventana en el centro de la pantalla     
        this.setLocation(
            centeredX + leftOffset,
            centeredY + topOffset
        );
    }
    
    
}
