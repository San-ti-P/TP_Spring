-- ========================================= EJECUTAR EN LA HERRAMIENTA SQL, PARADO EN LA BD (NO EN ALGUNA TABLA ESPECIFICA) =========================================

INSERT INTO coordenada (id, lat, lng) VALUES 
(1, -34.6037, -58.3816), -- 1
(2, -34.6132, -58.3794), -- 2
(3, -34.6083, -58.3709), -- 3
(4, -34.5955, -58.3975), -- 4
(5, -34.6044, -58.3812), -- 5
(6, -34.6100, -58.3800), -- 6
(7, -34.6050, -58.3850), -- 7
(8, -34.6090, -58.3820), -- 8
(9, -34.6070, -58.3900), -- 9
(10, -34.6110, -58.3750), -- 10
(11, -34.6120, -58.3760), -- 11
(12, -34.6130, -58.3770), -- 12
(13, -34.6140, -58.3780), -- 13
(14, -34.6150, -58.3790), -- 14
(15, -34.6160, -58.3800), -- 15
(16, -34.6170, -58.3810), -- 16
(17, -34.6180, -58.3820), -- 17
(18, -34.6190, -58.3830), -- 18
(19, -34.6200, -58.3840), -- 19
(20, -34.6210, -58.3850), -- 20
(21, -34.6220, -58.3860), -- 21
(22, -34.6230, -58.3870), -- 22
(23, -34.6240, -58.3880), -- 23
(24, -34.6250, -58.3890), -- 24
(25, -34.6260, -58.3900), -- 25
(26, -34.6270, -58.3910), -- 26
(27, -34.6280, -58.3920), -- 27
(28, -34.6290, -58.3930), -- 28
(29, -34.6300, -58.3940), -- 29
(30, -34.6310, -58.3950); -- 30

INSERT INTO cliente (id, coordenadas_id, nombre, direccion, email, cuit, activo) VALUES 
(1, 11, 'Roberto Sánchez', 'Calle 7 N°1234', 'roberto@email.com', '20-12345678-9', 1),
(2, 12, 'Laura Díaz', 'Avenida 9 N°5678', 'laura@email.com', '27-87654321-0', 1),
(3, 13, 'Miguel Ruiz', 'Boulevard 11 N°910', 'miguel@email.com', '20-45678912-3', 1),
(4, 14, 'Sofía López', 'Pasaje 13 N°1112', 'sofia@email.com', '27-23456789-1', 1),
(5, 15, 'Diego Morales', 'Ruta 15 N°1314', 'diego@email.com', '20-56789123-4', 1),
(6, 16, 'Elena Pérez', 'Calle 17 N°1516', 'elena@email.com', '20-67891234-5', 1),
(7, 17, 'Fernando García', 'Avenida 19 N°1718', 'fernando@email.com', '27-78912345-6', 1),
(8, 18, 'Patricia Torres', 'Boulevard 21 N°1920', 'patricia@email.com', '20-89123456-7', 1),
(9, 19, 'Andrés Gómez', 'Pasaje 23 N°2122', 'andres@email.com', '27-91234567-8', 1),
(10, 20, 'Claudia Fernández', 'Ruta 25 N°2324', 'claudia@email.com', '20-12345678-9', 1),
(11, 21, 'María González', 'Calle 27 N°1234', 'maria@email.com', '20-98765432-1', 1),
(12, 22, 'Juan Pérez', 'Avenida 28 N°5678', 'juan@email.com', '27-87654321-2', 1),
(13, 23, 'Ana Rodríguez', 'Boulevard 29 N°910', 'ana@email.com', '20-76543210-3', 1),
(14, 24, 'Carlos Fernández', 'Pasaje 30 N°1112', 'carlos@email.com', '27-65432109-4', 1),
(15, 25, 'Lucía Martínez', 'Ruta 31 N°1314', 'lucian@email.com', '20-54321098-5', 1),
(16, 26, 'Pedro Sánchez', 'Calle 32 N°1516', 'pedros@email.com', '20-43210987-6', 1),
(17, 27, 'Laura Gómez', 'Avenida 33 N°1718', 'laurag@email.com', '27-32109876-7', 1),
(18, 28, 'Jorge Díaz', 'Boulevard 34 N°1920', 'jorged@email.com', '20-21098765-8', 1),
(19, 29, 'Sofía Sanchez', 'Pasaje 35 N°2122', 'sofiasan@email.com', '27-10987654-9', 1),
(20, 30, 'Diego Rios', 'Ruta 36 N°2324', 'diegorios@email.com', '20-09876543-0', 1);

