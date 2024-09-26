Library-api Microservices





After you add a new user create a new Bearer token(/login)

This project simulate library workspace (microservices)


## Launch Order





To work with data you can use PostMan, or Curl, as a exmaple. In postman choose JSON type and your method


## Services

### 1. **User Service**
The user management service is responsible for storing and managing user accounts.

- **Main features:**
  - Managing user registration and details.

- **Endpoints:**
  `POST /api/users`
  ```
  {
    "name": "Nikita Zhelezko",
    "username": "nickzhezl",
    "email": "zhelezko.nik12345@gmail.com",
    "password": "admin"
  }
  ```

  `GET /api/users/{username}`

  `PUT /api/users/{username}`
  ```
  {
    "name": "Ivan Zolo",
    "username": "zolo",
    "email": "ivanzolo@gmail.com",
    "password": "password"
  }
  ```
To delete USE 'DELETE /apu/users/{id}'


### 2. **Authentication Server**
The authentication service is responsible for issuing JWT tokens for users in the system.

- **Main features:**
  - User authentication.
  - Issuing access and refresh tokens.
  - Token renewal.

- **Endpoints:**

  `POST /api/auth/login`
  ```
  {
    "username": "thisdudkin",
    "password": "password"
  }
  ```

  `POST /api/auth/refresh?refreshToken=`

### 3. **Books Service**
The book management service handles operations related to books, including creation, updating, deletion, and retrieving information about books.

- **Main features:**
  - Managing book data.
  - Searching for books.

- **Endpoints:**
  `POST /api/books`
  ```
  {
    "isbn": "7542103123901",
    "title": "Clean Code",
    "genre": "Science",
    "description": "Code is clean if it can be understood easily – by everyone on the team. Clean code can be read and enhanced by a developer other than its original author. With understandability comes readability, changeability, extensibility and maintainability.",
    "author": "Robert Martin"
  }
  ```

  `GET /api/books/{bookId}`

  `GET /api/books/isbn/{isbn}`

  `PUT /api/books/{bookId}`
  ```
  {
    "isbn": "7551105803901",
    "title": "Introduction to Algorithms",
    "genre": "Science",
    "description": "Some books on algorithms are rigorous but incomplete; others cover masses of material but lack rigor. Introduction to Algorithms uniquely combines rigor and comprehensiveness. The book covers a broad range of algorithms in depth, yet makes their design and analysis accessible to all levels of readers. Each chapter is relatively self-contained and can be used as a unit of study. The algorithms are described in English and in a pseudocode designed to be readable by anyone who has done a little programming. The explanations have been kept elementary without sacrificing depth of coverage or mathematical rigor.",
    "author": "Thomas H Cormen "
  }
  ```

  `DELETE /api/books/{bookId}`

The system uses JWT tokens for authorization
