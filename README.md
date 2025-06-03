# makeup-artist

in makeup-artist-frontend -> run build corresponds to "build": "ng build --configuration production" in package.json

## Running the Backend

To start the backend Spring Boot application, navigate to the `backend` directory and run the appropriate command for your operating system:

- **For Windows:**
  ```bash
  cd backend
  mvnw.cmd spring-boot:run
  ```

- **For macOS/Linux:**
  ```bash
  cd backend
  ./mvnw spring-boot:run
  ```

This will start the backend server.

## Running the Frontend

The frontend is an Angular application. The `frontend-maven-plugin` is configured to download and use a local version of Node.js and npm.

1.  **Install Node.js, npm, and frontend dependencies:**
    Navigate to the `frontend` directory and run the Maven install command appropriate for your operating system. This step ensures that Node.js and npm are installed locally (in `frontend/target/node`) and then `npm install` is executed.

    - **For Windows:**
      ```bash
      cd frontend
      ../backend/mvnw.cmd clean install
      ```

    - **For macOS/Linux:**
      ```bash
      cd frontend
      ../backend/./mvnw clean install
      ```
    *(Note: We use `../backend/mvnw.cmd` or `../backend/./mvnw` because the Maven wrapper scripts are in the `backend` directory. If you have Maven installed globally and configured on your PATH, you can use `mvn clean install` directly from the `frontend` directory after an initial `cd frontend`.)*

2.  **Start the Angular development server:**
    After the above command completes, navigate to the frontend application's source directory and use the locally installed npm to start the server.

    ```bash
    cd src/main/web
    ../../../target/node/npm start
    ```
    This will start the frontend development server, typically on `http://localhost:4200`.