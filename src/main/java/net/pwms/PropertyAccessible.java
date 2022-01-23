package net.pwms;

import java.util.List;

public interface PropertyAccessible
{
    String get(final String p0);    
    List<String> keysStartingWith(final String p0);
}