INSERT INTO vendedor (id, nombre, direccion, coordenadas_id) VALUES 
(1, 'El Buen Sabor', 'Calle 1 N°123', 1),
(2, 'Sabor a Hogar', 'Avenida 2 N°456', 2),
(3, 'Delicias del Chef', 'Boulevard 3 N°789', 3),
(4, 'La Cocina de Abuela', 'Pasaje 4 N°101', 4),
(5, 'Sabores del Mundo', 'Ruta 5 N°202', 5),
(6, 'El Rincón Gourmet', 'Calle 6 N°1234', 6),
(7, 'Fusión de Sabores', 'Avenida 7 N°5678', 7),
(8, 'La Esquina del Sabor', 'Boulevard 8 N°910', 8),
(9, 'El Sabor de Casa', 'Pasaje 9 N°1112', 9),
(10, 'Gusto y Tradición', 'Ruta 10 N°1314', 10);

INSERT INTO categoria (id, descripcion, tipo_item) VALUES
(1, 'Platos Principales', 'PLATO'),    
(2, 'Bebidas Alcoholicas', 'BEBIDA'),   
(3, 'Postres', 'PLATO'),               
(4, 'Bebidas Sin Alcohol', 'BEBIDA'),   
(5, 'Gaseosas', 'BEBIDA'),              
(6, 'Hamburguesas', 'PLATO'),          
(7, 'Helados', 'PLATO'),               
(8, 'Vinos', 'BEBIDA'),                 
(9, 'Pastas', 'PLATO'),                
(10, 'Carnes', 'PLATO'),               
(11, 'Verduras', 'PLATO'),             
(12, 'Harinas', 'PLATO'),              
(13, 'Cervezas', 'BEBIDA');             

