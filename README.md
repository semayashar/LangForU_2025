# 🌐 LangForU – Web Application for Language Learning

## 🎯 Project Overview
**LangForU** is a modern, web-based educational platform designed to support **language learning for Bulgarian-speaking users**.  
The system combines **interactive courses, intelligent assistance, and automated evaluation** in one integrated environment.

Developed as part of a **bachelor’s thesis in Software Engineering** at *Ruse University “Angel Kanchev”*, the project demonstrates how innovative web technologies and artificial intelligence can create an effective educational ecosystem.

---

## 🚀 Key Features
- **🧑‍🏫 Interactive Courses:** Structured lessons including video, text, and interactive exercises.  
- **🤖 Virtual Assistant “Sevi”:** AI-based assistant providing explanations, linguistic help, and essay feedback.  
- **🧩 Automatic Evaluation:** Real-time quiz correction and automated final exam scoring with certificate generation.  
- **📰 Educational Blog:** Articles written by instructors, with comments and discussions.  
- **⚙️ Admin Panel:** Management of users, courses, lessons, exams, and blog posts.  
- **📩 Contact Form:** Communication channel between users and administrators.  

---

## 🧱 System Architecture
**LangForU** follows a **multi-layer architecture** built on robust and scalable technologies:

| Layer | Technologies |
|-------|---------------|
| **Frontend** | HTML5, CSS3, JavaScript (Vanilla JS), Thymeleaf |
| **Backend** | Java 17, Spring Boot 3.3.3, Spring Data JPA, Spring Security, WebFlux |
| **Database** | PostgreSQL |
| **Testing** | JUnit 5, Mockito, Selenium |
| **AI Integration** | OpenAI (ChatGPT API) for Virtual Assistant “Sevi” |
| **PDF Generation** | iTextPDF |
| **Build Tool** | Maven |

---

## 🧩 Core Modules
- `AuthController` – User registration, login, and email confirmation  
- `UserController` – User profile and common pages  
- `AdminController` – Administrative operations  
- `CourseController` – Course and lesson management  
- `LectionController` – Learning content  
- `FinalExamController` – Final exams and grading  
- `BlogController` – Blog and comments  
- `ChatPageController` – Integration with AI assistant “Sevi”

---

## 🧠 AI Virtual Assistant “Sevi”
The virtual assistant “Sevi” uses **OpenAI’s ChatGPT API** to provide:
- Real-time conversation during exercises  
- Assistance with grammar and vocabulary  
- Essay evaluation and feedback  
- General educational support  

---

## 🧪 Testing
Testing is carried out on multiple levels:
- **Unit & Integration Tests:** Using JUnit 5 and Mockito  
- **E2E Tests:** Automated UI testing with Selenium WebDriver  
- **Repository Tests:** With `@DataJpaTest` and in-memory database  

---

## 🔒 Security
Implemented using **Spring Security**, ensuring:
- Role-based access control (`USER`, `ADMIN`)  
- Secure login and registration with confirmation tokens  
- Protection against CSRF, XSS, and SQL injection  

---

## 📂 Database Schema
Main entities:
`AppUser`, `Course`, `Lection`, `Question`, `FinalExam`, `ExamResult`,  
`Blog`, `BlogComment`, `BlogLike`, `UserCourseRequest`, `ConfirmationToken`,  
`ContactRequest`, `Subscriber`.

---

## 📈 Results
- Fully functional prototype of an intelligent learning platform  
- Integration of AI for educational dialogue  
- Automated grading and certification  
- Scalable, modular architecture built with Java and Spring Boot  

---

## 🏛️ Academic Context
**Author:** *Sema Salieva Yasharova*  
**Supervisor:** *Metodi Dimitrov*  
**Institution:** Ruse University “Angel Kanchev”  
**Degree:** Bachelor in Software Engineering  
**Year:** 2025  

---

## ⚙️ How to Run
```bash
# 1. Clone repository
git clone https://github.com/your-username/LangForU.git
cd LangForU

# 2. Build project
mvn clean install

# 3. Run Spring Boot app
mvn spring-boot:run

# 4. Access in browser
http://localhost:8080
