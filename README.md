A) **Steps to run the project in default profile**
- **Step 1**: Clone the repository shared(using the command given)
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
  The yaml file can be found at https://github.com/amolksagar/booklibrary/blob/master/src/main/resources/oas/booklibrary.yaml
- **Step 6**:
  The postman collection to test the multiple use cases has been provided too.
  Please run the postman collection named **"RNSW"**
- **Step 7**:
  The h2 db console is hosted at http://localhost:8082/booklibrary/h2-console/
  Enter the jdbc url from the application context logs.Search for text 'H2 console available at '/h2-console'. Database available at'
  You will find the jdbc url.Please note the jdbc url changes with each application launch Eg format:jdbc:h2:mem:a1a6c333-7f2d-4348-a9b2-6903657439f8

**Important points**:
  - A few book entries have been added during application load so that the library wont be empty initially
  - Caching has been implemented for the STATUS table which is a lookup table holding the statusId vs the Status description

**Testing Done:**
- Units tests
- Manual testing using Postman fo this test.In production grade applications we can use frameworks like Karate to write feature wise test cases and run them via Jenkins pipeline

**Benefits Of Open API**
- Design-First Approach can be easily followed
- Code Generators generate the stubs and developers do not need to create the models
- It has a Huge Userbase
- Stable Implementation