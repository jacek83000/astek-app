# astek-app

This is a application to calculate Reimbursement.

## How to run 

1. In terminal: go to the root of application (folder called astek app)
2. Run command `mvnw clean package` to build and test application.
3. Go to the `..\astek-app\target` folder. In this folder will find there file called 'astek-app.war'. Copy it.
4. Go to the folder `..\astek-app\tomcat\apache-tomcat-10.1.11\webapps`.
5. Replace current file (astek-app.war) with the one that you've just built.
6. Go to the folder `..\astek-app\tomcat\apache-tomcat-10.1.11\bin` and run 'startup.bat' to start the server.
7. Application should be available in your browser at address: http://localhost:8080/astek-app