INSERT INTO item_menu (id, nombre, descripcion, precio, categoria_id, vendedor_id, apto_vegano) VALUES 
(1, 'Hamburguesa Clásica', 'Hamburguesa de carne con queso', 1200.00, 6, 1, 0),     -- El Buen Sabor, Plato
(2, 'Pizza Margherita', 'Pizza tradicional con albahaca', 1500.00, 1, 2, 1),        -- Sabor a Hogar, Plato
(3, 'Ensalada Verde', 'Ensalada mixta de verduras frescas', 800.00, 11, 3, 1),      -- Delicias del Chef, Plato
(4, 'Pollo al Horno', 'Pollo asado con hierbas', 1300.00, 10, 4, 0),                -- La Cocina de Abuela, Plato
(5, 'Tarta de Verduras', 'Tarta vegetariana multicolor', 950.00, 11, 5, 1),         -- Sabores del Mundo, Plato
(6, 'Sushi Variado', 'Selección de rolls', 2000.00, 1, 6, 0),                       -- El Rincón Gourmet, Plato
(7, 'Brownie de Chocolate', 'Postre de chocolate intenso', 600.00, 3, 7, 1),        -- Fusión de Sabores, Plato
(8, 'Empanadas Mix', 'Selección de 6 empanadas', 1100.00, 1, 8, 0),                 -- La Esquina del Sabor, Plato
(9, 'Cesar Vegano', 'Ensalada cesar sin ingredientes de origen animal', 850.00, 11, 9, 1), -- El Sabor de Casa, Plato
(10, 'Lasaña', 'Lasaña tradicional de carne', 1400.00, 1, 10, 0),                   -- Gusto y Tradición, Plato
(11, 'Tacos de Pollo', 'Tacos con pollo y vegetales', 1200.00, 1, 1, 0),            -- El Buen Sabor, Plato
(12, 'Sopa de Tomate', 'Sopa cremosa de tomate', 700.00, 7, 2, 1),                  -- Sabor a Hogar, Plato
(13, 'Ceviche', 'Ceviche de pescado fresco', 1800.00, 1, 3, 0),                     -- Delicias del Chef, Plato
(14, 'Bife de Chorizo', 'Bife de chorizo a la parrilla', 2200.00, 10, 4, 0),        -- La Cocina de Abuela, Plato
(15, 'Helado de Vainilla', 'Helado artesanal de vainilla', 500.00, 7, 5, 1),        -- Sabores del Mundo, Plato
(16, 'Cerveza Artesanal', 'Cerveza artesanal rubia', 200.00, 13, 1, 0),             -- El Buen Sabor, Bebida
(17, 'Jugo de Naranja', 'Jugo de naranja natural', 150.00, 5, 2, 1),                -- Sabor a Hogar, Bebida
(18, 'Whisky', 'Whisky escocés', 500.00, 2, 3, 0),                                  -- Delicias del Chef, Bebida
(19, 'Vino Tinto', 'Vino tinto Malbec', 300.00, 8, 4, 0),                           -- La Cocina de Abuela, Bebida
(20, 'Agua Mineral', 'Agua mineral sin gas', 100.00, 4, 5, 1),                      -- Sabores del Mundo, Bebida
(21, 'Limonada', 'Limonada casera', 120.00, 4, 6, 1),                               -- El Rincón Gourmet, Bebida
(22, 'Café Expreso', 'Café expreso italiano', 80.00, 4, 7, 1),                      -- Fusión de Sabores, Bebida
(23, 'Té Verde', 'Té verde orgánico', 90.00, 4, 8, 1),                              -- La Esquina del Sabor, Bebida
(24, 'Batido de Frutas', 'Batido de frutas frescas', 180.00, 4, 9, 1),              -- El Sabor de Casa, Bebida
(25, 'Mojito', 'Cóctel de ron con menta', 250.00, 2, 10, 0);                        -- Gusto y Tradición, Bebida

INSERT INTO bebida (id, graduacion_alcoholica, tamaño) VALUES 
(16, 5.5, 330), -- Cerveza Artesanal
(17, 0.0, 250), -- Jugo de Naranja
(18, 40.0, 50), -- Whisky
(19, 12.5, 750), -- Vino Tinto
(20, 0.0, 500), -- Agua Mineral
(21, 0.0, 400), -- Limonada
(22, 0.0, 200), -- Café Expreso
(23, 0.0, 300), -- Té Verde
(24, 0.0, 450), -- Batido de Frutas
(25, 10.0, 300); -- Mojito

INSERT INTO plato (id, apto_celiaco, calorias, peso) VALUES 
(1, 0, 450, 250.5), -- Hamburguesa Clásica
(2, 1, 320, 180.0), -- Pizza Margherita
(3, 1, 280, 200.5), -- Ensalada Verde
(4, 0, 550, 300.0), -- Pollo al Horno
(5, 1, 380, 220.5), -- Tarta de Verduras
(6, 0, 600, 350.0), -- Sushi Variado
(7, 1, 400, 250.0), -- Brownie de Chocolate
(8, 0, 500, 300.0), -- Empanadas Mix
(9, 1, 350, 200.0), -- Cesar Vegano
(10, 0, 450, 250.0), -- Lasaña
(11, 0, 300, 200.0), -- Tacos de Pollo
(12, 1, 150, 100.0), -- Sopa de Tomate
(13, 0, 200, 150.0), -- Ceviche
(14, 0, 700, 400.0), -- Bife de Chorizo
(15, 1, 200, 150.0); -- Helado de Vainilla

INSERT INTO estrategia_de_pago (id) VALUES 
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20);

