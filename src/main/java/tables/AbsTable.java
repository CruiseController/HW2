package tables;

import java.util.HashMap;
import java.util.Map;

public abstract class AbsTable {

    protected String name;
    protected Map<String, String> columns = new HashMap<>();

    public AbsTable(String name) {
        this.name = name;
    }

}
