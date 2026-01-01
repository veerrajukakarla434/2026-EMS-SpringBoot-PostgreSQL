# Employee Management System

A full-stack employee management system built with Spring Boot, PostgreSQL, React, and TypeScript.

## 🏗️ Architecture

### Backend (Spring Boot)
```
src/main/java/com/company/employeemanagement/
├── config/              # OpenAPI and app configuration
├── controller/          # REST API controllers with OpenAPI docs
├── dto/                 # Data Transfer Objects
├── entity/              # JPA entities
├── exception/           # Custom exceptions and global handler
├── repository/          # Spring Data JPA repositories
├── service/             # Service interfaces
│   └── impl/            # Service implementations
└── util/                # Helper and mapper utilities
```

### Frontend (React + TypeScript)
```
src/
├── components/
│   ├── common/          # Reusable components (Button, Modal, Pagination)
│   ├── employees/       # Employee-specific components
│   └── departments/     # Department-specific components
├── services/            # API service layer
├── store/               # Zustand state management
├── types/               # TypeScript interfaces
└── styles.css           # Reusable CSS components
```

## 🚀 Key Features

### Backend Features
- ✅ **Layered Architecture**: Clear separation of concerns (Controller → Service → Repository)
- ✅ **Service Interfaces**: Service layer uses interfaces with implementation classes
- ✅ **DTO Pattern**: Separate request/response DTOs with validation
- ✅ **Mapper Utilities**: Helper classes to transform entities to DTOs
- ✅ **Pagination & Filtering**: Efficient handling of large datasets
- ✅ **OpenAPI Documentation**: Swagger UI at `/api/swagger-ui.html`
- ✅ **Global Exception Handling**: Centralized error management
- ✅ **CRUD Operations**: Complete Create, Read, Update, Delete for both modules

### Frontend Features
- ✅ **TypeScript**: Type-safe development
- ✅ **Zustand State Management**: Lightweight and performant
- ✅ **React Query**: Server state management with caching
- ✅ **Reusable Components**: Button, Modal, Pagination, Form inputs
- ✅ **Reusable CSS**: Consistent styling with CSS variables
- ✅ **Form Validation**: React Hook Form with validation
- ✅ **Toast Notifications**: User feedback for actions
- ✅ **Responsive Design**: Works on all screen sizes

## 📋 Prerequisites

- Java 17+
- Maven 3.6+
- PostgreSQL 12+
- Node.js 18+
- npm or yarn

## 🛠️ Backend Setup

### 1. Database Setup
```sql
-- Create database
CREATE DATABASE employee_db;

-- Run the SQL schema from db_schema.sql
```

### 2. Configure Application
Edit `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/employee_db
    username: your_username
    password: your_password
```

### 3. Run Backend
```bash
cd employee-management-backend
mvn clean install
mvn spring-boot:run
```

Backend runs at: `http://localhost:8080/api`
Swagger UI: `http://localhost:8080/api/swagger-ui.html`

## 🎨 Frontend Setup

### 1. Install Dependencies
```bash
cd employee-management-frontend
npm install
```

### 2. Configure API URL
Edit `src/services/api.service.ts` if needed:
```typescript
const API_BASE_URL = 'http://localhost:8080/api';
```

### 3. Run Frontend
```bash
npm run dev
```

Frontend runs at: `http://localhost:5173`

## 📡 API Endpoints

### Department Endpoints
- `GET /api/departments` - Get all departments (paginated)
- `GET /api/departments/{id}` - Get department by ID
- `GET /api/departments/list` - Get all departments (no pagination)
- `GET /api/departments/search?search={keyword}` - Search departments
- `POST /api/departments` - Create department
- `PUT /api/departments/{id}` - Update department
- `DELETE /api/departments/{id}` - Delete department

### Employee Endpoints
- `GET /api/employees` - Get all employees (paginated)
- `GET /api/employees/{id}` - Get employee by ID
- `GET /api/employees/list` - Get all employees (no pagination)
- `GET /api/employees/search?search={keyword}` - Search employees
- `GET /api/employees/filter?departmentId=&position=&search=` - Filter employees
- `GET /api/employees/department/{departmentId}` - Get employees by department
- `POST /api/employees` - Create employee
- `PUT /api/employees/{id}` - Update employee
- `DELETE /api/employees/{id}` - Delete employee

## 🎯 Best Practices Implemented

### Backend
1. **Service Layer Pattern**: Services defined as interfaces, implementations separate
2. **DTO Transformation**: Mapper utilities handle entity-to-DTO conversion
3. **Validation**: Bean validation on DTOs with custom error messages
4. **Exception Handling**: Global exception handler for consistent error responses
5. **Pagination**: Proper pagination support for scalability
6. **Logging**: SLF4J logging throughout the application
7. **OpenAPI**: Complete API documentation with examples

### Frontend
1. **State Management**: Zustand for global state, React Query for server state
2. **Component Reusability**: Common components (Button, Modal, Pagination)
3. **CSS Architecture**: CSS variables for theming, reusable utility classes
4. **Type Safety**: Full TypeScript implementation
5. **Error Handling**: Toast notifications for user feedback
6. **Form Management**: React Hook Form with validation
7. **Code Organization**: Feature-based folder structure

## 🔧 Configuration

### Pagination Defaults
Backend (`application.yml`):
```yaml
app:
  pagination:
    default-page-size: 10
    max-page-size: 100
```

Frontend (default parameters):
```typescript
page: 0,      // 0-indexed
size: 10,
sortBy: 'id',
sortDir: 'asc'
```

## 📝 Sample Data

The SQL schema includes sample data:
- 4 Departments (Engineering, HR, Finance, Marketing)
- 4 Sample Employees with different roles

## 🧪 Testing APIs

### Using Swagger UI
Navigate to: `http://localhost:8080/api/swagger-ui.html`
- Interactive API documentation
- Try out endpoints directly
- View request/response schemas

### Using cURL
```bash
# Get all employees
curl http://localhost:8080/api/employees

# Create new employee
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "position": "Developer",
    "salary": 75000,
    "departmentId": 1
  }'
```

## 📚 Technology Stack

### Backend
- Spring Boot 3.2.0
- Spring Data JPA
- PostgreSQL
- Lombok
- SpringDoc OpenAPI 3
- Bean Validation

### Frontend
- React 18
- TypeScript 5
- React Router DOM 6
- React Query 3
- Zustand 4
- React Hook Form 7
- Axios
- React Icons
- React Toastify
- Date-fns

## 🤝 Contributing

1. Follow the layered architecture pattern
2. Use service interfaces with implementations
3. Create DTOs for all API requests/responses
4. Add proper validation and error handling
5. Write reusable components and CSS
6. Use TypeScript for type safety
7. Document API endpoints with OpenAPI annotations

## 📄 License

This project is licensed under the MIT License.

## 👥 Authors

Your Company - Employee Management Team

## 🐛 Known Issues

None at this time

## 🔮 Future Enhancements

- [ ] User authentication and authorization
- [ ] Role-based access control
- [ ] Employee attendance tracking
- [ ] Performance reviews module
- [ ] Document upload functionality
- [ ] Advanced reporting and analytics
- [ ] Email notifications
- [ ] Export to PDF/Excel
