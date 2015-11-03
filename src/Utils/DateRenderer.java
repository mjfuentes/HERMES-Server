package Utils;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;
    private Date dateValue;
    private SimpleDateFormat sdfNewValue = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private String valueToString = "";

    @Override
    public void setValue(Object value) {
        if ((value != null)) {
            valueToString = sdfNewValue.format((Date) value);
            value = valueToString;
        }
        super.setValue(value);
    }
}