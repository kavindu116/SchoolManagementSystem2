
package COM.KAVINDU.COMPORNET;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

public class roundbutton extends JButton {
    public roundbutton(){
        init();
    }
    
    private void init(){
       this.putClientProperty(FlatClientProperties.STYLE,"arc:660");
    }
}
