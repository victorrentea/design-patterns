package victor.training.patterns.adapter.domain;

public interface IUserApiAdapter {
    User fetchUser(String username);
}