INSERT INTO estrategia_mercado_pago (id, alias) VALUES 
(1, 'mercadopago_roberto'),
(2, 'mercadopago_laura'),
(3, 'mercadopago_miguel'),
(4, 'mercadopago_sofia'),
(5, 'mercadopago_diego'),
(6, 'mercadopago_elena'),
(7, 'mercadopago_fernando'),
(8, 'mercadopago_patricia'),
(9, 'mercadopago_andres'),
(10, 'mercadopago_claudia');

INSERT INTO estrategia_transferencia (id, cbu, cuit) VALUES 
(11, '8901234567890123456789', '20-98765432-1'), 
(12, '9012345678901234567890', '27-87654321-2'), 
(13, '0123456789012345678901', '20-76543210-3'), 
(14, '1234567890123456789013', '27-65432109-4'), 
(15, '2345678901234567890124', '20-54321098-5'), 
(16, '3456789012345678901235', '20-43210987-6'), 
(17, '4567890123456789012346', '27-32109876-7'), 
(18, '5678901234567890123457', '20-21098765-8'), 
(19, '6789012345678901234568', '27-10987654-9'), 
(20, '7890123456789012345679', '20-09876543-0');

INSERT INTO pedido (id, cliente_id, vendedor_id, precio, estado) VALUES 
(1, 1, 1, 3200.00, 'EN_PREPARACION'), -- Roberto Sánchez, El Buen Sabor
(2, 2, 2, 2700.00, 'ENTREGADO'), -- Laura Díaz, Sabor a Hogar
(3, 3, 3, 2050.00, 'RECIBIDO'), -- Miguel Ruiz, Delicias del Chef
(4, 4, 4, 3450.00, 'EN_PREPARACION'), -- Sofía López, La Cocina de Abuela
(5, 5, 5, 3000.00, 'ENTREGADO'), -- Diego Morales, Sabores del Mundo
(6, 6, 6, 2500.00, 'RECIBIDO'), -- Elena Pérez, El Rincón Gourmet
(7, 7, 7, 2800.00, 'EN_PREPARACION'), -- Fernando García, Fusión de Sabores
(8, 8, 8, 3100.00, 'ENTREGADO'), -- Patricia Torres, La Esquina del Sabor
(9, 9, 9, 2900.00, 'RECIBIDO'), -- Andrés Gómez, El Sabor de Casa
(10, 10, 10, 3300.00, 'EN_PREPARACION'), -- Claudia Fernández, Gusto y Tradición
(11, 11, 1, 1500.00, 'RECIBIDO'), -- María González, El Buen Sabor
(12, 12, 2, 1800.00, 'EN_PREPARACION'), -- Juan Pérez, Sabor a Hogar
(13, 13, 3, 2200.00, 'ENTREGADO'), -- Ana Rodríguez, Delicias del Chef
(14, 14, 4, 2600.00, 'RECIBIDO'), -- Carlos Fernández, La Cocina de Abuela
(15, 15, 5, 2400.00, 'EN_PREPARACION'), -- Lucía Martínez, Sabores del Mundo
(16, 16, 6, 2000.00, 'ENTREGADO'), -- Pedro Sánchez, El Rincón Gourmet
(17, 17, 7, 2700.00, 'RECIBIDO'), -- Laura Gómez, Fusión de Sabores
(18, 18, 8, 3100.00, 'EN_PREPARACION'), -- Jorge Díaz, La Esquina del Sabor
(19, 19, 9, 2900.00, 'ENTREGADO'), -- Sofía Sanchez, El Sabor de Casa
(20, 20, 10, 3300.00, 'RECIBIDO'); -- Diego Rios, Gusto y Tradición

