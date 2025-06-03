# makeup-artist

in makeup-artist-frontend -> run build corresponds to "build": "ng build --configuration production" in package.json

## Running the Backend

*(If `mvnw.cmd` or `./mvnw` fails with a properties file error, please see the "Troubleshooting Missing Maven Wrapper" section below.)*

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
    *(If `../backend/mvnw.cmd` or `../backend/./mvnw` fails with a properties file error, please see the "Troubleshooting Missing Maven Wrapper" section below.)*
    Navigate to the `frontend` directory and run the Maven install command appropriate for your operating system. This step ensures that Node.js and npm are installed locally (in `frontend/target/node`) and then `npm install` is executed.

    - **For Windows:**
      ```bash
      cd frontend
      mvn clean install -DskipTests
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
    npm start
    ```
    This will start the frontend development server, typically on `http://localhost:4200`.

### Troubleshooting Missing Maven Wrapper

If you encounter an error like "Impossibile trovare il percorso ... maven-wrapper.properties" or "Cannot start maven from wrapper", it means the Maven wrapper files are missing or corrupted in your `backend/.mvn/wrapper/` directory.

To regenerate the Maven wrapper files for the backend module:

1.  **Ensure you have Apache Maven installed globally.** You can download it from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi) and make sure it's added to your system's PATH. This is a temporary requirement to fix the wrapper.
2.  **Navigate to the `backend` directory** in your project.
    ```bash
    cd backend
    ```
3.  **Run the Maven wrapper command:**
    ```bash
    mvn wrapper:wrapper
    ```
    This command will download the necessary `maven-wrapper.jar` and create the `maven-wrapper.properties` file in the `backend/.mvn/wrapper/` directory. It will also ensure `mvnw` and `mvnw.cmd` are up to date.
4.  **Important:** After running this command, you should see new or updated files in `backend/.mvn/wrapper/` (and potentially `backend/mvnw`, `backend/mvnw.cmd`). **These files should be committed to your Git repository.** This ensures that other users can build the project without needing a global Maven installation.

    Example commit commands:
    ```bash
    git add .mvn/wrapper/maven-wrapper.jar .mvn/wrapper/maven-wrapper.properties mvnw mvnw.cmd
    git commit -m "Fix: Regenerate Maven wrapper files for backend module"
    ```

Once these files are regenerated and committed, the `./mvnw` (macOS/Linux) or `mvnw.cmd` (Windows) commands should work correctly.