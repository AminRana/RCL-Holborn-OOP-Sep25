# Fix: "Cannot invoke prepareStatement because 'con' is null"

## Problem
The database connection is returning `null`, which means the application cannot connect to MySQL.

## Root Cause
One of these issues:
1. ❌ MySQL server is not running
2. ❌ Database `enrolment_db` doesn't exist
3. ❌ Wrong MySQL username or password
4. ❌ MySQL is not listening on port 3306

## Solution Steps

### Step 1: Check if MySQL is Running
```bash
# Check MySQL status
brew services list | grep mysql

# If not running, start it
brew services start mysql
```

### Step 2: Verify MySQL Credentials
Try logging into MySQL:
```bash
mysql -u root -p
```

If this fails, your password is NOT "root". Find your actual password and update:
- File: `src/db/DBConnection.java`
- Line 10: Change `PASSWORD = "root";` to your actual password

### Step 3: Create the Database
Once logged into MySQL:
```sql
CREATE DATABASE IF NOT EXISTS enrolment_db;
USE enrolment_db;
SHOW TABLES;
```

Or run the SQL script:
```bash
mysql -u root -p < /Users/gelbertdomiciano/Downloads/StudentEnrolmentApp/enrolment_db.sql
```

### Step 4: Test the Connection
```bash
cd /Users/gelbertdomiciano/Downloads/StudentEnrolmentApp
java -cp "build/classes:dist/lib/*" TestMySQLConnection
```

You should see:
```
✅ MySQL Connector Driver loaded successfully!
✅ Connected to MySQL database successfully!
✅ Connection closed successfully!
```

### Step 5: Recompile and Run
```bash
cd /Users/gelbertdomiciano/Downloads/StudentEnrolmentApp
javac -d build/classes -cp "dist/lib/*" src/db/*.java src/model/*.java src/ui/*.java src/Main.java
java -cp "build/classes:dist/lib/*" ui.MainFrame
```

## Quick Fix Commands

Run these in order:
```bash
# 1. Start MySQL
brew services start mysql

# 2. Create database
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS enrolment_db;"

# 3. Run the SQL setup
mysql -u root -p enrolment_db < /Users/gelbertdomiciano/Downloads/StudentEnrolmentApp/enrolment_db.sql

# 4. Test connection
cd /Users/gelbertdomiciano/Downloads/StudentEnrolmentApp
java -cp "build/classes:dist/lib/*" TestMySQLConnection

# 5. Run the app
java -cp "build/classes:dist/lib/*" ui.MainFrame
```

## Still Not Working?

### Check MySQL Port
```bash
lsof -i :3306
```

If nothing shows, MySQL is not running on port 3306.

### Check Error Logs
Look at the console output when running the app - it will show the exact error.

### Update Password in Code
Edit `src/db/DBConnection.java`:
```java
private static final String PASSWORD = "YOUR_ACTUAL_PASSWORD";
```

Then recompile:
```bash
javac -d build/classes -cp "dist/lib/*" src/db/*.java src/model/*.java src/ui/*.java src/Main.java
```
