## Setup order-manager

####  Setep 1 - Clone the project.
*           $ git init
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

##### Get IP HOST address of container postgres
*           $ docker ps
*   - copy the CONTAINER ID of postgres container and input in below command.
*           $ docker inspect <CONTAINER ID> | grep IPAddress
*   - Connection / Host: copy IP address from the output of the command above and input in the Host  

##### Login pgdmin
*   - Username : root
*   - Password:  root
*   - Save

####  Setep 3 - Run the Project. 

####  Setep 3 - Tests.
  * - There is a file in /order-manager/Postiman/order-manager.postman_collection.json whith collections for Postiman .

* 				GET: "http://localhost:8080/api/item/items				
					GET: "http://localhost:8080/api/item/items/{id}			
					PUT: "http://localhost:8080/api/item/item/{id}	
					POST: "http://localhost:8080/api/item/item					
					DELETE: "http://localhost:8080/api/item/items					
					DELETE: "http://localhost:8080/api/item/item/{id}
  
					
* 				GET: "http://localhost:8080/api/user/users				
		 			GET: "http://localhost:8080/api/item/user/{id}				
					PUT: "http://localhost:8080/api/item/users/{id}				
					POST: "http://localhost:8080/api/item/user		
					DELETE: "http://localhost:8080/api/item/users			
					DELETE: "http://localhost:8080/api/item/user/{id}
  			
*					GET: "http://localhost:8080/api/stockMovement/stockMovements  
  	      GET: "http://localhost:8080/api/stockMovement/stockMovement/{id}				
					POST: "http://localhost:8080/api/stockMovement/stockMovement
          PUT: "http://localhost:8080/api/stockMovement/stockMovement/{id}				
					DELETE: "http://localhost:8080/api/stockMovement/stockMovement/{id}			
					DELETE: "http://localhost:8080/api/stockMovement/stockMovements
  
					
*					GET: "http://localhost:8080/api/order/orders  
  	      GET: "http://localhost:8080/api/order/ordes/{id}  
          POST: "http://localhost:8080/api/item/order  
          PUT: "http://localhost:8080/api/order/order/{id}					
					DELETE: "http://localhost:8080/api/order/order/{id}					
					DELETE: "http://localhost:8080/api/order/order				
					DELETE: "http://localhost:8080/api/order/RegStockMov
					
