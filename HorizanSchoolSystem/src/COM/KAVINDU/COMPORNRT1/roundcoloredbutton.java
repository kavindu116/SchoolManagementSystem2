
package COM.KAVINDU.COMPORNRT1;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

public class roundcoloredbutton extends JButton {
    @Override
    public boolean isDefaultButton() {
        return true; 
    }
    public roundcoloredbutton(){
        init();
        

    }
    
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE,"arc:660");
 

    }

   
}
