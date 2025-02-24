# Example Consumer Test Pact JVM

This project is a Java-based consumer client that interacts with a service using HTTP requests. It is designed to facilitate testing with Pact, a contract testing tool that ensures that the consumer and provider services can communicate effectively.

## Repo Purpose

Demonstrate a working consumer contract test for `pact-jvm-consumer-junit 4.1.43`. The [ExampleJavaConsumerPactRuleTest.java](https://github.com/pact-foundation/pact-jvm/blob/v4.1.x/consumer/junit/src/test/java/au/com/dius/pact/consumer/junit/examples/ExampleJavaConsumerPactRuleTest.java) and [ConsumerClient.java](https://github.com/pact-foundation/pact-jvm/blob/3_6_15/pact-jvm-consumer-junit/src/test/java/au/com/dius/pact/consumer/exampleclients/ConsumerClient.java) files were sourced from the [pact-foundation](https://github.com/pact-foundation) GitHub organization.

## Current Issue

When running mvn test, the test errors as `provider.getUrl()` returns null. See below for details:

```bash
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running exampleclients.ExampleJavaConsumerPactRuleTest
Provider URL: null
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.024 s <<< FAILURE! - in exampleclients.ExampleJavaConsumerPactRuleTest
[ERROR] runTest(exampleclients.ExampleJavaConsumerPactRuleTest)  Time elapsed: 0.014 s  <<< FAILURE!
java.lang.AssertionError: Provider URL is null! Aborting test.
        at exampleclients.ExampleJavaConsumerPactRuleTest.runTest(ExampleJavaConsumerPactRuleTest.java:57)

[INFO] 
[INFO] Results:
[INFO] 
[ERROR] Failures: 
[ERROR]   ExampleJavaConsumerPactRuleTest.runTest:57 Provider URL is null! Aborting test.
[INFO] 
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```

## Project Structure

- `src/main/java/exampleclients/ConsumerClient.java`: Contains the implementation of the consumer client that makes HTTP requests to the service.
- `src/test/java/exampleclients/ConsumerClientTest.java`: Contains the test cases for the `ConsumerClient` to ensure its functionality and correctness.
- `pom.xml`: Maven configuration file that manages project dependencies and build settings.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven

### Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd example-consumer-test-pact-jvm
   ```

3. Build the project using Maven:
   ```
   mvn clean install
   ```

### Running Tests

To run the tests, use the following Maven command:
```
mvn test
```

### Dependencies

This project uses the following dependencies:
- Pact JVM for contract testing
- Apache HttpClient for making HTTP requests
- Jackson for JSON processing

## Usage

To use the `ConsumerClient`, instantiate it with the base URL of the service you want to interact with, and call the appropriate methods to make requests.

Example:
```java
ConsumerClient client = new ConsumerClient("http://example.com/api");
Map response = client.getAsMap("/endpoint", "param=value");
```
