

INSERT INTO public.users (id, email, name) VALUES (1, 'Mario Silva', 'mario@gmail.com');
INSERT INTO public.users (id, email, name) VALUES (2, 'Luis Novaes', 'luis@gmail.com');
INSERT INTO public.users (id, email, name) VALUES (3, 'onh Steven', 'jonh@gmail.com');
INSERT INTO public.users (id, email, name) VALUES (4, 'Willian Severo', 'will@gmail.com');
INSERT INTO public.users (id, email, name) VALUES (5, 'Sarah Capacho', 'capacho@gmail.com');

INSERT INTO public.items(id, name) VALUES (1, 'Keyboard');
INSERT INTO public.items(id, name) VALUES (2, 'Mouse');
INSERT INTO public.items(id, name) VALUES (3, 'Laptop');
INSERT INTO public.items(id, name) VALUES (4, 'Power 15A');
INSERT INTO public.items(id, name) VALUES (5, 'Cabo RS45');

INSERT INTO public.stock_movements (id, creation_date, quantity, item_id) VALUES (1, TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 10, 1);
INSERT INTO public.stock_movements (id, creation_date, quantity, item_id) VALUES (2, TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 10, 2);
INSERT INTO public.stock_movements (id, creation_date, quantity, item_id) VALUES (3, TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 10, 3);
INSERT INTO public.stock_movements (id, creation_date, quantity, item_id) VALUES (4, TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 10, 4);
INSERT INTO public.stock_movements (id, creation_date, quantity, item_id) VALUES (5, TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 10, 5);

INSERT INTO public.orders(id, creation_date, quantity, item_id, user_id) VALUES (1,  TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 2, 1, 1);
INSERT INTO public.orders(id, creation_date, quantity, item_id, user_id) VALUES (2,  TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 2, 2, 2);
INSERT INTO public.orders(id, creation_date, quantity, item_id, user_id) VALUES (3,  TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 2, 3, 3);
INSERT INTO public.orders(id, creation_date, quantity, item_id, user_id) VALUES (4,  TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 2, 4, 4);
INSERT INTO public.orders(id, creation_date, quantity, item_id, user_id) VALUES (5,  TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02', 2, 5, 5);
