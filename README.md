## Setup order-manager

####  Setep 1 - Clone the project.
*           $ git int
*           $ git clone https://github.com/luisNovaes/order-manager.git
*           $ mvn clean instal 

####  Setep 2 - Build and run image postgresDB whith pgadmin 
* - Enter the root directory of the application and run the command below, (where is the docker-compose.yml file).
*           $ docker compose up

####  Setep 3 Connect pgadmin whith postgresDB 

##### Login pgdmin
*   - http://localhost:5050/browser/
*   - Username : admin@admin.com
*   - Password: root

##### Connect pgdmin whith postgresDB
* - Select registe/server in the pgadmin
*   - General / Name: test_db

##### Get IP address of container postgres
*           $ docker ps
*   - copy the CONTAINER ID of postgres container and input in below command.
*           $ docker inspect <CONTAINER ID> | grep IPAddress
*   - Connection / Host: copy IP address from the output of the command above and input in the Host  

##### Login pgdmin
*   - Username : root
*   - Password:  root
*   - Save

####  Setep 3 - Run the Project. 

