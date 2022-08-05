package ibk.demo;

public class UserRequestBuilder {

    private String password;
    private String email;

    public static UserRequestBuilder aUser(){
        return new UserRequestBuilder();
    }

    public UserRequestBuilder withPassword(String password){
        this.password = password;
        return this;
    }

    public UserRequestBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public CreateUserRequest build(){
        return new CreateUserRequest(password, email);
    }
}
