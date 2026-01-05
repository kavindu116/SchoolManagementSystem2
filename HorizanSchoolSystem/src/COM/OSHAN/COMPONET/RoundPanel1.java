
package COM.OSHAN.COMPONET;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPanel;


public class RoundPanel1 extends JPanel{
    public RoundPanel1(){
        init();
    }
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "arc:20");
    }
}
