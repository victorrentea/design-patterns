package victor.training.patterns.adapter.domain;

public interface ExtrernalUserService {
    User retrieveByUsername(String username);
}
