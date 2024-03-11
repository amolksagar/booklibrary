A) **Steps to run the project in default profile**
- **Step 1**: Clone the repository shared(using the command given) or download the zipped version
  **"git clone https://github.com/amolksagar/booklibrary.git"**

- **Step 2**:
  cd to the booklibrary folder.
  Execute the command
  **mvn clean install**

- **Step 3**:
  From the same location as defined in Step 2, execute the command **"mvn spring-boot:run"**

- **Step 4**:
  Please open the postman collection sent with the email.The services should be hosted at http://localhost:8082/booklibrary/. For example the endpoint for Get All Books Url is GET http://localhost:8082/booklibrary/book-library/v1/books?sortBy=title

- **Step 5**:
  Open API has been used to define the specification and swagger tools have been for implementing the Open API specifications. There are multiple benefits of using Open API which I have detailed below in a seperate section
  **Swagger URL** : http://localhost:8082/booklibrary/docs/swagger-ui/index.html#/
  The yaml file can be found at https://github.com/amolksagar/booklibrary/blob/master/src/main/resources/oas/booklibrary.yaml or you can find the same in the zip file of the code I have shared
- **Step 6**:
  The postman collection to test the multiple use cases has been provided too.
  Please run the postman collection named **"Transfer Registration"**

**Important Points:**
- CustomExceptionHandler has been implemented which will customise the messages sent back in a particular format
- Basic Auth has been implemented
- Profile based logging has been implemented
- Names of the tests in Postman are such that they will give an idea as to what use case is in question


**Testing Done:**
- Units tests
- Manual testing using Postman fo this test.In production grade applications we can use frameworks like Karate to write feature wise test cases and run them via Jenkins pipeline

**Benefits Of Open API**
- Design-First Approach can be easily followed
- Code Generators generate the stubs and developers do not need to create the models
- It has a Huge Userbase
- Stable Implementation
