package ibk.demo;

import java.util.Optional;

public class ConfVariables {
    public static String getHost() {
        return Optional.ofNullable(System.getenv("host"))
                .orElse(ApplicationProperties2.getInstance().getProperty("host"));
    }

    public static String getMPath() {
        return Optional.ofNullable(System.getenv("mpath"))
                .orElse(ApplicationProperties2.getInstance().getProperty("mpath"));
    }

//}    public static String getHost(){
//        return Optional.ofNullable(System.getenv("host"))
//                .orElse((String)ApplicationProperties.loadPropertiesFile().get("host"));
//    }
//
//    public static String getMPath(){
//        return Optional.ofNullable(System.getenv("mpath"))
//                .orElse((String)ApplicationProperties.loadPropertiesFile().get("mpath"));
//    }
}
