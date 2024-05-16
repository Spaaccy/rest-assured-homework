package tbc.tbcacademy.ge.data.steps.helpers;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EmployeesNamespaceContext implements NamespaceContext {
    private final Map<String, String> namespaces = new HashMap<>();

    public EmployeesNamespaceContext() {
        namespaces.put("ns2", "http://interfaces.soap.springboot.example.com");
        namespaces.put("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
    }

    @Override
    public String getNamespaceURI(String prefix) {
        if (prefix == null) {
            return namespaces.getOrDefault(XMLConstants.DEFAULT_NS_PREFIX, "");
        }
        return namespaces.get(prefix);
    }

    @Override
    public String getPrefix(String namespaceURI) {
        for (Map.Entry<String, String> entry : namespaces.entrySet()) {
            if (entry.getValue().equals(namespaceURI)) {
                return entry.getKey();
            }
        }
        return null;
    }
    @Override
    public Iterator<String> getPrefixes(String namespaceURI) {
        return namespaces.keySet().stream()
                .filter(prefix -> namespaces.get(prefix).equals(namespaceURI))
                .iterator();
    }
}