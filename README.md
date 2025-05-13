### 🔐 JWT Role-Based Authentication

### 📄 Access OpenAPI Documentation
After adding the necessary dependencies, you can access the OpenAPI documentation and Swagger UI through the following URLs:

1. **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) 📋
2. **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 🖥️

#### 📚 Using Spring Doc
For more detailed information and advanced configurations, refer to the official Spring Doc documentation:
- [Spring Doc Official Website](https://springdoc.org/) 🌐

### 🗃️ Database Schema (DBML for dbdiagram.io)

```dbml
Table users {
  id bigint [pk, increment]
  username varchar [unique, not null]
  email varchar [unique, not null]
  password varchar [not null]
  role varchar
}
```

#### Explanation
- **Table `users`:** Represents the User entity.
    - **id:** Primary key, auto-incremented.
    - **username:** Unique and not null.
    - **email:** Unique and not null.
    - **password:** Not null.
    - **role:** Enum type stored as a string.

### 📊 PlantUML Diagram

```plantuml
@startuml

actor User
entity "AuthController" as AC
entity "AuthenticationService" as AS
entity "JwtService" as JS
entity "UserRepository" as UR

User -> AC : Register
AC -> AS : register(request)
AS -> UR : save(user)
AS -> JS : generateToken(user)
AS -> JS : generateRefreshToken(user)
AS -> AC : return tokens

User -> AC : Login
AC -> AS : login(request)
AS -> UR : findByUsername(username)
AS -> JS : generateToken(user)
AS -> JS : generateRefreshToken(user)
AS -> AC : return tokens

User -> AC : Refresh Token
AC -> JS : extractUsername(refreshToken)
AC -> UR : findByUsername(username)
AC -> JS : isRefreshTokenValid(refreshToken, user)
AC -> JS : generateToken(user)
AC -> User : return new access token

@enduml
```

#### Explanation
- **Actors and Entities:**
    - **User:** Represents the end-user interacting with the system.
    - **AuthController:** Handles HTTP requests for authentication.
    - **AuthenticationService:** Manages authentication logic.
    - **JwtService:** Handles JWT token generation and validation.
    - **UserRepository:** Interacts with the database to manage user data.

- **Processes:**
    - **Register:** User sends a registration request, which is processed to create a new user and generate tokens.
    - **Login:** User sends login credentials, which are authenticated to generate tokens.
    - **Refresh Token:** User requests a new access token using a refresh token.

