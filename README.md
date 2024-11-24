# TP Ingenieria de Software I - 2C2024

## Run with docker

To build and run the containers:

```bash
docker-compose up --build
```
The env vars are set on a `.env` file at root of the project 

## Access the API

- The API will be available at [http://localhost:8080](http://localhost:8080).
- The front-end interface is available at [http://localhost:5173](http://localhost:5173).

## Access the PostgreSQL Database

1. **Open a new terminal** and use the following command to enter the PostgreSQL container:
    ```bash
    docker exec -it is1_db psql -U is1_admin -d orderdb
    ```

2. Once inside the container, you can run SQL commands to inspect the database, like:
    ```sql
    \dt   -- List all tables
    \conninfo -- Check connection
    ```

## Access pgAdmin via Web

To access the PostgreSQL database using the web interface:

1. After running `docker-compose up --build`, pgAdmin will be available at [http://localhost:8081](http://localhost:8081).

2. Log in using the following credentials (defined in the `docker-compose.yml`):
    - Email: `admin@admin.com`
    - Password: `admin`

## Stop the containers

To stop the containers, press `CTRL + C` in the terminal where Docker Compose is running, then clean up the containers:
```bash
docker-compose down
```



