/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pilgrimscrutinizer;
import Gui.Login;
import de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel;
import javax.swing.UIManager;
/**
 *
 * @author HP
 */
public class PilgrimScrutinizer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SyntheticaSkyMetallicLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Login l=new Login();
        l.setVisible(true);
        // TODO code application logic here
    }
}
