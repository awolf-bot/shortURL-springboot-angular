Hello there! :)
This is an application build on Spring boot for backend, Angular v11 for frontend and MySQL for database. 

Setup before running the application (for Windows): 
1.Create a database in MySQL named urlDB.
2. Open backend(springboot) and look for application.properties file
follow this path backend(springboot) -> ShortURL -> src -> main-> resources.
 --> change the following properties: 
     spring.datasource.username
     spring.datasource.password 
enter your msql username and password. Save it. 
3. Open the project ShortURL in backend(springboot) folder in STS. 
4. Run the project by right clicking and select run as spring boot application. 
It should start running at localhost:8080
Note: make sure you have jdk17 installed in your system.                                      

5. Now open frontend(angular) folder and open shortURLApp.
6. Now in the same directory open cmd and enter command ng serve 
It should start running at localhost:4200                                      
Note: make sure you have Node and Angular installed in your system. 

About this project:
-a user enters a long url and only after its valid syntax he is able to create a short url that he can use to access his original url.
-if the long url already exist in database he is prompted with a error msg
-if a user enter a short url in new tab which is not assigned to any long url that is short url is not valid then he is directed to a page 
where he is notified that no such url exists and he can navigate to create a short url by clicking the go to home button
-after 5 mins once user click on short url a service hits backend and rase the flag that link is expired. 
