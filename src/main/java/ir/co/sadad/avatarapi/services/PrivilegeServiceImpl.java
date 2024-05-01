package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.repositories.ServiceAccessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {

    private final ServiceAccessRepository accessRepository;

    @Override
    public Mono<Boolean> hasPrivilege(String ssn) {
        return Mono.just(accessRepository.getUsers().contains(ssn));
    }
}
