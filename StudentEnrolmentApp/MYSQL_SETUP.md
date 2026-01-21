# MySQL Connector Setup - ✅ COMPLETED

## Status
The MySQL Connector JAR is properly configured and working!

## What Was Done
1. ✅ MySQL Connector JAR exists: `dist/lib/mysql-connector-j-9.5.0.jar`
2. ✅ Added MySQL connector to IntelliJ IDEA project (StudentEnrolmentApp.iml)
3. ✅ Driver loads successfully: `com.mysql.cj.jdbc.Driver`

## Current Configuration

### Database Connection (DBConnection.java)
```java
URL: jdbc:mysql://localhost:3306/enrolment_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
Username: root
Password: root
```

## Next Steps Required

### 1. Update MySQL Password (if different)
If your MySQL root password is not "root", update it in:
- `src/db/DBConnection.java` (line 10)
- `src/TestMySQLConnection.java` (line 14)

### 2. Ensure MySQL Server is Running
```bash
# On macOS with Homebrew
brew services start mysql

# Or check if running
brew services list
```

### 3. Create the Database
```bash
mysql -u root -p
```
Then run:
```sql
CREATE DATABASE IF NOT EXISTS enrolment_db;
```

Or use the provided SQL file:
```bash
mysql -u root -p < enrolment_db.sql
```

## Testing

### Test MySQL Connection
```bash
cd /Users/gelbertdomiciano/Downloads/StudentEnrolmentApp
java -cp "build/classes:dist/lib/*" TestMySQLConnection
```

### Run the Application
```bash
java -cp "build/classes:dist/lib/*" Main
```

## IntelliJ IDEA Setup
The MySQL connector is now added to the project. You may need to:
1. **Reload the project**: File → Invalidate Caches → Invalidate and Restart
2. **Or**: File → Reload Project from Disk

## Troubleshooting

### Error: "Access denied for user 'root'@'localhost'"
- Update the password in `DBConnection.java` to match your MySQL root password

### Error: "Communications link failure"
- Ensure MySQL server is running
- Check that MySQL is listening on port 3306

### Error: "Unknown database 'enrolment_db'"
- Create the database using the SQL script provided
