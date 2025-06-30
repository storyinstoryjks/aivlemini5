package aivlemini.domain;

import aivlemini.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "scripts", path = "scripts")
public interface ScriptRepository
    extends PagingAndSortingRepository<Script, Long> {}
