# # REST Assured API Testing Framework for PetStore Swagger API BY MUSTAPHA-QA

**## About the Project**

**### Overview**

- This project presents a comprehensive REST API testing framework designed to automate API testing of the Petstore Swagger API, ensuring its quality and reliability. It focuses on testing the Pet, Store and User models.

**### Features**

- List of the key features:
  - Test cases covering GET, POST, PUT, DELETE HTTP requests for Pet, Store and User models
  - Data generation using POJOs and Faker library
  - Extent report and logging for comprehensive test results

**### Technologies Used**

- List the technologies employed:
  - IDE: Eclipse
  - Coding language: Java
  - Test automation framework: TestNG
  - API testing library: REST Assured
  - Build and project management tool: Maven

**## Framework Structure**

- The framework's structure is organized into four packages:

  - ```
    api.endpoints
    ```

    - Routes class which encapsulates URLs.
    - Endpoint classes (one per model) for CRUD operations.

  - ```
    api.payload
    ```

    - Houses POJO classes representing model data used for testing.

  - ```
    api.test
    ```

    - Contains test classes with actual test methods executing HTTP requests.
    - Employs setup methods to generate test data using POJOs and Faker.

  - ```
    api.utilities
    ```

    - Contains the ExtentReportManager class for report generation.

**## Setup and Usage**

- Add necessary dependencies according to the pom.xml file.

**## Project Structure**

To ensure a clear and organized project structure, we've adopted the following conventions:

- Source code:
  - All project packages are located within the `src/test/java`folder, as this is the standard directory for test code in Maven projects.
- External resources:
  - The `src/test/resources` folder serves as a repository for external files essential for testing, including:
    - Schema files for JSON schema validation
    - Configuration files like `log4j2.xml`for logging

**## Results Achieved** 

- Comprehensive validation of API responses:
  - **Successful status code verification:** All response status codes matched expectations based on the Swagger document, ensuring successful HTTP requests.
  - **Accurate response body:** Response body data consistently aligned with the API's Swagger document, verifying data integrity.
  - **Header validation:** Selected response headers were verified for correctness.
- Robust error handling:
  - **Meaningful error messages and status codes:** The API returns informative error messages and appropriate status codes in various error scenarios, aiding in troubleshooting and debugging.
- JSON schema validation:
  - **Schema compliance verified:** JSON schemas were successfully validated for different HTTP requests, ensuring adherence to data structure and format requirements.

**## Screenshot of Extent Report**

<img src="C:\Users\AMINE\Desktop\API files\PetStore all models Extent report.PNG" style="zoom:60%;" /> 

<img src="C:\Users\AMINE\Desktop\API files\PetStore_all_models_extent_report.PNG" style="zoom:60%;" />

This screenshot demonstrates the comprehensive test results generated by the framework (check screenshots folder).

**## License**

- This project is licensed under the MIT License.