INSERT INTO pago (id, pedido_id, monto_final, fecha, estrategia_de_pago_id) VALUES 
(1, 1, 3200.00, '2024-02-15 19:30:00', 1), -- Pedido 1, Estrategia de Pago 1
(2, 2, 2700.00, '2024-02-16 20:15:00', 2), -- Pedido 2, Estrategia de Pago 2
(3, 3, 2050.00, '2024-02-17 18:45:00', 3), -- Pedido 3, Estrategia de Pago 3
(4, 4, 3450.00, '2024-02-18 21:00:00', 4), -- Pedido 4, Estrategia de Pago 4
(5, 5, 3000.00, '2024-02-19 19:45:00', 5), -- Pedido 5, Estrategia de Pago 5
(6, 6, 2500.00, '2024-02-20 18:30:00', 6), -- Pedido 6, Estrategia de Pago 6
(7, 7, 2800.00, '2024-02-21 20:00:00', 7), -- Pedido 7, Estrategia de Pago 7
(8, 8, 3100.00, '2024-02-22 19:15:00', 8), -- Pedido 8, Estrategia de Pago 8
(9, 9, 2900.00, '2024-02-23 18:45:00', 9), -- Pedido 9, Estrategia de Pago 9
(10, 10, 3300.00, '2024-02-24 20:30:00', 10), -- Pedido 10, Estrategia de Pago 10
(11, 11, 1500.00, '2024-02-25 19:30:00', 11), -- Pedido 11, Estrategia de Pago 11
(12, 12, 1800.00, '2024-02-26 20:15:00', 12), -- Pedido 12, Estrategia de Pago 12
(13, 13, 2200.00, '2024-02-27 18:45:00', 13), -- Pedido 13, Estrategia de Pago 13
(14, 14, 2600.00, '2024-02-28 21:00:00', 14), -- Pedido 14, Estrategia de Pago 14
(15, 15, 2400.00, '2024-03-01 19:45:00', 15), -- Pedido 15, Estrategia de Pago 15
(16, 16, 2000.00, '2024-03-02 18:30:00', 16), -- Pedido 16, Estrategia de Pago 16
(17, 17, 2700.00, '2024-03-03 20:00:00', 17), -- Pedido 17, Estrategia de Pago 17
(18, 18, 3100.00, '2024-03-04 19:15:00', 18), -- Pedido 18, Estrategia de Pago 18
(19, 19, 2900.00, '2024-03-05 18:45:00', 19), -- Pedido 19, Estrategia de Pago 19
(20, 20, 3300.00, '2024-03-06 20:30:00', 20); -- Pedido 20, Estrategia de Pago 20

UPDATE pedido SET pago_id = 1 WHERE id = 1;
UPDATE pedido SET pago_id = 2 WHERE id = 2;
UPDATE pedido SET pago_id = 3 WHERE id = 3;
UPDATE pedido SET pago_id = 4 WHERE id = 4;
UPDATE pedido SET pago_id = 5 WHERE id = 5;
UPDATE pedido SET pago_id = 6 WHERE id = 6;
UPDATE pedido SET pago_id = 7 WHERE id = 7;
UPDATE pedido SET pago_id = 8 WHERE id = 8;
UPDATE pedido SET pago_id = 9 WHERE id = 9;
UPDATE pedido SET pago_id = 10 WHERE id = 10;
UPDATE pedido SET pago_id = 11 WHERE id = 11;
UPDATE pedido SET pago_id = 12 WHERE id = 12;
UPDATE pedido SET pago_id = 13 WHERE id = 13;
UPDATE pedido SET pago_id = 14 WHERE id = 14;
UPDATE pedido SET pago_id = 15 WHERE id = 15;
UPDATE pedido SET pago_id = 16 WHERE id = 16;
UPDATE pedido SET pago_id = 17 WHERE id = 17;
UPDATE pedido SET pago_id = 18 WHERE id = 18;
UPDATE pedido SET pago_id = 19 WHERE id = 19;
UPDATE pedido SET pago_id = 20 WHERE id = 20;

