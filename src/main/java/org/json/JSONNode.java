package org.json;

public class JSONNode {
    public String path;
    public String key;
    public Object value;

    public JSONNode(String path, String key, Object value) {
        this.path = path;
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "JSONNode{" +
                "path='" + path + '\'' +
                ", key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
