package ir.co.sadad.avatarapi.services;

import reactor.core.publisher.Mono;

/**
 * service for check privilege of uses
 */
public interface PrivilegeService {

    /**
     * check that user has privilege to do crud service or not
     *
     * @param ssn ssn of user
     * @return has privilege or not
     */
    Mono<Boolean> hasPrivilege(String ssn);

}