INSERT INTO item_pedido (id, pedido_id, item_id, cantidad) VALUES 
(1, 1, 1, 2), -- Pedido 1, Hamburguesa Clásica (El Buen Sabor)
(2, 1, 11, 1), -- Pedido 1, Tacos de Pollo (El Buen Sabor)
(3, 2, 2, 1), -- Pedido 2, Pizza Margherita (Sabor a Hogar)
(4, 2, 12, 2), -- Pedido 2, Sopa de Tomate (Sabor a Hogar)
(5, 3, 3, 1), -- Pedido 3, Ensalada Verde (Delicias del Chef)
(6, 3, 13, 1), -- Pedido 3, Ceviche (Delicias del Chef)
(7, 4, 4, 2), -- Pedido 4, Pollo al Horno (La Cocina de Abuela)
(8, 4, 14, 1), -- Pedido 4, Bife de Chorizo (La Cocina de Abuela)
(9, 5, 5, 1), -- Pedido 5, Tarta de Verduras (Sabores del Mundo)
(10, 5, 15, 1), -- Pedido 5, Helado de Vainilla (Sabores del Mundo)
(11, 6, 6, 2), -- Pedido 6, Sushi Variado (El Rincón Gourmet)
(12, 6, 21, 1), -- Pedido 6, Limonada (El Rincón Gourmet)
(13, 7, 7, 1), -- Pedido 7, Brownie de Chocolate (Fusión de Sabores)
(14, 7, 22, 2), -- Pedido 7, Café Expreso (Fusión de Sabores)
(15, 8, 8, 1), -- Pedido 8, Empanadas Mix (La Esquina del Sabor)
(16, 8, 23, 1), -- Pedido 8, Té Verde (La Esquina del Sabor)
(17, 9, 9, 2), -- Pedido 9, Cesar Vegano (El Sabor de Casa)
(18, 9, 24, 1), -- Pedido 9, Batido de Frutas (El Sabor de Casa)
(19, 10, 10, 1), -- Pedido 10, Lasaña (Gusto y Tradición)
(20, 10, 25, 2), -- Pedido 10, Mojito (Gusto y Tradición)
(21, 11, 1, 1), -- Pedido 11, Hamburguesa Clásica (El Buen Sabor)
(22, 11, 11, 1), -- Pedido 11, Tacos de Pollo (El Buen Sabor)
(23, 12, 2, 2), -- Pedido 12, Pizza Margherita (Sabor a Hogar)
(24, 12, 12, 1), -- Pedido 12, Sopa de Tomate (Sabor a Hogar)
(25, 13, 3, 1), -- Pedido 13, Ensalada Verde (Delicias del Chef)
(26, 13, 13, 2), -- Pedido 13, Ceviche (Delicias del Chef)
(27, 14, 4, 1), -- Pedido 14, Pollo al Horno (La Cocina de Abuela)
(28, 14, 14, 1), -- Pedido 14, Bife de Chorizo (La Cocina de Abuela)
(29, 15, 5, 2), -- Pedido 15, Tarta de Verduras (Sabores del Mundo)
(30, 15, 15, 1), -- Pedido 15, Helado de Vainilla (Sabores del Mundo)
(31, 16, 6, 1), -- Pedido 16, Sushi Variado (El Rincón Gourmet)
(32, 16, 21, 1), -- Pedido 16, Limonada (El Rincón Gourmet)
(33, 17, 7, 2), -- Pedido 17, Brownie de Chocolate (Fusión de Sabores)
(34, 17, 22, 1), -- Pedido 17, Café Expreso (Fusión de Sabores)
(35, 18, 8, 1), -- Pedido 18, Empanadas Mix (La Esquina del Sabor)
(36, 18, 23, 1), -- Pedido 18, Té Verde (La Esquina del Sabor)
(37, 19, 9, 2), -- Pedido 19, Cesar Vegano (El Sabor de Casa)
(38, 19, 24, 1), -- Pedido 19, Batido de Frutas (El Sabor de Casa)
(39, 20, 10, 1), -- Pedido 20, Lasaña (Gusto y Tradición)
(40, 20, 25, 2); -- Pedido 20, Mojito (Gusto y Tradición)