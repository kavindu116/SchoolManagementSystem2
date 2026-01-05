
package COM.KAVINDU.COMPORNET;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPanel;
import org.jfree.chart.JFreeChart;


public class RoundPanel1 extends JPanel{
    public RoundPanel1(){
        init();
    }
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, "arc:20");
    }

   
}
