-- Inserção das 3 Categorias (IDs gerados automaticamente: 1, 2 e 3)
INSERT INTO category (name)
VALUES ('Eletrónicos'),
       ('Periféricos'),
       ('Acessórios');

-- Inserção dos 15 Produtos (5 para cada categoria)

-- Categoria 1: Eletrónicos (category_id_tb = 1)
INSERT INTO product (name, description, price, quantity, category_id_tb)
VALUES ('Smartphone XYZ', 'Smartphone com 128GB de armazenamento', 1499.99, 10, 1),
       ('Portátil Pro', 'Computador portátil para trabalho e jogos', 4500.00, 5, 1),
       ('Tablet HD', 'Ecrã de 10 polegadas com caneta tátil', 899.90, 8, 1),
       ('Smartwatch Fit', 'Relógio inteligente com monitor cardíaco', 299.50, 15, 1),
       ('Monitor 24"', 'Monitor Full HD IPS 75Hz', 650.00, 12, 1);

-- Categoria 2: Periféricos (category_id_tb = 2)
INSERT INTO product (name, description, price, quantity, category_id_tb)
VALUES ('Teclado Mecânico', 'Teclado RGB com switches azuis', 250.00, 20, 2),
       ('Rato Gamer', 'Rato sem fios de 16000 DPI', 120.00, 30, 2),
       ('Auscultadores 7.1', 'Auscultadores com cancelamento ativo de ruído', 320.00, 14, 2),
       ('Webcam Full HD', 'Câmara 1080p com microfone integrado', 180.00, 22, 2),
       ('Tapete para Rato XL', 'Tapete antiderrapante com iluminação RGB', 75.00, 40, 2);

-- Categoria 3: Acessórios (category_id_tb = 3)
INSERT INTO product (name, description, price, quantity, category_id_tb)
VALUES ('Cabo HDMI 2.0', 'Cabo blindado de 2 metros', 35.00, 50, 3),
       ('Suporte para Portátil', 'Suporte ergonómico em alumínio', 95.00, 18, 3),
       ('Hub USB-C', 'Adaptador multiportas com 4 portas USB 3.0', 110.00, 25, 3),
       ('Carregador Rápido', 'Carregador de tomada 65W GaN', 140.00, 35, 3),
       ('Mochila Impermeável', 'Mochila reforçada para portátil até 15.6"', 160.00, 10, 3);