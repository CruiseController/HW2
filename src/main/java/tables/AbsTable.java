package tables;

import java.util.HashMap;
import java.util.Map;

public abstract class AbsTable {

    protected String name;
    protected Map<String, String> columns = new HashMap<>();

    public AbsTable(String name) {
        this.name = name;
    }

    private String convertMapColumnsToString() {
        String result = "";
        for (Map.Entry<String, String> el : columns.entrySet()) {
            result += el.getKey() + " " + el.getValue() + ",";
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }
}
