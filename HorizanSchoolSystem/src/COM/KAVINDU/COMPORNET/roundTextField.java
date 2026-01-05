
package COM.KAVINDU.COMPORNET;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JTextField;


public class roundTextField extends JTextField{
    
    public roundTextField (){
        init();
    
}
    
    private void init(){
               this.putClientProperty(FlatClientProperties.STYLE,"arc:660");

    }
}
