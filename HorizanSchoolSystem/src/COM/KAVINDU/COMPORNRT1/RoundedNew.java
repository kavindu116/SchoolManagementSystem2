
package COM.KAVINDU.COMPORNRT1;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPanel;

public class RoundedNew extends JPanel{
    public RoundedNew(){
        init();
    }
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "arc:20");
    }
    
}
