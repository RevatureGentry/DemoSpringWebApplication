## Demo Spring Web Application for Performance Testing
## Endpoints

| Method | URL | Description |
| ------ | --- | ----------- |
| GET | /login | Shows the Login Page |
| GET | /info | Shows the user information page |
| GET | /info/update | Shows the update user info page |
| GET | /register | Shows the Registration Page |
| GET | /todos | The authenticated home page, show table of all todos |
| GET | /todos/create | Shows the Create Todo Form |
| POST | /process | Attempts authentication from form values |
| POST | /register | Attemps registration from form values |
| POST | /todos | Creates todo from Create Todo Form |
| POST | /logout | Signs the user out of the application |
| PUT | /todos | Marks the Todo by id at as completed. Id is found in request body |
| PUT | /info | Attempts to update user information |
| DELETE | /todos | Delete the Todo by id. Id is found in request body |

## Preloaded users

| Username | Password | Roles |
| -------- | -------- | ----- | 
| admin | Password123! | ROLE_ADMIN,ROLE_USER | 
| user2 | Password123! | ROLE_USER | 
| user3 | Password123! | ROLE_USER | 
| user4 | Password123! | ROLE_USER | 
| user5 | Password123! | ROLE_USER | 
| user6 | Password123! | ROLE_USER | 
| user7 | Password123! | ROLE_USER | 
| user8 | Password123! | ROLE_USER | 
| user9 | Password123! | ROLE_USER | 
| user10 | Password123! | ROLE_USER | 