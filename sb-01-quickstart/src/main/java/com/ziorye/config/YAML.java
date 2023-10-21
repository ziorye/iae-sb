package com.ziorye.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "yaml")
@Data
public class YAML {
    String description;
    String whatItIs;
    Boolean easyToUse;
    Double version;
    Date releaseDate;
    String[] authors;
    List<String> goals;
    Map<String, Object> frameworksAndTools;
    Set<String> revisions;
    Specification specification;
    Map<String, List<Specification>> specifications;
}

@Component
@ConfigurationProperties(prefix = "yaml.specification")
@Data
class Specification {
    String name;
    String url;
}
