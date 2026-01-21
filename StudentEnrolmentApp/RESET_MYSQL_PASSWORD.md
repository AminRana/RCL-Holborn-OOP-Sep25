# Reset MySQL Root Password

## Method 1: Using MySQL Workbench (Easiest)

If you can still connect with MySQL Workbench:

1. **Open MySQL Workbench** and connect to your database
2. **Run this query:**
   ```sql
   ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword';
   FLUSH PRIVILEGES;
   ```
3. Replace `'newpassword'` with whatever password you want (e.g., 'root', '123456', etc.)

## Method 2: Reset from Terminal

### Step 1: Stop MySQL
```bash
# Find MySQL process
ps aux | grep mysql

# Kill the process (replace PID with the actual process ID)
sudo kill -9 PID
```

### Step 2: Start MySQL in Safe Mode
```bash
sudo mysqld_safe --skip-grant-tables &
```

### Step 3: Connect Without Password
```bash
mysql -u root
```

### Step 4: Reset Password
```sql
FLUSH PRIVILEGES;
ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword';
FLUSH PRIVILEGES;
exit;
```

### Step 5: Restart MySQL Normally
```bash
# Kill safe mode
sudo killall mysqld

# Start MySQL normally (if installed via Homebrew)
brew services start mysql

# Or if installed differently
sudo /usr/local/mysql/support-files/mysql.server start
```

## Method 3: Simple Password Reset (Recommended)

Since you have MySQL Workbench connected, just use it:

1. **In MySQL Workbench**, run:
   ```sql
   SET PASSWORD FOR 'root'@'localhost' = 'root';
   ```

2. **Then update your Java code** to use password: `root`

## After Resetting Password

Once you've reset your password to something simple like "root" or "123456", tell me what you set it to, and I'll update the Java code immediately!

## Quick Commands

**Set password to "root":**
```sql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
FLUSH PRIVILEGES;
```

**Set password to "123456":**
```sql
ALTER USER 'root'@'localhost' IDENTIFIED BY '123456';
FLUSH PRIVILEGES;
```

**Set password to empty (no password):**
```sql
ALTER USER 'root'@'localhost' IDENTIFIED BY '';
FLUSH PRIVILEGES;
```
