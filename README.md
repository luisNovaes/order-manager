## Setup order-manager
### Option 1 -  Using Container
####  Setep 1 - Connect pgadmin whith postgresDB 
* - Enter the root directory of the application and run the command below, (where is the docker-compose.yml file).
*           $ docker compose up
####  Setep 2 - Connect pgadmin whith postgresDB 
* - Select registe/server in the pgadmin
*   - General / Name: test_db
*           $ docker ps
*   - copy the CONTAINER ID of postgres container and input in below command.
*           $ docker inspect <CONTAINER ID> | grep IPAddress
*   - Connection / Host: copy IP address from the output of the command above and input in the Host                      
*   - Username :root
*   - Password: root
*   - Save
  
####  Setep 3 - Populating the database
*            INSERT INTO public.users (id, email, name) VALUES (1, 'mario@gmail.com','Mario Silva');
             INSERT INTO public.users (id, email, name) VALUES (2, 'luis@gmail.com', 'Luis Novaes');
             INSERT INTO public.users (id, email, name) VALUES (3, 'jonh@gmail.com', 'onh Steven');
             INSERT INTO public.users (id, email, name) VALUES (4, 'will@gmail.com', 'Willian Severo');
             INSERT INTO public.users (id, email, name) VALUES (5, 'capacho@gmail.com','Sarah Capacho');

*            INSERT INTO public.items(id, name) VALUES (1, 'Keyboard');
             INSERT INTO public.items(id, name) VALUES (2, 'Mouse');
             INSERT INTO public.items(id, name) VALUES (3, 'Laptop');
             INSERT INTO public.items(id, name) VALUES (4, 'Power 15A');
             INSERT INTO public.items(id, name) VALUES (5, 'Cabo RS45');

*            INSERT INTO public.stock_movements (id, creation_date, quantity, item_id) VALUES (1, TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 10, 1);
             INSERT INTO public.stock_movements (id, creation_date, quantity, item_id) VALUES (2, TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 10, 2);
             INSERT INTO public.stock_movements (id, creation_date, quantity, item_id) VALUES (3, TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 10, 3);
           - INSERT INTO public.stock_movements (id, creation_date, quantity, item_id) VALUES (4, TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 10, 4);
           - INSERT INTO public.stock_movements (id, creation_date, quantity, item_id) VALUES (5, TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 10, 5);

### Option 2 -  Using maven
   
