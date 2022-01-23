package net.pwms;

import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyManager implements PropertyAccessible
{
    private String pfx;
    private Properties props;
    
    public PropertyManager(final String s) {
        this(s, true);
    }
    
    public PropertyManager(final String s, final boolean b) {
        this.pfx = s;
        this.props = new Properties();
        InputStream inStream = null;
        try {
            System.err.println("PropertyManager trying " + s + ".props");
            inStream = new FileInputStream(s + ".props");
            System.err.println("PropertyManager opened " + s + ".props");
            this.props.load(inStream);
            if (b) {
                this.props.list(System.err);
            }
            else {
                System.err.println("PropertyManager loaded " + this.props.size() + " props");
            }
        }
        catch (Exception obj) {
            System.err.println("PropertyManager exception " + obj);
        }
        finally {
            try {
                if (inStream != null) {
                    ((FileInputStream)inStream).close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    @Override
    public String get(final String key) {
        return this.props.getProperty(key, "");
    }
    
    @Override
    public List<String> keysStartingWith(final String prefix) {
        final ArrayList<String> list = new ArrayList<String>();
        final Enumeration<?> propertyNames = this.props.propertyNames();
        while (propertyNames.hasMoreElements()) {
            final String s = (String)propertyNames.nextElement();
            if (prefix.equals("") || s.startsWith(prefix)) {
                list.add(s);
            }
        }
        return list;
    }
    
    public static void main(final String[] array) {
    }
}
