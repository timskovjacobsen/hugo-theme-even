# Processes and Ports

## Kill a task running on a port

!!! tip "Windows (PowerShell)"
    1.  Find the process ID (PID)
        ```powershell
        netstat -ano | findstr :<port_number>
        ```

    2. Kill the process
        ```powershell
        taskkill /PID <pid> /F
        ```
