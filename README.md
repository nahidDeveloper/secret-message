
# Secret Message
This project demonstrates my ability to create secure endpoints as well as authentication of Users by using JWT Authentication. As well as by implementing Roles for further security, all this using MongoDB as we;;

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Setup and Installation](#setup-and-installation)
- [Tests](#tests)
- [Improvements](#improvments)
## Overview
This project allows Users to register and sign up with their credentials to view a secret message,users must be authorised to view this message. I have also allowed a built in Admin account to view this message but to also allow future plans that an admin can modify the message.

## Features

### User Authentication:
- Users can sign up to create an account.
- Existing users can sign in to the service.
- JWT tokens are used for authentication.

### Access Control
- Authenticated users can view the secret message.
- Unauthorized access to the secret message endpoint results in a 401 error response.
- Users can sign out to terminate their session.

### Password Management:

- Users can reset their password while logged in.(Work in progress)
- Passwords are encrypted and decrypted during sign-in and sign-out.

### Error Handling:

- Duplicate credentials result in a 400 Bad Request response with an explanation.(work in progress)
- Unauthorized attempts to reset the password or access restricted endpoints result in a 401 error response.


 ### Endpoint Management:

- `/sign-up`: Registers a new user.
- `/sign-in`: Logs in an existing user.
- `/sign-out`: Logs out the currently logged-in user.
 -`/secret-message`: Retrieves the secret message. Requires authentication.
- `/reset-my-password`: Resets the password for the currently logged-in user.(Work in progress)
- `/generate-token`: Generates a token for password reset.
 ### Testing:

- Code is unit tested and integration tested to ensure reliability and functionality.

### Security:

- Passwords are securely encrypted and decrypted during authentication.
- JWT tokens are used for secure authentication and authorization.

## Technologies

- Spring Booot
- MongoDB
- JWT Authentication
- JUnit
- Mockito
## Setup and Installation

1. Clone the repository:
    ```bash
    git clone <repository_url>
    ```

2. Navigate to the project directory:
    ```bash
    cd <project_directory>
    ```

3. Build and run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```

4. Set up a MongoDB database and configure the database URL, username, and password in `application.properties`. As well as creating a single collection called `secret` and creating a single secret message


## Testing

### Unit Tests

This project includes unit tests and integration tests for our Service repository's and entitys To run the unit tests, execute the following command:

```bash
mvn test
```


## Improvments
### Work in progress

The following implementations are currently in progress:

1. **Admin Endpoints for Secret Message Management**: Implement endpoints for administrators to make changes to the secret message, such as updating or deleting the message content.

2. **Input Sanitization for Registration Inputs**: Enhance input sanitization for registration inputs to improve data integrity and security. Ensure that user input is properly sanitized and validated to prevent potential security vulnerabilities.

3. **Global Exception Handling**: Implement better global exception handling to provide meaningful error messages and responses. Handle scenarios such as missing fields in requests to improve the user experience and facilitate troubleshooting.

4. **Logging with Log4j2**: Integrate logging using Log4j2 or a similar logging framework to capture important events and errors within the application. Logging can aid in debugging, performance monitoring, and security auditing.

**Additional Improvements**

Here are additional improvements that can be considered:

1. **API Documentation**: Implement comprehensive API documentation  Swagger to provide clear documentation on how to use each endpoint, expected request parameters, and response structure.

2. **Input Validation**: Extend input validation to all endpoints to ensure data integrity and security. Validate user input for length, format, and type, and provide meaningful error messages for invalid inputs.

3. **Rate Limiting and Throttling**: Implement rate limiting and throttling mechanisms to prevent abuse or misuse of the API, protecting the application from denial-of-service attacks and ensuring fair resource usage.

4. **CORS Configuration**: Configure Cross-Origin Resource Sharing (CORS) to restrict access to the API from unauthorized domains, enhancing security.

5. **Audit Logging**: Implement audit logging to track user actions within the application for troubleshooting, compliance, and security monitoring purposes.

6. **API Versioning**: Implement API versioning to manage changes and updates over time, allowing for the introduction of new features without breaking existing client implementations.




These improvements aim to enhance the functionality, security, and maintainability of the application, providing a better experience for both users and developers.