package fr.cuib.mediathequeweb;

import fr.cuib.mediathequeweb.security.SecurityTools;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.net.*;

public class Main {

    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Test();
    }

    static URL full;
    static {
        try {
            full = new URL("http://localhost:8080/CUIB/faces/confirm.jsf?compte=7YNfUFBGgxrM72TdazqGNs2FJFbIXn-_HGVWwEpyYFPuOFCHB6WDenynqET6CdIdsuVzMlbI7fQO-WqAXiiWsHuKT_ZB9T-nTQhwWfBDnV6YIhZDHMCM6bO_ZRJ_f3Bsg0S7fQciL3w9Gt-gsME4WEQhFKw6ZytKNP0nWMXdqkL1ZEjdZ0G9bPQa30c1fwAdeTube6bce1IIH4q_ZmacZxApjVDrlB_d2GkdPYM47kQ=");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    static String compteEncoded = "7YNfUFBGgxrM72TdazqGNs2FJFbIXn-_HGVWwEpyYFPuOFCHB6WDenynqET6CdIdsuVzMlbI7fQO-WqAXiiWsHuKT_ZB9T-nTQhwWfBDnV6YIhZDHMCM6bO_ZRJ_f3Bsg0S7fQciL3w9Gt-gsME4WEQhFKw6ZytKNP0nWMXdqkL1ZEjdZ0G9bPQa30c1fwAdeTube6bce1IIH4q_ZmacZxApjVDrlB_d2GkdPYM47kQ=";
    static String urlDecoded2 ="/l=bdgsvhvh&c=30403&e=quentinos@cuib.fr&p=PBKDF2WithHmacSHA256:2048:xvBIDioMWQX5fqyAR/NLW+GgFO2QoZGGvtsa0OIlrvs=:rPi5fcpZilNfhzfpUOjYQ00dgoYuFRE9mHJoO1PP8u0=&d=23-11-22-14:13:27";

    public static void Test() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        System.out.println(full);
        String query = full.getQuery();
        String compte = query.split("=")[1];
        String urlDecoded = SecurityTools.decrypt(compte);
        System.out.println(buildQueryMap(urlDecoded2.substring(1)));

    }

    private static Map<String, String> buildQueryMap(String query) {
        if (query == null)
            return null;
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String[] currentParam = param.split("=");
            if (currentParam.length != 2) {
                String name = currentParam[0];
                String value = query.substring(query.indexOf("p=")+2,query.indexOf("=&")+1);
                map.put(name, value);
                continue;
            }
            String name = currentParam[0];
            String value = currentParam[1];
            map.put(name, value);
        }
        return map;
    }

}
