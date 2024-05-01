package ir.co.sadad.avatarapi.repositories;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * repo for getting list of users that have privilege to add configs
 */
@Component
@ConfigurationProperties(prefix = "services")
@Data
public class ServiceAccessRepository {

    private List<String> users = new ArrayList<>();
}



