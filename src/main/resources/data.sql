USE slippers;
INSERT INTO Usuario(nome, email, senha, total_compras) VALUES ('andrye', 'email@email', '$2a$10$8hh3MN6RKoih4uTCH1GQK.MSRngIO5M2eSOXXsB6OCRh29t3WGNiu', 0);
INSERT INTO Carrinho(qtd_itens_carrinho, total_carrinho, usuario_id) VALUES (0, 0, 1);
UPDATE Usuario set carrinho_id = 1;