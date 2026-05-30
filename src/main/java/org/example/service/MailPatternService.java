package org.example.service;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class MailPatternService {
    public Map<String, Object> generatePattern(String name, String domain){
        String[] parts = name.split("\\s+");
        Map<String, Object> result = new LinkedHashMap<>();
        if(parts.length == 0 || domain == null || domain.isEmpty()){
            result.put("error" , "name and domain required");
            return result;
        }
        String first = parts[0];
        String middle = parts.length > 2 ? parts[1] : "";
        String last = parts.length > 1 ?parts[parts.length-1] : "";
        Set<String> set = new LinkedHashSet<>();
        if(!last.isEmpty()){
            set.add(first+"."+last+"@"+domain);
            set.add(first+"_"+last+"@"+domain);
            set.add(first.charAt(0)+last+"@"+domain);
            set.add(last+"."+first+"@"+domain);
            set.add(first+last+"@"+domain);
            set.add(first+"."+last.charAt(0)+"@"+domain);
            set.add(first.charAt(0)+"."+last+"@"+domain);
            set.add(last+first.charAt(0)+"@"+domain);
        }
        if(!middle.isEmpty()){
            set.add(first+"."+middle.charAt(0)+"."+last+"@"+domain);
            set.add(first.charAt(0) +""+ middle.charAt(0) + last + "@" + domain);
        }
        set.add(first+"@" + domain);
        result.put("patterns", set);
        return result;
    }
}
